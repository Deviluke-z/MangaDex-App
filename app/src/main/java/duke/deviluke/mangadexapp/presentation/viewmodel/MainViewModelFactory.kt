package duke.deviluke.mangadexapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import duke.deviluke.mangadexapp.domain.usecases.GetRandomMangaUseCase
import duke.deviluke.mangadexapp.domain.usecases.LoginUseCase

class MainViewModelFactory(
    private val application: Application,
    private val loginUseCase: LoginUseCase,
    private val getRandomMangaUseCase: GetRandomMangaUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            application,
            loginUseCase,
            getRandomMangaUseCase
        ) as T
    }
}