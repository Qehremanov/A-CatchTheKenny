package com.aliismayilov.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Runnable runnable;
    Handler handler;
    CountDownTimer countDownTimer;
    TextView textTime;
    TextView textScore;
    int score, randNumber;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textTime = findViewById(R.id.time);
        textScore=findViewById(R.id.score);
        score = 0;
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageArray = new ImageView[] {imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        hideImages();

        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textTime.setText("Time: "+millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                textTime.setText("Time: OFF");
                handler.removeCallbacks(runnable);

                for ( ImageView image : imageArray ){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart game?");

                alert.setPositiveButton("Yes", (dialog, which) -> {
                    textScore.setText("Score: 0");
                    score = 0;
                    hideImages();
                    countDownTimer.start();
                });

                alert.setNegativeButton("No", (dialog, which) -> {
                    Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_LONG).show();
                });
                alert.setCancelable(false);
                alert.show();
            }
        };
        countDownTimer.start();
    }

    private void hideImages() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for ( ImageView image : imageArray ){
                    image.setVisibility(View.INVISIBLE);
                }
                randNumber = (int) (Math.random()*9);
                imageArray[randNumber].setVisibility(View.VISIBLE);
                handler.postDelayed(runnable, 400);
            }
        };
        handler.post(runnable);

    }

    public void increaseScore ( View view ) {
        score++;
        textScore.setText("Score: "+score);
    }

}