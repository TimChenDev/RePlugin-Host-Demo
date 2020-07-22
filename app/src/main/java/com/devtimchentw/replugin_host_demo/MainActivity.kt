package com.devtimchentw.replugin_host_demo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.qihoo360.replugin.RePlugin
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_go_to_plugin_one.setOnClickListener {
            Log.v(TAG, "btn_go_to_plugin_one clicked!")
            RePlugin.startActivity(
                this@MainActivity,
                Intent(),
                "app-debug", // 這裡使用 jar 的檔名或者 plugin 的 packageName 都可以
                "com.devtimchentw.replugin_pluginone_demo.MainActivity"
            )
        }

        btn_go_to_plugin_two.setOnClickListener {
            Log.v(TAG, "btn_go_to_plugin_two clicked!")
            RePlugin.startActivity(
                this@MainActivity,
                Intent(),
                "app-debug2", // 這裡使用 jar 的檔名或者 plugin 的 packageName 都可以
                "com.devtimchentw.replugin_plugintwo_demo.MainActivity"
            )
        }
    }
}
