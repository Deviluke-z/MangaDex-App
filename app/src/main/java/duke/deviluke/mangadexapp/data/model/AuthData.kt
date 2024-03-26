package duke.deviluke.mangadexapp.data.model


import com.google.gson.annotations.SerializedName

data class AuthData(
    @SerializedName("grant_type")
    val grantType: String = "password",
    @SerializedName("username")
    val username: String = "",
    @SerializedName("password")
    val password: String = "",
    @SerializedName("client_id")
    val clientId: String = "",
    @SerializedName("client_secret")
    val clientSecret: String = ""
)