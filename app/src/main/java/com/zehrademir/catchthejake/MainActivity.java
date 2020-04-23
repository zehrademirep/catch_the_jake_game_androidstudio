package com.zehrademir.catchthejake;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);

        imageView=findViewById(R.id.imageView);
        imageView=findViewById(R.id.imageView2);
        imageView=findViewById(R.id.imageView3);
        imageView=findViewById(R.id.imageView4);
        imageView=findViewById(R.id.imageView5);
        imageView=findViewById(R.id.imageView6);
        imageView=findViewById(R.id.imageView7);
        imageView=findViewById(R.id.imageView8);
imageArray=new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};
hideImages();

        score =0;
        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("Time: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
textView.setText("Time Off!");
handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent=getIntent();

                        finish();

                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Game Over.",Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();
            }
        }.start();

    }
    public void increaseScore(View view){
        score++;
        textView2.setText("Score: "+score);
    }
    public void hideImages(){
        handler=new Handler();
        runnable=new Runnable() {
public void run() {
    for (ImageView image : imageArray) {
        image.setVisibility(View.INVISIBLE);
    }

    Random random = new Random();
    int i = random.nextInt(9);
    imageArray[i].setVisibility(View.INVISIBLE);
    handler.postDelayed(this,1000);
}
        };
        handler.post(runnable);
    }
}
