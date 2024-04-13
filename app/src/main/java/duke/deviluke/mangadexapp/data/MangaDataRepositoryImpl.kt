package duke.deviluke.mangadexapp.data

import duke.deviluke.mangadexapp.data.modelJson.ListMangaDataJson
import duke.deviluke.mangadexapp.data.modelJson.MangaCoverJson
import duke.deviluke.mangadexapp.data.modelJson.MangaDataJson
import duke.deviluke.mangadexapp.data.repository.dataSource.MangaDataRemoteDataSource
import duke.deviluke.mangadexapp.data.util.Resource
import duke.deviluke.mangadexapp.domain.repository.MangaDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class MangaDataRepositoryImpl(
    private val mangaDataRemoteDataSource: MangaDataRemoteDataSource
) : MangaDataRepository {

    override suspend fun getRandomManga(): Flow<Resource<MangaDataJson>> = flow {
        emit(responseToMangaResource(mangaDataRemoteDataSource.getRandomManga()))
    }

    override suspend fun getLatestMangaList(
        limit: String,
        offset: String,
        latestUploadedChapter: String
    ): Flow<Resource<ListMangaDataJson>> = flow {
        emit(
            responseToMangaListResource(
                mangaDataRemoteDataSource.getLatestMangaList(
                    limit,
                    offset,
                    latestUploadedChapter
                )
            )
        )
    }

    override suspend fun getCoverPath(path: String): Flow<Resource<MangaCoverJson>> = flow {
        emit(
            responseToMangaCoverPathResource(mangaDataRemoteDataSource.getCoverPath(path))
        )
    }

    private fun responseToMangaResource(response: Response<MangaDataJson>): Resource<MangaDataJson> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Failure(response.message())
    }

    private fun responseToMangaListResource(response: Response<ListMangaDataJson>): Resource<ListMangaDataJson> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Failure(response.message())
    }

    private fun responseToMangaCoverPathResource(response: Response<MangaCoverJson>): Resource<MangaCoverJson> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Failure(response.message())
    }
}