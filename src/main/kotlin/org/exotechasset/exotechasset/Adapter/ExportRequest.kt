import com.fasterxml.jackson.annotation.JsonProperty
import org.exotechasset.exotechasset.usecase.AssetList

class ExportRequest(private var filePath: String = ""){
    fun getFilePath(): String {
        return filePath
    }
}



