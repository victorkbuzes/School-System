package com.example.e_commerceapp.presentation.products.productsList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.domain.repository.ProductsRepository
import com.example.e_commerceapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private  val repository: ProductsRepository
): ViewModel() {
    var state by mutableStateOf(ProductsState())
    private var searchJob: Job? = null

    fun onEvent(event: ProductsEvents) {
        when(event){

            is ProductsEvents.Refresh -> {
                getProductsListings(fetchFromRemote = true)

            }
            is ProductsEvents.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getProductsListings()
                }


            }

        }
    }
    private fun getProductsListings(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository
                .getProducts(fetchFromRemote,query)
                .collect { result ->
                    when(result){
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                    state = state.copy(
                                        products = listings
                                    )

                            }
                        }
                        is Resource.Error -> { Unit

                        }
                        is Resource.Loading -> {
                            state.copy(isLoading = result.isLoading)

                        }
                    }

                }



        }
    }
}







