package duke.deviluke.mangadexapp.data.repository.dataSource

import duke.deviluke.mangadexapp.data.model.AuthData
import duke.deviluke.mangadexapp.data.modelJson.ListMangaDataJson
import duke.deviluke.mangadexapp.data.modelJson.MangaCoverJson
import duke.deviluke.mangadexapp.data.modelJson.MangaDataJson
import duke.deviluke.mangadexapp.data.modelJson.TokenJson
import retrofit2.Response

interface MangaDataRemoteDataSource {

    suspend fun getRandomManga(): Response<MangaDataJson>

    suspend fun getLatestMangaList(limit: String, offset: String, latestUploadedChapter: String): Response<ListMangaDataJson>

    suspend fun getCoverPath(path: String): Response<MangaCoverJson>
}