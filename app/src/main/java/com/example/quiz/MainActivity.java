package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private  String selectedTopic = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout Moscow = findViewById(R.id.Moscow);
        final LinearLayout Penza = findViewById(R.id.Penza);
        final LinearLayout Russia = findViewById(R.id.Russia);
        final LinearLayout Spb = findViewById(R.id.Spb);
        final Button startQuizButton=findViewById(R.id.startQuizButton);

        Moscow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "Moscow";
                Moscow.setBackgroundResource(R.drawable.round_back_white_stroke10);//ссылка на файл с внешним видом кнопкки
                Russia.setBackgroundResource(R.drawable.round_back_white10);
                Penza.setBackgroundResource(R.drawable.round_back_white10);
                Spb.setBackgroundResource(R.drawable.round_back_white10);
            }
        });
        Penza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "Penza";
                Penza.setBackgroundResource(R.drawable.round_back_white_stroke10);//ссылка на файл с внешним видом кнопкки
                Russia.setBackgroundResource(R.drawable.round_back_white10);
                Moscow.setBackgroundResource(R.drawable.round_back_white10);
                Spb.setBackgroundResource(R.drawable.round_back_white10);
            }
        });
        Russia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "Russia";
                Russia.setBackgroundResource(R.drawable.round_back_white_stroke10);//ссылка на файл с внешним видом кнопкки
                Moscow.setBackgroundResource(R.drawable.round_back_white10);
                Penza.setBackgroundResource(R.drawable.round_back_white10);
                Spb.setBackgroundResource(R.drawable.round_back_white10);
            }
        });
        Spb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopic = "Spb";
                Spb.setBackgroundResource(R.drawable.round_back_white_stroke10);//ссылка на файл с внешним видом кнопкки
                Russia.setBackgroundResource(R.drawable.round_back_white10);
                Penza.setBackgroundResource(R.drawable.round_back_white10);
                Moscow.setBackgroundResource(R.drawable.round_back_white10);
            }
        });
        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTopic.isEmpty()){
                    Toast.makeText(MainActivity.this,"Тема не выбрана",Toast.LENGTH_SHORT).show();
                }
                else {
                    startQuizButton.setBackgroundResource(R.drawable.round_back_white_stroke10);
                    Intent i = new Intent(MainActivity.this, QuizActivity.class);
                    i.putExtra("selectedTopic", selectedTopic);// передаем введеные значения на новой активити
                    startActivity(i);
                    finish();
                }}
        });
    }
    }