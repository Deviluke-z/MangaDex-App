package duke.deviluke.mangadexapp.domain.usecases

import android.util.Log
import duke.deviluke.mangadexapp.MangaDexApplication
import duke.deviluke.mangadexapp.data.modelJson.MangaCoverJson
import duke.deviluke.mangadexapp.data.util.Resource
import duke.deviluke.mangadexapp.domain.repository.MangaDataRepository
import kotlinx.coroutines.flow.Flow

class GetMangaCoverPathUseCase(private val mangaDataRepository: MangaDataRepository) {

    suspend fun execute(path: String): Flow<Resource<MangaCoverJson>> {
        Log.d(MangaDexApplication.DEBUG_TAG, "GetMangaCoverPathUseCase: execute()")
        return mangaDataRepository.getCoverPath(path)
    }
}