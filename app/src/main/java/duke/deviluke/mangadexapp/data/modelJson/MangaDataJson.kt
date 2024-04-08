package duke.deviluke.mangadexapp.data.modelJson


import com.google.gson.annotations.SerializedName
import duke.deviluke.mangadexapp.data.model.MangaData

data class MangaDataJson(
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
            @SerializedName("title")
            val title: Title?,
            @SerializedName("altTitles")
            val altTitles: List<AltTitle?>?,
            @SerializedName("description")
            val description: Description?,
            @SerializedName("isLocked")
            val isLocked: Boolean?,
            @SerializedName("links")
            val links: Links?,
            @SerializedName("originalLanguage")
            val originalLanguage: String?,
            @SerializedName("lastVolume")
            val lastVolume: String?,
            @SerializedName("lastChapter")
            val lastChapter: String?,
            @SerializedName("publicationDemographic")
            val publicationDemographic: Any?,
            @SerializedName("status")
            val status: String?,
            @SerializedName("year")
            val year: Int?,
            @SerializedName("contentRating")
            val contentRating: String?,
            @SerializedName("tags")
            val tags: ArrayList<Tag?>?,
            @SerializedName("state")
            val state: String?,
            @SerializedName("chapterNumbersResetOnNewVolume")
            val chapterNumbersResetOnNewVolume: Boolean?,
            @SerializedName("createdAt")
            val createdAt: String?,
            @SerializedName("updatedAt")
            val updatedAt: String?,
            @SerializedName("version")
            val version: Int?,
            @SerializedName("availableTranslatedLanguages")
            val availableTranslatedLanguages: List<String?>?,
            @SerializedName("latestUploadedChapter")
            val latestUploadedChapter: String?
        ) {
            data class Title(
                @SerializedName("en")
                val en: String?
            )

            data class AltTitle(
                @SerializedName("zh")
                val zh: String?
            )

            data class Description(
                @SerializedName("en")
                val en: String?
            )

            data class Links(
                @SerializedName("al")
                val al: String?,
                @SerializedName("ap")
                val ap: String?
            )

            data class Tag(
                @SerializedName("id")
                val id: String?,
                @SerializedName("type")
                val type: String?,
                @SerializedName("attributes")
                val attributes: Attributes?,
                @SerializedName("relationships")
                val relationships: List<Any?>?
            ) {
                data class Attributes(
                    @SerializedName("name")
                    val name: Name?,
                    @SerializedName("description")
                    val description: List<Any?>?,
                    @SerializedName("group")
                    val group: String?,
                    @SerializedName("version")
                    val version: Int?
                ) {
                    data class Name(
                        @SerializedName("en")
                        val en: String?
                    )
                }
            }
        }

        data class Relationship(
            @SerializedName("id")
            val id: String?,
            @SerializedName("type")
            val type: String?
        )
    }
}

fun MangaDataJson.Data.Attributes.Tag.Attributes.Name.toModel(): MangaData.Data.Attributes.Tag.Attributes.Name =
    MangaData.Data.Attributes.Tag.Attributes.Name(
        en = en ?: ""
    )

fun MangaDataJson.Data.Attributes.Tag.Attributes.toModel(): MangaData.Data.Attributes.Tag.Attributes =
    MangaData.Data.Attributes.Tag.Attributes(
        name = name?.toModel() ?: MangaData.Data.Attributes.Tag.Attributes.Name()
    )

fun MangaDataJson.Data.Attributes.Tag.toModel(): MangaData.Data.Attributes.Tag =
    MangaData.Data.Attributes.Tag(
        attributes = attributes?.toModel() ?: MangaData.Data.Attributes.Tag.Attributes()
    )

fun MangaDataJson.Data.Attributes.Links.toModel(): MangaData.Data.Attributes.Links =
    MangaData.Data.Attributes.Links(
        al = al ?: "",
        ap = ap ?: ""
    )

fun MangaDataJson.Data.Attributes.Description.toModel(): MangaData.Data.Attributes.Description =
    MangaData.Data.Attributes.Description(
        en = en ?: ""
    )

fun MangaDataJson.Data.Attributes.Title.toModel(): MangaData.Data.Attributes.Title =
    MangaData.Data.Attributes.Title(
        en = en ?: ""
    )

fun MangaDataJson.Data.Attributes.toModel(): MangaData.Data.Attributes =
    MangaData.Data.Attributes(
        title = title?.toModel() ?: MangaData.Data.Attributes.Title(),
        description = description?.toModel() ?: MangaData.Data.Attributes.Description(),
        links = links?.toModel() ?: MangaData.Data.Attributes.Links(),
        lastVolume = lastVolume ?: "",
        lastChapter = lastChapter ?: "",
        status = status ?: "",
        year = year ?: 0,
        tags = tags?.filterNotNull()?.map {
            it.toModel()
        }?.let { ArrayList(it) } ?: arrayListOf(),
        createdAt = createdAt ?: "",
        updatedAt = updatedAt ?: ""
    )

fun MangaDataJson.Data.toModel(): MangaData.Data =
    MangaData.Data(
        id = id ?: "",
        attributes = attributes?.toModel() ?: MangaData.Data.Attributes(
            tags = arrayListOf()
        )
    )

fun MangaDataJson.toModel(): MangaData =
    MangaData(
        data = data?.toModel() ?: MangaData.Data()
    )