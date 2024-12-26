package zeev.fraiman.forfinalprojects_10_2024;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Context context;
    Button bJumpAfterClick, bButtonJump, bMoveWithSensor,
            bMoveWithFinger, bShuffleString, bListenAndTellMe,
            bMoveOrJump, bMove8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;
        bJumpAfterClick= (Button) findViewById(R.id.bJumpAfterClick);
        bJumpAfterClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go=new Intent(context, JumpAfterClick.class);
                startActivity(go);
            }
        });

        bButtonJump= (Button) findViewById(R.id.bButtonJump);
        bButtonJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go=new Intent(context, ButtonJump.class);
                startActivity(go);
            }
        });

        bMoveWithSensor= (Button) findViewById(R.id.bMoveWithSensor);
        bMoveWithSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go=new Intent(context, MoveWithSensor.class);
                startActivity(go);
            }
        });

        bMoveWithFinger= (Button) findViewById(R.id.bMoveWithFinger);
        bMoveWithFinger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go=new Intent(context, MoveWithFinger.class);
                startActivity(go);
            }
        });

        bShuffleString= (Button) findViewById(R.id.bShuffleString);
        bShuffleString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go=new Intent(context, ShuffleString.class);
                startActivity(go);
            }
        });

        bListenAndTellMe= (Button) findViewById(R.id.bListenAndTellMe);
        bListenAndTellMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go=new Intent(context, ListenAndTellMe.class);
                startActivity(go);
            }
        });

        bMoveOrJump= (Button) findViewById(R.id.bMoveOrJump);
        bMoveOrJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go=new Intent(context, MoveOrJump.class);
                startActivity(go);
            }
        });
    }
}