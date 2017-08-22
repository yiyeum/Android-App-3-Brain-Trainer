package com.kateyeeum.braintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    RelativeLayout gameLayout;
    TextView sumTextView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    final int numberOfButtons = 4;
    Button button0;
    Button button1;
    Button button2;
    Button button3;


    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
    }

    public void generateQuestion(View view){

        // two random integers

        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        // sum of two random integers

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        // locate a+b on the button

        locationOfCorrectAnswer = rand.nextInt(4);

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

    }
}
