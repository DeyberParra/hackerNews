package com.deyber.hackernews.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deyber.hackernews.core.network.doFailure
import com.deyber.hackernews.core.network.doLoading
import com.deyber.hackernews.core.network.doSuccess
import com.deyber.hackernews.di.useCases.GetNewsUseCasesQualifier
import com.deyber.hackernews.domain.GetNewsUseCase
import com.deyber.hackernews.domain.model.ui.HitModel
import com.deyber.hackernews.domain.model.ui.NewsResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel  @Inject constructor(
    @GetNewsUseCasesQualifier
    private val getNewsUseCase: GetNewsUseCase,
    state: SavedStateHandle
): ViewModel(){

    private val _news = state.getLiveData<NewsResponseModel>("news")
    val news : LiveData<NewsResponseModel> = _news


    fun getNews(){
        viewModelScope.launch(Dispatchers.IO) {
            val news = getNewsUseCase.invoke()
            news.doLoading {

            }
            news.doSuccess { new ->
                _news.postValue(new)
            }

            news.doFailure { error, throwable, typeErrorType ->

            }
        }
    }
}