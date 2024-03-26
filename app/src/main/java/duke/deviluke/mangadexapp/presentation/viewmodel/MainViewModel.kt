package duke.deviluke.mangadexapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import duke.deviluke.mangadexapp.MangaDexApplication.Companion.DEBUG_TAG
import duke.deviluke.mangadexapp.data.model.AuthData
import duke.deviluke.mangadexapp.data.modelJson.TokenJson
import duke.deviluke.mangadexapp.data.util.Resource
import duke.deviluke.mangadexapp.domain.usecases.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(
    private val application: Application,
    private val loginUseCase: LoginUseCase
) : AndroidViewModel(application) {

    companion object {
        private val INTERNET_NOT_AVAILABLE_MESSAGE = "Internet is not available"
    }

    val authToken: MutableLiveData<Resource<TokenJson>> = MutableLiveData()

    fun getAuthToken(authData: AuthData) = viewModelScope.launch(Dispatchers.IO) {
        Log.d(DEBUG_TAG, "MainViewModel: getAuthToken()")
        try {
            if (isNetworkAvailable(application)) {
                authToken.postValue(Resource.Loading())
                val token = viewModelScope.async {
                    loginUseCase.execute(authData)
                }
                authToken.postValue(token.await())
            } else {
                authToken.postValue(Resource.Failure(INTERNET_NOT_AVAILABLE_MESSAGE))
            }
        } catch (e: Exception) {
            authToken.postValue(Resource.Failure(e.message.toString()))
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