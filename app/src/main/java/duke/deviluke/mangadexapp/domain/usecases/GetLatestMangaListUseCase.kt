package duke.deviluke.mangadexapp.domain.usecases

import android.util.Log
import duke.deviluke.mangadexapp.MangaDexApplication
import duke.deviluke.mangadexapp.data.modelJson.ListMangaDataJson
import duke.deviluke.mangadexapp.data.util.Resource
import duke.deviluke.mangadexapp.domain.repository.MangaDataRepository
import kotlinx.coroutines.flow.Flow

class GetLatestMangaListUseCase(private val mangaDataRepository: MangaDataRepository) {

    suspend fun execute(): Flow<Resource<ListMangaDataJson>> {
        Log.d(MangaDexApplication.DEBUG_TAG, "GetLatestMangaListUseCase: execute()")
        return mangaDataRepository.getLatestMangaList("10", "0", "desc")
    }
}