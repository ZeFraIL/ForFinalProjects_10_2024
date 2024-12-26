package zeev.fraiman.forfinalprojects_10_2024;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class ButtonJump extends AppCompatActivity {

    Button myButton;
    private RelativeLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_jump);

        myButton = findViewById(R.id.myButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveButtonRandomly();
            }
        });
    }

    private void moveButtonRandomly() {
        container = findViewById(R.id.container);

        // Генерируем случайные координаты для размещения кнопки
        Random rand = new Random();
        int maxX = container.getWidth() - myButton.getWidth();
        int maxY = container.getHeight() - myButton.getHeight();
        int newX = rand.nextInt(maxX);
        int newY = rand.nextInt(maxY);

        // Устанавливаем новые координаты для кнопки
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = newX;
        layoutParams.topMargin = newY;
        myButton.setLayoutParams(layoutParams);
    }

}