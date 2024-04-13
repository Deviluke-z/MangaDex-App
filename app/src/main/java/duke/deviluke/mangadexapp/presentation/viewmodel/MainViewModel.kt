package duke.deviluke.mangadexapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import duke.deviluke.mangadexapp.MangaDexApplication.Companion.DEBUG_TAG
import duke.deviluke.mangadexapp.data.model.AuthData
import duke.deviluke.mangadexapp.data.modelJson.ListMangaDataJson
import duke.deviluke.mangadexapp.data.modelJson.MangaDataJson
import duke.deviluke.mangadexapp.data.modelJson.TokenJson
import duke.deviluke.mangadexapp.data.modelJson.toModel
import duke.deviluke.mangadexapp.data.util.Resource
import duke.deviluke.mangadexapp.domain.usecases.GetLatestMangaListUseCase
import duke.deviluke.mangadexapp.domain.usecases.GetMangaCoverPathUseCase
import duke.deviluke.mangadexapp.domain.usecases.GetRandomMangaUseCase
import duke.deviluke.mangadexapp.domain.usecases.LoginUseCase
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MainViewModel(
    private val application: Application,
    private val loginUseCase: LoginUseCase,
    private val getRandomMangaUseCase: GetRandomMangaUseCase,
    private val getLatestMangaListUseCase: GetLatestMangaListUseCase,
    private val getMangaCoverPathUseCase: GetMangaCoverPathUseCase
) : AndroidViewModel(application) {

    companion object {
        private val INTERNET_NOT_AVAILABLE_MESSAGE = "Internet is not available"
    }

    val _authToken = MutableStateFlow<Resource<TokenJson>>(Resource.Loading())
    val authToken = _authToken.asStateFlow()

    val _randomMangaData = MutableStateFlow<Resource<MangaDataJson>>(Resource.Loading())
    val randomMangaData = _randomMangaData.asStateFlow()

    val _latestMangaList = MutableStateFlow<Resource<ListMangaDataJson>>(Resource.Loading())
    val latestMangaList = _latestMangaList.asStateFlow()

    fun getAuthToken(authData: AuthData) = viewModelScope.launch {
        Log.d(DEBUG_TAG, "MainViewModel: getAuthToken()")

        if (isNetworkAvailable(application)) {
            loginUseCase.execute(authData)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _authToken.value = Resource.Failure(e.message.toString())
                }
                .collect { result ->
                    result.data?.let { _authToken.value = Resource.Success(it) }
                }
        } else {
            _authToken.value = Resource.Failure(INTERNET_NOT_AVAILABLE_MESSAGE)
        }
    }

    fun getRandomMangaData() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(DEBUG_TAG, "MainViewModel: getRandomMangaData()")
        if (isNetworkAvailable(application)) {
            getRandomMangaUseCase.execute()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _randomMangaData.value = Resource.Failure(e.message.toString())
                }
                .collect { result ->
                    result.data?.let {
                        _randomMangaData.value = Resource.Success(it)
                    }
                }
        } else {
            _randomMangaData.value = Resource.Failure(INTERNET_NOT_AVAILABLE_MESSAGE)
        }
    }

    fun getLatestMangaListData() = viewModelScope.launch(Dispatchers.IO) {
        Log.d(DEBUG_TAG, "MainViewModel: getLatestMangaListData()")
        if (isNetworkAvailable(application)) {
            getLatestMangaListUseCase.execute()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _latestMangaList.value = Resource.Failure(e.message.toString())
                }
                .collect { result ->
                    result.data?.let {
                        _latestMangaList.value = Resource.Success(it)
                    }
                }
        } else {
            _latestMangaList.value = Resource.Failure(INTERNET_NOT_AVAILABLE_MESSAGE)
        }
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        Log.d(DEBUG_TAG, "MainViewModel: isNetworkAvailable()")
        var result = false

        if (context == null) {
            return false
        }

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        connectivityManager?.run {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
        return result
    }
}