package com.example.flappybird;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    //Эл. разметки
    ConstraintLayout layout;
    Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Инициализация эл. разметки
        layout = findViewById(R.id.menu_layout);
        play = findViewById(R.id.play_btn);

        //Заливка фона
        layout.setBackgroundColor(Color.BLUE);

        //Переход в игру
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toGame = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(toGame);
            }
        });


    }
}