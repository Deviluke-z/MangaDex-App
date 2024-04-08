package duke.deviluke.mangadexapp.data

import com.google.gson.Gson
import duke.deviluke.mangadexapp.data.modelJson.MangaDataJson
import duke.deviluke.mangadexapp.data.repository.dataSource.MangaDataRemoteDataSource
import duke.deviluke.mangadexapp.data.util.Resource
import duke.deviluke.mangadexapp.domain.repository.MangaDataRepository
import retrofit2.Response

class MangaDataRepositoryImpl(
    private val mangaDataRemoteDataSource: MangaDataRemoteDataSource
) : MangaDataRepository {

    override suspend fun getRandomManga(): Resource<MangaDataJson> {
        return responseToResource(mangaDataRemoteDataSource.getRandomManga())
    }

    fun responseToResource(response: Response<MangaDataJson>): Resource<MangaDataJson> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Failure(response.message())
    }
}