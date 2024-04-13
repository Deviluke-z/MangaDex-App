package duke.deviluke.mangadexapp.data.modelJson


import com.google.gson.annotations.SerializedName
import duke.deviluke.mangadexapp.data.model.MangaCover

data class MangaCoverJson(
    @SerializedName("result")
    val result: String?,
    @SerializedName("response")
    val response: String?,
    @SerializedName("data")
    val `data`: Data?
) {
    data class Data(
        @SerializedName("id")
        val id: String?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("attributes")
        val attributes: Attributes?,
        @SerializedName("relationships")
        val relationships: List<Relationship?>?
    ) {
        data class Attributes(
            @SerializedName("description")
            val description: String?,
            @SerializedName("volume")
            val volume: String?,
            @SerializedName("fileName")
            val fileName: String?,
            @SerializedName("locale")
            val locale: String?,
            @SerializedName("createdAt")
            val createdAt: String?,
            @SerializedName("updatedAt")
            val updatedAt: String?,
            @SerializedName("version")
            val version: Int?
        )

        data class Relationship(
            @SerializedName("id")
            val id: String?,
            @SerializedName("type")
            val type: String?
        )
    }
}

fun MangaCoverJson.Data.Attributes.toModel(): MangaCover.Data.Attributes =
    MangaCover.Data.Attributes(
        fileName = fileName ?: ""
    )

fun MangaCoverJson.Data.toModel() : MangaCover.Data =
    MangaCover.Data(
        attributes = attributes?.toModel() ?: MangaCover.Data.Attributes()
    )

fun MangaCoverJson.toModel() : MangaCover =
    MangaCover(
        data = data?.toModel() ?: MangaCover.Data()
    )