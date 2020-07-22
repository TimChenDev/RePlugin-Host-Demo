package com.devtimchentw.replugin_host_demo

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.devtimchentw.remote.OneListener
import com.qihoo360.replugin.RePlugin
import com.qihoo360.replugin.component.service.PluginServiceClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initEvent()
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindOneService()
    }

    private fun initEvent() {

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

        btn_start_one_service.setOnClickListener {
            Log.v(TAG, "btn_go_to_plugin_two clicked!")
            bindOneService()
        }
    }

    /**
     * 存放 One 的 instance
     */
    private var mOneListener: OneListener? = null

    /**
     * 保存 Binder 並且 cast to OneListener
     * 便可通知 PluginOne 的 oneListener 去做事情
     */
    private val mServiceConn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, binder: IBinder) {

            Log.v("AidlTest", "Host >> MainActivity >> mServiceConn.onServiceConnected()")

            // Cast binder to OneListener
            mOneListener = OneListener.Stub.asInterface(binder)

            try {
                // Service 綁定後直接通知 OneListener 進行更新 AppInfo
                mOneListener?.updateAppInfo()
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {
            mOneListener = null
        }
    }

    private var mIsBound = false

    /**
     * 設定 Intent 透過 PluginName and class, 將插件的 Service 拉起來執行
     * 並且於 mServiceConn 保存 Binder 並且 cast to OneListener
     * 便可通知 PluginOne 的 oneListener 去做事情
     */
    private fun bindOneService() {

        Log.v("AidlTest", "Host >> MainActivity >> bindOneService()")

        val intent = RePlugin.createIntent(
            "com.devtimchentw.replugin_pluginone_demo",
            "com.devtimchentw.replugin_pluginone_demo.OneService"
        )
        val bindService = PluginServiceClient.bindService(
            this,
            intent,
            mServiceConn,
            BIND_AUTO_CREATE
        )
        if (bindService)
            mIsBound = true
    }

    private fun unbindOneService() {
        if (!mIsBound) {
            return
        }
        PluginServiceClient.unbindService(this, mServiceConn)
        mIsBound = false
    }
}
