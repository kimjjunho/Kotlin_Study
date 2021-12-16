package com.example.videokt

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView

class ViewVideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_video)

        val video : VideoView = findViewById(R.id.video)
        val uri : Uri = Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")

        video.setVideoURI(uri)
        video.setMediaController(android.widget.MediaController(this))
        video.requestFocus()

        video.setOnPreparedListener {
            video.start()
        }
    }
}