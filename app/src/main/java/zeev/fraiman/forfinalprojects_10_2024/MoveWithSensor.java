package zeev.fraiman.forfinalprojects_10_2024;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MoveWithSensor extends AppCompatActivity
        implements SensorEventListener {

    Context context;
    ImageView ivArnav;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private float lastX, lastY;
    int speed=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_sensor);

        context=this;
        ivArnav= (ImageView) findViewById(R.id.ivArnav);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener((SensorEventListener) context,
                accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //sensorManager.registerListener((SensorEventListener) context, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onDestroy()  {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];

            // new coordinates for ImageView
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ivArnav.getLayoutParams();
            int newX = (int) (layoutParams.leftMargin - x * speed);
            int newY = (int) (layoutParams.topMargin + y * speed);

            // new position of ImageView on the screen
            layoutParams.leftMargin = newX;
            layoutParams.topMargin = newY;
            ivArnav.setLayoutParams(layoutParams);
        }
        else
            Toast.makeText(context, "No", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}