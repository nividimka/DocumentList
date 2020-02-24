package com.example.documentlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(!VK.isLoggedIn()) {
            VK.login(this, listOf(VKScope.DOCS))
        }else{
            startActivity(Intent(this,DocsActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object: VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                startActivity(Intent(this@MainActivity,DocsActivity::class.java))
                finish()
            }

            override fun onLoginFailed(errorCode: Int) {
                finish()
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
