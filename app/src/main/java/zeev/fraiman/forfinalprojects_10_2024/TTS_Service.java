package zeev.fraiman.forfinalprojects_10_2024;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class TTS_Service extends Service {
    TextToSpeech tts;
    int language;
    String st="";

    public TTS_Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        st=intent.getStringExtra("what");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                initializeTextToSpeech(st);
            }
        });
        thread.start();
        return START_STICKY;
    }

    private void initializeTextToSpeech(final String text) {
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        //Log.e("TTS", "Language is not supported");
                    } else {
                        tts.speak(st, TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                } else {
                    //Log.e("TTS", "Initialization failed");
                }
            }
        });
    }

}