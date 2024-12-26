package zeev.fraiman.forfinalprojects_10_2024;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MoveOrJump extends AppCompatActivity {

    Context context;
    ImageView ivHare;
    RelativeLayout RLmain;
    Button bJumpBezier;
    float x1,y1,x2,y2;
    float startX, startY, controlX, controlY, endX, endY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_or_jump);

        initComponents();

        RLmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animatorX = ObjectAnimator.ofFloat(ivHare, "x", x1, x2);
                ObjectAnimator animatorY = ObjectAnimator.ofFloat(ivHare, "y", y1, y2);

                // Устанавливаем длительность анимации (в миллисекундах)
                animatorX.setDuration(4000);
                animatorY.setDuration(4000);

                // Запускаем анимацию
                animatorX.start();
                animatorY.start();

                // Обработчик завершения анимации
                animatorX.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        // После завершения анимации обновляем начальные координаты
                        x1 = x2;
                        y1 = y2;
                        // Меняем местами начальные и конечные координаты, чтобы анимация шла в обратную сторону
                        float tempX = x1;
                        float tempY = y1;
                        x1 = x2;
                        y1 = y2;
                        x2 = tempX;
                        y2 = tempY;
                    }
                });
            }
        });

        bJumpBezier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
                animator.setDuration(2000); // Устанавливаем длительность анимации (в миллисекундах)
                animator.setInterpolator(new LinearInterpolator());

                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        float fraction = valueAnimator.getAnimatedFraction();
                        float oneMinusT = 1 - fraction;

                        // Вычисляем координаты на кривой Безье
                        float x = oneMinusT * oneMinusT * startX + 2 * oneMinusT * fraction * controlX + fraction * fraction * endX;
                        float y = oneMinusT * oneMinusT * startY + 2 * oneMinusT * fraction * controlY + fraction * fraction * endY;

                        // Устанавливаем координаты для ImageView
                        ivHare.setX(x);
                        ivHare.setY(y);
                    }
                });

                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        // Действия по завершению анимации
                    }
                });

                // Запускаем анимацию
                animator.start();
            }
        });
    }

    private void initComponents() {
        context=this;
        ivHare= (ImageView) findViewById(R.id.ivHare);
        RLmain= (RelativeLayout) findViewById(R.id.RLmain);
        bJumpBezier= (Button) findViewById(R.id.bJumpBezier);
        x1 = 100; // Начальное положение по оси X
        y1 = 1000; // Начальное положение по оси Y

        // Задаем конечные координаты
        x2 = 500; // Конечное положение по оси X
        y2 = 500; // Конечное положение по оси Y

        // Задаем начальные, контрольные и конечные координаты для параболы
        startX = 100; // Начальное положение по оси X
        startY = 1000; // Начальное положение по оси Y
        controlX = 400; // Контрольное положение по оси X
        controlY = 200; // Контрольное положение по оси Y
        endX = 700; // Конечное положение по оси X
        endY = 1000; // Конечное положение по оси Y

        setImageViewPosition(x1, y1);
    }

    private void setImageViewPosition(float x, float y) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ivHare.getLayoutParams();
        layoutParams.leftMargin = (int) x;
        layoutParams.topMargin = (int) y;
        ivHare.setLayoutParams(layoutParams);
    }
}