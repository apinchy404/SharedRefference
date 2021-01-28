package com.example.sharedrefference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtscore,txtHighScore;
    Button btnPlay,btnReset;

//    declare shared pref obj
    SharedPreferences sp;
//    for edit shared pref data
    SharedPreferences.Editor editor;

//    own package name.game
     private final String SP_NAME="com.example.sharedrefference.game";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtscore=findViewById(R.id.txt_score);
        txtHighScore=findViewById(R.id.txt_high_score);
        btnPlay=findViewById(R.id.btn_play);
        btnReset=findViewById(R.id.btn_reset);


//        open shared pref file with private mode
        sp=getSharedPreferences(SP_NAME,MODE_PRIVATE);
        editor=sp.edit();

//        get value from shared pref
        int highScore=sp.getInt("high_score",0);

        txtHighScore.setText("High Score : "+highScore);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random random=new Random();
                int score = random.nextInt(9999);
                txtscore.setText(String.valueOf(score));

                int getSaveScore=sp.getInt("high_score",0);
                if (score>getSaveScore)
                {
                    txtHighScore.setText("High Score :"+score);
//                    put value
                    editor.putInt("high_score",score);
//                    save value in shared pref
                    editor.apply();
                }

            }
        });




        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putInt("high_score",0);
                        editor.apply();
                        txtHighScore.setText("High Score:0");
                        txtscore.setText("0");
            }
        });

    }
}