package com.teamxenox.telegramapi.models

import com.google.gson.annotations.SerializedName


data class SendMessageRequest(
        @SerializedName("chat_id")
        val chatId: Long, // to
        @SerializedName("text")
        val text: String, // This is some message
        @SerializedName("disable_web_page_preview")
        val isDisableWebPagePreview: Boolean? = true,
        @SerializedName("parse_mode")
        val parseMode: String? = "HTML",
        @SerializedName("reply_to_message_id")
        val replyMsgId: Long? = null,
        @SerializedName("reply_markup")
        val replyMarkup: ReplyMarkup? = null
) {
    data class ReplyMarkup(
            @SerializedName("inline_keyboard")
            val inlineKeyboard: List<List<InlineButton>>
    )

    data class InlineButton(
            @SerializedName("text")
            val text: String, // ✅ Relevant
            @SerializedName("callback_data")
            val callbackData: String // r123
    ) {
        class ByteOverflowException(message: String?) : Throwable(message)

        init {
            val byteSize = callbackData.toByteArray().size
            if (byteSize > 64) {
                throw ByteOverflowException(
                        "Callback data exceeded `$callbackData`"
                )
            }
        }
    }
}