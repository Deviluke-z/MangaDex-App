package duke.deviluke.mangadexapp.domain.repository

import duke.deviluke.mangadexapp.data.modelJson.ListMangaDataJson
import duke.deviluke.mangadexapp.data.modelJson.MangaCoverJson
import duke.deviluke.mangadexapp.data.modelJson.MangaDataJson
import duke.deviluke.mangadexapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface MangaDataRepository {

    suspend fun getRandomManga(): Flow<Resource<MangaDataJson>>

    suspend fun getLatestMangaList(
        limit: String,
        offset: String,
        latestUploadedChapter: String
    ): Flow<Resource<ListMangaDataJson>>

    suspend fun getCoverPath(
        path: String
    ): Flow<Resource<MangaCoverJson>>
}