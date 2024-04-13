package duke.deviluke.mangadexapp.data.api

import duke.deviluke.mangadexapp.data.modelJson.ListMangaDataJson
import duke.deviluke.mangadexapp.data.modelJson.MangaCoverJson
import duke.deviluke.mangadexapp.data.modelJson.MangaDataJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MangaAPIService {

    @GET("/manga/random/")
    suspend fun getRandomManga(): Response<MangaDataJson>

    @GET("/manga")
    suspend fun getLatestMangaList(
        @Query("limit")
        limit: String,
        @Query("offset")
        offset: String,
        @Query("order[latestUploadedChapter]")
        latestUploadedChapter: String
    ): Response<ListMangaDataJson>

    @GET("/cover/")
    suspend fun getCoverPath(
        @Query("")
        path: String
    ): Response<MangaCoverJson>
}