package org.exotechasset.exotechasset.useCase

import java.io.BufferedWriter
import java.nio.charset.StandardCharsets
import java.io.File
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.Paths

// TODO 未來會把實作依照依賴反轉原則抽離到FrameworkDriver層
// TODO Composite Asset
class AssetListFile() {

    private var filePath: String = ""
    private var content: String = ""

    // 次要構造函數
    constructor(filePath: String) : this() {
        this.filePath = filePath
    }

    init {
        // 這裡可以放置一些共同的初始化邏輯，如果需要的話
    }

    private fun validateCsvContent(content: String): Boolean {
        val expectedHeader = "id, status, assignee, auditDate, location, changelog"
        val lines = content.lines()
        return lines.isNotEmpty() && lines[0] == expectedHeader
    }

    public fun write(result: String){
        if (!validateCsvContent(result)) {
            throw IllegalArgumentException("Invalid CSV header. Expected: id,status,assignee,auditDate,location,changelog")
        }

        content = result
        if(filePath == ""){
            return
        }

        val file = File(filePath)
        // Create a FileWriter to write to the file
        if(!file.exists()){
            file.createNewFile()
        }

        // Write content to the file
        file.writeText(content)
    }

    public fun readCsv(){
        val bytes = Files.readAllBytes(Paths.get(filePath))
        val readContent = String(bytes, StandardCharsets.UTF_8)
        if (!validateCsvContent(readContent)) {
            throw IllegalArgumentException("Invalid CSV header. Expected: id,status,assignee,auditDate,location,changelog")
        }
        content = readContent
    }

    public fun getContent():String{
        return content
    }
}