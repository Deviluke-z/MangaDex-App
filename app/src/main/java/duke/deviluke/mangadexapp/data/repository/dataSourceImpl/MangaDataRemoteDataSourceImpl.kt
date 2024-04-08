package duke.deviluke.mangadexapp.data.repository.dataSourceImpl

import duke.deviluke.mangadexapp.data.api.MangaAPIService
import duke.deviluke.mangadexapp.data.modelJson.MangaDataJson
import duke.deviluke.mangadexapp.data.repository.dataSource.MangaDataRemoteDataSource
import retrofit2.Response

class MangaDataRemoteDataSourceImpl(
    private val mangaAPIService: MangaAPIService
): MangaDataRemoteDataSource {
    override suspend fun getRandomManga(): Response<MangaDataJson> {
        return mangaAPIService.getRandomManga()
    }
}