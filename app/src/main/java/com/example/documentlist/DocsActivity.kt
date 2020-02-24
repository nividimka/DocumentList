package com.example.documentlist

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import kotlinx.android.synthetic.main.docs_layout.*

class DocsActivity : AppCompatActivity(){
    val vkDocsAdapter = DocsRecyclerViewAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.docs_layout)
        recycler_view.adapter = vkDocsAdapter
        VK.execute(VKDocsRequest(), object: VKApiCallback<List<VKDoc>> {
            override fun success(result: List<VKDoc>) {
                vkDocsAdapter.updateItems(result)
            }
            override fun fail(error: VKApiExecutionException) {
                Log.e("usersException",error.toString())
            }
        })
    }
}