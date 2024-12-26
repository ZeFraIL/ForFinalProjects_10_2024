package zeev.fraiman.forfinalprojects_10_2024;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class JumpAfterClick extends AppCompatActivity {

    Context context;
    ImageView ivBanny;
    ConstraintLayout CL;
    int maxOffset=500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump_after_click);

        context=this;
        ivBanny=findViewById(R.id.ivBanny);
        CL= (ConstraintLayout) findViewById(R.id.conteiner);

        ivBanny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int containerWidth = CL.getWidth();
                int containerHeight = CL.getHeight();

                int imageViewWidth = ivBanny.getWidth();
                int imageViewHeight = ivBanny.getHeight();

                int maxX = containerWidth - imageViewWidth;
                int maxY = containerHeight - imageViewHeight;

                int newX = -maxX+(int)(2*maxX*Math.random());
                int newY = -maxY+(int)(2*maxY*Math.random());

                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ivBanny.getLayoutParams();
                layoutParams.leftMargin =newX;
                layoutParams.topMargin =newY;
                ivBanny.setLayoutParams(layoutParams);
            }
        });
    }
}