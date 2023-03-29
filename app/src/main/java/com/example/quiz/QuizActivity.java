package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {
    private TextView questions;
    private TextView question;
    private AppCompatButton option1,option2,option3,option4;
    private AppCompatButton nextBtn;
    private Timer quizTimer;
    private int seconds =0;
    private int totalTimeInMins=1;
    private  final List<QuestionsLIst> questionsLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //получение данных с предыдущего активити
        final ImageView backBtn= findViewById(R.id.backBtn);
        final TextView timer = findViewById(R.id.timer);
        final TextView selectedTopicName =findViewById(R.id.SelectedtopicName);
        questions =findViewById(R.id.questions);
        question=findViewById(R.id.question);
        option1 =findViewById(R.id.option1);
        option2 =findViewById(R.id.option2);
        option3 =findViewById(R.id.option3);
        option4 =findViewById(R.id.option4);
        nextBtn = findViewById(R.id.nextBtn);


        final String getSelectedTopic=getIntent().getStringExtra("selectedTopic");
        selectedTopicName.setText(getSelectedTopic);//получение названия викторины сверху из имени
        StartTimer(timer);//запуск таймера
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//возврат по кнопке назад
                backBtn.setBackgroundResource(R.drawable.round_back_green_30);//изменение цвета кнопки по нажатию   
                quizTimer.purge();
                quizTimer.cancel();
                startActivity(new Intent(QuizActivity.this, MainActivity.class));
                finish();
            }
        });
    }
    private  void StartTimer (TextView timerTextView){//метод для таймера в углу
    quizTimer = new Timer();
    quizTimer.scheduleAtFixedRate(new TimerTask() {
    @Override
    public void run() {
        if (seconds == 0) {
            totalTimeInMins--;// уменьшение минут на 1 сек
            seconds = 59;
        }
        else if (seconds == 0 && totalTimeInMins == 0) {
            quizTimer.purge();
            quizTimer.cancel();//окончание времени работы таймера
            //сообщение об окончании времени
            Toast.makeText(QuizActivity.this, "Время вышло", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(QuizActivity.this, QuizResults.class);
            intent.putExtra("correct", getCorrentAnswer());
            //вызываем новое намерение, вкладываем в него количество корректных ответов
            intent.putExtra("incorrect", getInCorrentAnswer());//получение некорректных ответов
            startActivity(intent);
            finish();
        }
        else {
            seconds--;//уменьшение секунд
        }
        //метод для корректного отображения таймера
        runOnUiThread(new Runnable() {
            @Override
            public void run() {//перевод в строковые метод для двухразрядноо отображения 09 08 07 и тд
              String finalMinutes = String.valueOf(totalTimeInMins);
              String finalSeconds =String.valueOf(seconds);
              if (finalMinutes.length()==1){
                    finalMinutes= "0"+finalMinutes;
              }
              if (finalSeconds.length()==1) {
              finalSeconds = "0"+finalSeconds;
              }
                timerTextView.setText(finalMinutes+":" +finalSeconds);
            }
        });//запуск нового потока
    }//внимательно БЛЯТЬ на эти скобки
    },1000,1000);//отвечает за скорость таймера
    }
    private  int getCorrentAnswer () {//получение корректного ответа
        int correctAnswer =0;
    for (int i =0; i<questionsLists.size();i++) {//повтор до тех пор пока не равен размеру листа
    final  String getUserSelectedAnswer=questionsLists.get(i).getUserSelectedAnswer();
    final  String getAnswer =questionsLists.get(i).getAnswer();
    if(getUserSelectedAnswer.equals(getAnswer)){
        correctAnswer ++;//если ответ правильный увеличивает кол-во правильных ответов на 1
    }
    }
        return correctAnswer;
            }
    private  int getInCorrentAnswer (){//получение некорректного ответа
        int correctAnswer =0;
        for (int i =0; i<questionsLists.size();i++) {//повтор до тех пор пока не равен размеру листа
            final  String getUserSelectedAnswer=questionsLists.get(i).getUserSelectedAnswer();
            final  String getAnswer =questionsLists.get(i).getAnswer();
            if(!getUserSelectedAnswer.equals(getAnswer)){//если ответ неправильный
                correctAnswer ++;//если ответ не правильный увеличивает кол-во правильных ответов на 1
            }
        }
        return correctAnswer;
    }

    @Override
    public void onBackPressed() {//возврат назад
        quizTimer.purge();
        quizTimer.cancel();
        startActivity(new Intent(QuizActivity.this, MainActivity.class));
        finish();
    }
}