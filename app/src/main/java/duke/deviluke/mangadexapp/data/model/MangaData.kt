package duke.deviluke.mangadexapp.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class MangaData(
    @SerializedName("data")
    val `data`: Data = Data(
        "",
        Data.Attributes(tags = arrayListOf())
    )
) {
    data class Data(
        @SerializedName("id")
        val id: String = "",
        @SerializedName("attributes")
        val attributes: Attributes = Attributes(
            Attributes.Title(),
            Attributes.Description(),
            Attributes.Links(),
            "",
            "",
            "",
            0,
            arrayListOf(),
            "",
            ""
        )
    ) {
        data class Attributes(
            @SerializedName("title")
            val title: Title = Title(""),
            @SerializedName("description")
            val description: Description = Description(""),
            @SerializedName("links")
            val links: Links = Links("", ""),
            @SerializedName("lastVolume")
            val lastVolume: String = "",
            @SerializedName("lastChapter")
            val lastChapter: String = "",
            @SerializedName("status")
            val status: String = "",
            @SerializedName("year")
            val year: Int = 0,
            @SerializedName("tags")
            val tags: ArrayList<Tag>,
            @SerializedName("createdAt")
            val createdAt: String = "",
            @SerializedName("updatedAt")
            val updatedAt: String = ""
        ) {
            data class Title(
                @SerializedName("en")
                val en: String = ""
            )

            data class Description(
                @SerializedName("en")
                val en: String = ""
            )

            data class Links(
                @SerializedName("al")
                val al: String = "",
                @SerializedName("ap")
                val ap: String = ""
            )

            data class Tag(
                @SerializedName("attributes")
                val attributes: Attributes = Attributes()
            ) {
                data class Attributes(
                    @SerializedName("name")
                    val name: Name = Name("")
                ) {
                    data class Name(
                        @SerializedName("en")
                        val en: String = ""
                    )
                }
            }
        }
    }
}

