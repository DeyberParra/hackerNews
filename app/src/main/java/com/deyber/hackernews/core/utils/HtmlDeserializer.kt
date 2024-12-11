package com.deyber.hackernews.core.utils

import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonDeserializationContext
import java.lang.reflect.Type
import android.text.Html

class HtmlDeserializer : JsonDeserializer<String> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): String {
        val rawText = json.asString
        return Html.fromHtml(rawText, Html.FROM_HTML_MODE_LEGACY).toString()
    }
}
