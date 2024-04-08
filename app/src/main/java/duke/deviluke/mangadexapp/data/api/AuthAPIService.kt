package duke.deviluke.mangadexapp.data.api

import duke.deviluke.mangadexapp.data.model.AuthData
import duke.deviluke.mangadexapp.data.modelJson.TokenJson
import duke.deviluke.mangadexapp.data.util.Resource
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthAPIService {

    @Headers(
        value = [
            "Content-Type: application/x-www-form-urlencoded"
        ]
    )
    @FormUrlEncoded
    @POST("realms/mangadex/protocol/openid-connect/token/")
    suspend fun getAuthenticating(
        @Field("grant_type")
        grantType: String,
        @Field("username")
        username: String,
        @Field("password")
        password: String,
        @Field("client_id")
        clientId: String,
        @Field("client_secret")
        clientSecret: String
    ): Response<TokenJson>
}