package duke.deviluke.mangadexapp.data

import duke.deviluke.mangadexapp.data.model.AuthData
import duke.deviluke.mangadexapp.data.modelJson.TokenJson
import duke.deviluke.mangadexapp.data.repository.dataSource.AuthRemoteDataSource
import duke.deviluke.mangadexapp.data.util.Resource
import duke.deviluke.mangadexapp.domain.repository.AuthDataRepository
import retrofit2.Response

class AuthDataRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthDataRepository {

    override suspend fun postAuthData(authData: AuthData): Resource<TokenJson> {
        return responseToResource(authRemoteDataSource.postAuthData(authData))
    }

    fun responseToResource(response: Response<TokenJson>): Resource<TokenJson> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Failure(response.message())
    }
}