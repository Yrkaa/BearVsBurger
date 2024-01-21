package com.example.flappybird;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

    }
}