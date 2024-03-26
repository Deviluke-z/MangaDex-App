package duke.deviluke.mangadexapp.domain.repository

import duke.deviluke.mangadexapp.data.model.AuthData
import duke.deviluke.mangadexapp.data.modelJson.TokenJson
import duke.deviluke.mangadexapp.data.util.Resource

interface AuthDataRepository {

    suspend fun postAuthData(authData: AuthData): Resource<TokenJson>
}