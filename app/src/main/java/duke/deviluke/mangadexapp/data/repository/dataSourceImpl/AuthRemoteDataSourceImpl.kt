package duke.deviluke.mangadexapp.data.repository.dataSourceImpl

import duke.deviluke.mangadexapp.data.api.AuthAPIService
import duke.deviluke.mangadexapp.data.model.AuthData
import duke.deviluke.mangadexapp.data.modelJson.TokenJson
import duke.deviluke.mangadexapp.data.repository.dataSource.AuthRemoteDataSource
import duke.deviluke.mangadexapp.data.util.Resource
import retrofit2.Response

class AuthRemoteDataSourceImpl(
    private val authAPIService: AuthAPIService
) : AuthRemoteDataSource {

    override suspend fun postAuthData(authData: AuthData): Response<TokenJson> {
        return authAPIService.getAuthenticating(
            authData.grantType,
            authData.username,
            authData.password,
            authData.clientId,
            authData.clientSecret
        )
    }
}