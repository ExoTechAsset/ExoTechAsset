import com.fasterxml.jackson.annotation.JsonProperty

class ExportRequest(private var filePath: String = ""){
    fun getFilePath(): String {
        return filePath
    }
}



