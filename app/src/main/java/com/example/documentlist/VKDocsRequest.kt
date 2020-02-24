package com.example.documentlist

import android.util.Log
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class VKDocsRequest() : VKRequest<List<VKDoc>>("docs.get") {
    init {
        addParam("return_tags", "1")
    }

    override fun parse(r: JSONObject): List<VKDoc> {
        val response = r.getJSONObject("response")
        Log.e("result",response.toString())
        val result = ArrayList<VKDoc>()
        val items = response.getJSONArray("items")
        for (i in 0 until items.length()) {
            result.add(VKDoc.parse(items.getJSONObject(i)))
        }
        return result
    }
}