package com.badschizoids.ciphergame.tools

class Chat(
        var name: String,
        var stringsUser : Array<StoryMessage>,
        var stringsCompany: Array<StoryMessage>
){
    fun getPositionInUserString(string: String): Int {
        var position = -1
        for (i in string.indices){
            if (stringsUser[i].message == string){
                position = i
                break
            }
        }
        return position
    }
    companion object{
        val nameStart = "Вступление"
        val nameGetHelps = "Получение справок"
        val nameFisrtWork = "Первая работа"
    }
}