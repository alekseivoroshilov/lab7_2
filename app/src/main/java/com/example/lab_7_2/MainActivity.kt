package com.example.lab_7_2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var receiver: BroadcastReceiver

    companion object {
        const val broadcastActionKey = "com.example.lab_7_1.PIC_DOWNLOAD"
        const val broadcastMessageKey = "broadcastMessageKey"

        var instance: MainActivity? = null
    }

    class MainReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            instance!!.updateTextView(intent.getStringExtra(broadcastMessageKey)!!)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        instance = this
        receiver = MainReceiver()
        registerReceiver(receiver, IntentFilter(broadcastActionKey))

        findViewById<Button>(R.id.clear_button).setOnClickListener {
            updateTextView("")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        
        instance = null
        unregisterReceiver(receiver)
    }

    fun updateTextView(message: String) {
        findViewById<TextView>(R.id.message).text = message
    }
}
