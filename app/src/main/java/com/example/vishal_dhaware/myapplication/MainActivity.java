package com.example.vishal_dhaware.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView textView;
  Button button;
    private final int REQ_CODE_SPEACH_INPUT=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desing);
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VoiceSpeeck();
            }

            private void VoiceSpeeck() {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);//prfome an action for speech
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);//1)sport multiple lungu and mantent lug free form
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());// using defult system language
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi speak something");//passing addition value and display addition massage
                try {
                    startActivityForResult(intent, REQ_CODE_SPEACH_INPUT);// we pass object of intent in startactivity for reseat, startactivity is method is follodby onactivity result
                } catch (ActivityNotFoundException e){

                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQ_CODE_SPEACH_INPUT:{
                if (resultCode == RESULT_OK && null!= data){

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textView.setText(result.get(0));

                }
                break;
            }
        }
    }
}
