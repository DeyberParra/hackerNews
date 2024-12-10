package com.deyber.hackernews.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deyber.hackernews.core.network.doFailure
import com.deyber.hackernews.core.network.doLoading
import com.deyber.hackernews.core.network.doSuccess
import com.deyber.hackernews.di.useCases.GetNewsUseCasesQualifier
import com.deyber.hackernews.domain.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel  @Inject constructor(
    @GetNewsUseCasesQualifier
    private val getNewsUseCase: GetNewsUseCase
): ViewModel(){

    fun getNews(){
        viewModelScope.launch(Dispatchers.IO) {
            val news = getNewsUseCase.invoke()
            news.doLoading {
                println("deyber : estamos cargando data!....")
            }
            news.doSuccess {
                println("deyber : las news son : $it")
            }

            news.doFailure { error, throwable, typeErrorType ->
                println("deyber : ha fallado $error $throwable" )
            }

        }
    }
}