package zeev.fraiman.forfinalprojects_10_2024;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ShuffleString extends AppCompatActivity {

    Context context;
    EditText etNormalString;
    Button bShuffle;
    LinearLayout LL;
    TextView tvResult;
    String stNormal="", stShuffle="";
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuffle_string);

        initComponents();

        bShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stNormal=etNormalString.getText().toString();
                //arrayList= (ArrayList<String>) Arrays.asList(stNormal.split(""));
                arrayList=new ArrayList<>();
                for (int i = 0; i < stNormal.length(); i++) {
                    arrayList.add(""+stNormal.charAt(i));
                }
                Collections.shuffle(arrayList);
                stShuffle="";
                for (int i = 0; i < arrayList.size(); i++) {
                    stShuffle+=arrayList.get(i);
                }
                Button[] letters=new Button[stShuffle.length()];
                for (int i = 0; i < letters.length; i++) {
                    letters[i]=new Button(context);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams( 150, 150 );
                    layoutParams.setMargins(10,10,10,10);
                    letters[i].setLayoutParams(new LinearLayout.LayoutParams(layoutParams));
                    letters[i].setId(i);
                    letters[i].setText(""+stShuffle.charAt(i));
                    letters[i].setTextSize(25);
                    LL.addView(letters[i]);
                }
            }
        });
    }

    private void initComponents() {
        context=this;
        etNormalString= (EditText) findViewById(R.id.etNormalString);
        bShuffle= (Button) findViewById(R.id.bShuffle);
        LL= (LinearLayout) findViewById(R.id.LL);
        tvResult= (TextView) findViewById(R.id.tvResult);
    }
}