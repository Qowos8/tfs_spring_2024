package com.example.homework_2

data class EmojiNCU(
    val name: String,
    val code: Int
) {
    fun getCodeString() = String(Character.toChars(code))
}

val emojiSetNCU = listOf(

// Smileys & Emotion
    EmojiNCU("grinning", 0x1f600),
    EmojiNCU("smiley", 0x1f603),
    EmojiNCU("big_smile", 0x1f604),
    EmojiNCU("grinning_face_with_smiling_eyes", 0x1f601),
    EmojiNCU("laughing", 0x1f606),
    EmojiNCU("sweat_smile", 0x1f605),
    EmojiNCU("rolling_on_the_floor_laughing", 0x1f923),
    EmojiNCU("joy", 0x1f602),
    EmojiNCU("smile", 0x1f642),
    EmojiNCU("upside_down", 0x1f643),
    EmojiNCU("wink", 0x1f609),
    EmojiNCU("blush", 0x1f60a),
    EmojiNCU("innocent", 0x1f607),
    EmojiNCU("heart_eyes", 0x1f60d),
    EmojiNCU("heart_kiss", 0x1f618),
    EmojiNCU("kiss", 0x1f617),
    EmojiNCU("smiling_face", 0x263a),
    EmojiNCU("kiss_with_blush", 0x1f61a),
    EmojiNCU("kiss_smiling_eyes", 0x1f619),
    EmojiNCU("yum", 0x1f60b),
    EmojiNCU("stuck_out_tongue", 0x1f61b),
    EmojiNCU("stuck_out_tongue_wink", 0x1f61c),
    EmojiNCU("stuck_out_tongue_closed_eyes", 0x1f61d),
    EmojiNCU("money_face", 0x1f911),
)