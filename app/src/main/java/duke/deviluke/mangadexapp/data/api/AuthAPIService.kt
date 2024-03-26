package duke.deviluke.mangadexapp.data.api

import duke.deviluke.mangadexapp.data.model.AuthData
import duke.deviluke.mangadexapp.data.modelJson.TokenJson
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthAPIService {

    @Headers(
        value = [
            "Content-Type: application/vnd.api+json"
        ]
    )
    @POST
    suspend fun getAuthenticating(@Body authData: AuthData): Response<TokenJson>
}