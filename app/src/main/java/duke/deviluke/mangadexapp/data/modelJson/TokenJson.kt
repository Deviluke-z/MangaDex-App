package duke.deviluke.mangadexapp.data.modelJson


import com.google.gson.annotations.SerializedName
import duke.deviluke.mangadexapp.data.model.Token

data class TokenJson(
    @SerializedName("access_token")
    val accessToken: String?,
    @SerializedName("refresh_token")
    val refreshToken: String?
)

fun TokenJson.toModel(): Token = Token(
    accessToken = accessToken ?: "",
    refreshToken = refreshToken ?: ""
)

