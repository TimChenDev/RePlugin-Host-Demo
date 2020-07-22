package com.devtimchentw.replugin_host_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        }

        btn_go_to_plugin_two.setOnClickListener {
            Log.v(TAG, "btn_go_to_plugin_two clicked!")
        }
    }
}
