package com.example.hyu13.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private TextView  mQuestionTextView;

    private Question[] mQuestionBank = new Question[]{
      new Question(R.string.question1, true),
      new Question(R.string.question2, true),
      new Question(R.string.question3, false),
      new Question(R.string.question4, false),
      new Question(R.string.question5, true),
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mTrueButton = (Button) findViewById(R.id.trueButton);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(true);
            }
        });
        mFalseButton = (Button) findViewById(R.id.falseButton);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.nextButton);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mPreviousButton = (Button) findViewById(R.id.previousButton);
        mPreviousButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                    if(mCurrentIndex == 0){
                        Toast.makeText(getApplicationContext(),"This is the First Question",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                        updateQuestion();
                    }
                }
            }
        );
        updateQuestion();

    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if(userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        }
        else{
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId,Toast.LENGTH_SHORT).show();

    }

}
