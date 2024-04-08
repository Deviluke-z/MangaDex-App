package duke.deviluke.mangadexapp.data.api

import duke.deviluke.mangadexapp.data.modelJson.MangaDataJson
import retrofit2.Response
import retrofit2.http.GET

interface MangaAPIService {

    @GET("/manga/random/")
    suspend fun getRandomManga(): Response<MangaDataJson>
}