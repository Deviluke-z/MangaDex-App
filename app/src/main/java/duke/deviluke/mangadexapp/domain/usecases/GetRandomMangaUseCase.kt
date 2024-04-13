package duke.deviluke.mangadexapp.domain.usecases

import android.util.Log
import duke.deviluke.mangadexapp.MangaDexApplication.Companion.DEBUG_TAG
import duke.deviluke.mangadexapp.data.modelJson.MangaDataJson
import duke.deviluke.mangadexapp.data.util.Resource
import duke.deviluke.mangadexapp.domain.repository.MangaDataRepository
import kotlinx.coroutines.flow.Flow

class GetRandomMangaUseCase(private val mangaDataRepository: MangaDataRepository) {

    suspend fun execute(): Flow<Resource<MangaDataJson>> {
        Log.d(DEBUG_TAG, "GetRandomMangaUseCase: execute()")
        return mangaDataRepository.getRandomManga()
    }
}