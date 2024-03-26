package duke.deviluke.mangadexapp.data.repository.dataSource

import duke.deviluke.mangadexapp.data.model.AuthData
import duke.deviluke.mangadexapp.data.modelJson.TokenJson
import retrofit2.Response

interface AuthRemoteDataSource {
    suspend fun postAuthData(authData: AuthData): Response<TokenJson>
}