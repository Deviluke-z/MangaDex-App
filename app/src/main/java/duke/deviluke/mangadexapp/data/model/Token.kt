package duke.deviluke.mangadexapp.data.model

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("access_token")
    val accessToken: String = "",
    @SerializedName("refresh_token")
    val refreshToken: String = ""
)
