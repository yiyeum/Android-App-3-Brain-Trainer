package com.kateyeeum.braintrainer;

import android.media.Image;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RelativeLayout gameLayout;
    TextView sumTextView;
    TextView timerTextView;
    TextView scoreTextView;
    TextView resultTextView;
    TextView welcomeTextView;
    TextView welcome2TextView;
    ImageView brainImage;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    ArrayList<String> messages = new ArrayList<String>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    final int numberOfButtons = 4;
    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playButton;
    Random rand = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = (Button) findViewById(R.id.goButton);
        gameLayout = (RelativeLayout) findViewById(R.id.gameLayout);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playButton = (Button) findViewById(R.id.playButton);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
        welcome2TextView = (TextView) findViewById(R.id.welcome2TextView);
        brainImage = (ImageView) findViewById(R.id.brainImageView);

    }

    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        welcomeTextView.setVisibility(View.INVISIBLE);
        welcome2TextView.setVisibility(View.INVISIBLE);
        brainImage.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playGame(playButton);
    }

    public void generateQuestion(){

        // two random integers

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        // sum of two random integers

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        // locate a+b on the button

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for(int i = 0; i < numberOfButtons; i++){

            if(i == locationOfCorrectAnswer){

                answers.add(a+b);

            } // if

            else{

                incorrectAnswer = rand.nextInt(41);

                while(incorrectAnswer == a+b){

                    incorrectAnswer = rand.nextInt(41);

                } // while

                answers.add(incorrectAnswer);

            } // else

        } // for


        // set text on the buttons from the ArrayList

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }

    public void playGame(View view){

        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        // Reset all the text view + run generateQuestion()

        timerTextView.setText("0s");
        scoreTextView.setText("0/0");
        score = 0;
        numberOfQuestions = 0;
        resultTextView.setText("");
        playButton.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30000,1000){

            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf(l / 1000) + "s");

            }

            @Override
            public void onFinish() {

                timerTextView.setText("0s");
                playButton.setVisibility(View.VISIBLE);
                resultTextView.setText("Your score is " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                playButton.setText("Try Again!");

            }
        }.start();

    }

    public void chooseAnswer(View view){

        messages.add("Awesome!");
        messages.add("Great!");
        messages.add("Correct!");
        messages.add("Perfect!");

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){

            int i = rand.nextInt(5);
            resultTextView.setText(messages.get(i));
            score++;

        } // if

        else{

            resultTextView.setText("Wrong!");

        } // else

        numberOfQuestions++;
        scoreTextView.setText(score + "/" + numberOfQuestions);
        generateQuestion();

    }


}
