package duke.deviluke.mangadexapp.data.model

import com.google.gson.annotations.SerializedName

class MangaCover(
    @SerializedName("data")
    val `data`: Data = Data()
) {
    data class Data(
        @SerializedName("attributes")
        val attributes: Attributes = Attributes()
    ) {
        data class Attributes(
            val fileName: String = "",
        )
    }
}