package com.deyber.hackernews.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deyber.hackernews.core.network.Resource
import com.deyber.hackernews.core.network.doFailure
import com.deyber.hackernews.core.network.doSuccess
import com.deyber.hackernews.di.useCases.DeleteHitUseCasesQualifier
import com.deyber.hackernews.di.useCases.GetNewsUseCasesQualifier
import com.deyber.hackernews.domain.DeleteHitUseCase
import com.deyber.hackernews.domain.GetNewsUseCase
import com.deyber.hackernews.domain.model.ui.HitModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    @GetNewsUseCasesQualifier
    private val getNewsUseCase: GetNewsUseCase,
    @DeleteHitUseCasesQualifier
    private val deleteHitUseCase: DeleteHitUseCase,
) : ViewModel() {

    private val _hits = MutableLiveData<Resource<List<HitModel>>>()
    val hits: LiveData<Resource<List<HitModel>>> = _hits

    fun getNews() {
        _hits.postValue(Resource.Loading())
        viewModelScope.launch {
            val news = getNewsUseCase.invoke()
            _hits.postValue(news)
        }
    }

    fun deleteHit(hit: HitModel) {
        viewModelScope.launch {
            val deleteHitResponse = deleteHitUseCase.invoke(hit.storyId!!, hit.createdAtI!!)
            deleteHitResponse.doSuccess {
                getNews()
            }
            deleteHitResponse.doFailure { error, throwable, typeErrorType ->
            }
        }
    }
}
