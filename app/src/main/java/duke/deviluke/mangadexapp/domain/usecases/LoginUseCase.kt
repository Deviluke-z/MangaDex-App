package duke.deviluke.mangadexapp.domain.usecases

import android.util.Log
import duke.deviluke.mangadexapp.MangaDexApplication.Companion.DEBUG_TAG
import duke.deviluke.mangadexapp.data.model.AuthData
import duke.deviluke.mangadexapp.data.modelJson.TokenJson
import duke.deviluke.mangadexapp.data.util.Resource
import duke.deviluke.mangadexapp.domain.repository.AuthDataRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(private val authDataRepository: AuthDataRepository) {

    suspend fun execute(authData: AuthData): Flow<Resource<TokenJson>> {
        Log.d(DEBUG_TAG, "LoginUseCase: execute()")
        return authDataRepository.postAuthData(authData)
    }
}