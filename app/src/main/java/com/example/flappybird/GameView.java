package com.example.flappybird;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Random;

public class GameView extends View {

    private Context c;
    private MediaPlayer mediaPlayer;

    private int viewWidth;
    private int viewHeight = 601;

    private int point = 0;

    private boolean flyTop = true;

    private float playerSpeed = 5f;

    private float playerY = -1;

    private int itemForPoint = 0;

    private float enemyX = 800;

    Random enemyRandom = new Random();

    private int enemyY = enemyRandom.nextInt(viewHeight - 600);
    private float enemySpeed = 3;

    public GameView(Context context, MediaPlayer mediaPlayer) {
        super(context);
        this.c = context;
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);

        //Значение для положения игрока по y
        if (playerY == -1)
            playerY = viewHeight/2.5f;

        //Текст с количеством очков
        Paint pointPaint = new Paint();
        pointPaint.setColor(Color.WHITE);
        pointPaint.setTextSize(55);
        canvas.drawText("Points: "+point, viewWidth-300, 90, pointPaint);

        //Игрок
        Bitmap player = BitmapFactory.decodeResource(getResources(), R.drawable.player);
        Paint playerPaint = new Paint();
        canvas.drawBitmap(player, 0, playerY, playerPaint);
        if(flyTop){
            playerY -= playerSpeed;
        }
        else{
            playerY += playerSpeed;
        }

        //Изменение полета при нажатии по канвасу
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                flyTop = !flyTop;
            }
        });

        //Добавление очков
        itemForPoint++;
        if(itemForPoint==60){
            point += 1;
            itemForPoint = 0;
        }

        //Контроль столкновений игрока с границами экрана
        if(playerY+190 > viewHeight)
            flyTop = true;
        if(playerY+15 < 0)
            flyTop = false;

        //Противник
        Bitmap enemy0 = BitmapFactory.decodeResource(getResources(), R.drawable.enemy);
        Paint enemyPaint = new Paint();
        canvas.drawBitmap(enemy0, enemyX, enemyY, enemyPaint);

        //Возвращение противника, если он за пределами экрана
        if(enemyX + 550 < 0){
            enemyX = 890;
            enemyY = enemyRandom.nextInt(viewHeight - 600);
        }

        //Проверка на столкновение
        if(new Rect(0, (int)playerY, 80*2+100, (int)playerY + 120*2+100).intersect(new Rect((int)enemyX, enemyY, (int)enemyX + 170*2+100, enemyY+200*2+100))){
            Intent toMenu = new Intent(c, MenuActivity.class);
            ((MainActivity) c).startActivity(toMenu);
             mediaPlayer.stop();
             this.setWillNotDraw(true);
        }


        //Перемещение противника
        enemyX -= enemySpeed;

        invalidate();
    }
}
