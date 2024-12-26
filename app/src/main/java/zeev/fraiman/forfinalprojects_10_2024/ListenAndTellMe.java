package zeev.fraiman.forfinalprojects_10_2024;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class ListenAndTellMe extends AppCompatActivity {

    Context context;
    TextView tvListen, tvRecord;
    Button bStart, bSpeachToText;
    CountDownTimer cdt;
    String[] stListen;
    int n=5, tikTime=1000, i=0;
    TextToSpeech tts;
    private ActivityResultLauncher<Intent> speechRecognitionLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen_and_tell_me);

        initComponents();

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO},
                1);

        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stListen=new String[n];
                i=0;
                for (int i = 0; i < stListen.length ; i++) {
                    int x=10+(int)((99-10+1)*Math.random());
                    if (x%2==0)
                        stListen[i]="O";
                    else
                        stListen[i]="A";
                }
                cdt=new CountDownTimer(tikTime*n, tikTime) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tvListen.setAlpha(1f);
                        tvListen.setText(""+stListen[i]);
                        tts.speak(stListen[i], TextToSpeech.QUEUE_FLUSH, null, null);
                        tvListen.animate().alpha(0f).setDuration(tikTime).start();
                        i++;
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();
            }
        });

        bSpeachToText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpeechRecognition();
            }
        });
    }

    private void startSpeechRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak something...");

        try {
            speechRecognitionLauncher.launch(intent);
        } catch (ActivityNotFoundException e) {
            // Handle speech recognition not available
        }
    }

    private void handleSpeechRecognitionResult(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (data != null) {
                ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (results != null && !results.isEmpty()) {
                    String recognizedText = results.get(0);
                    tvRecord.setText(recognizedText);
                }
            }
        }
    }



    private void initComponents() {
        context=this;
        tvListen= (TextView) findViewById(R.id.tvListen);
        tvRecord= (TextView) findViewById(R.id.tvRecord);
        bStart= (Button) findViewById(R.id.bStart);
        bSpeachToText= (Button) findViewById(R.id.bSpeachToText);
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        //
                    } else {
                        //startGeneratingAndSpeaking();
                    }
                } else {
                    //
                }
            }
        });
        // Initialize the ActivityResultLauncher אתחול
        speechRecognitionLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();
                        handleSpeechRecognitionResult(result.getResultCode(), data);
                    }
                }
        );
    }
}