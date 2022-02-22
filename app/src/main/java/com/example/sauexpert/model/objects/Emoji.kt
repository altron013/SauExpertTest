package com.example.sauexpert.model.objects

object Emoji {

    val grinningFace = getEmojiByUnicode(0x1F600)
    val smilingFace = getEmojiByUnicode(0x1F60A)
    val slightlySmilingFace = getEmojiByUnicode(0x1F642)
    val worriedFace = getEmojiByUnicode(0x1F61F)
    val headBandageFace = getEmojiByUnicode(0x1F915)

    fun getEmojiByUnicode(unicode: Int): String {
        return String(Character.toChars(unicode))
    }

}