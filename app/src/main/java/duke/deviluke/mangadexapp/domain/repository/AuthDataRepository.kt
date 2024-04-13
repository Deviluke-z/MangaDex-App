package duke.deviluke.mangadexapp.domain.repository

import duke.deviluke.mangadexapp.data.model.AuthData
import duke.deviluke.mangadexapp.data.modelJson.TokenJson
import duke.deviluke.mangadexapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthDataRepository {

    suspend fun postAuthData(authData: AuthData): Flow<Resource<TokenJson>>
}