package duke.deviluke.mangadexapp.domain.repository

import duke.deviluke.mangadexapp.data.modelJson.MangaDataJson
import duke.deviluke.mangadexapp.data.util.Resource

interface MangaDataRepository {

    suspend fun getRandomManga(): Resource<MangaDataJson>
}