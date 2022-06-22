package com.ych.debugtools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.DialogFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        //当为音量键出发
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            val clazz = Class.forName("com.ych.hidebugtools.DebugToolDialogFragment")
            val target: DialogFragment = clazz.getConstructor().newInstance() as DialogFragment
            target.show(supportFragmentManager,"debug_tool")
        }
        return super.onKeyDown(keyCode, event)
    }
}