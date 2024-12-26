package zeev.fraiman.forfinalprojects_10_2024;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MoveWithFinger extends AppCompatActivity {

    Context context;
    ImageView ivZaika, ivLisa;
    int lisaWidth,lisaHeight,zaykaWidth,zaykaHeight;
    ConstraintLayout CL_MWF;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_with_finger);

        context=this;
        tv= (TextView) findViewById(R.id.tv);
        ivZaika= (ImageView) findViewById(R.id.ivZaika);
        ivLisa= (ImageView) findViewById(R.id.ivLisa);
        lisaWidth = ivLisa.getWidth();
        lisaHeight = ivLisa.getHeight();
        zaykaWidth = ivZaika.getWidth();
        zaykaHeight = ivZaika.getHeight();
        CL_MWF=findViewById(R.id.CL_MWF);
        CL_MWF.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                tv.setText(""+x+","+y);
                ivLisa.setX(x);
                ivLisa.setY(y);
                if (isImageViewOverlapping(ivLisa, ivZaika)) {
                    Toast.makeText(context, "ivLisa TOUCH ivZaika!", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    /// Метод для определения пересечения двух ImageView
    private boolean isImageViewOverlapping(ImageView imageView1, ImageView imageView2) {
        int[] location1 = new int[2];
        int[] location2 = new int[2];

        imageView1.getLocationOnScreen(location1);
        imageView2.getLocationOnScreen(location2);

        return !(location1[0] > location2[0] + zaykaWidth ||
                location1[0] + lisaWidth < location2[0] ||
                location1[1] > location2[1] + zaykaHeight ||
                location1[1] + lisaHeight < location2[1]);
    }
}