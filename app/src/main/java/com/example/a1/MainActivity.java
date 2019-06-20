package com.example.a1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText textView;
    TextView memos;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView= findViewById(R.id.textView);
        button = findViewById(R.id.button);
        memos = findViewById(R.id.memos);

        final SharedPreferences SharedPref = getPreferences(Context.MODE_PRIVATE);
        final String OldItem= SharedPref.getString("OldItem","NOthing Found");
        final String viewMemo = SharedPref.getString("ViewMemo","Add Something");
        textView.setText(OldItem);
        memos.setText(viewMemo);
        memos.setMovementMethod(new ScrollingMovementMethod());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor= SharedPref.edit();
                editor.putString("OldItem", textView.getText().toString());
                //editor.putString("viewMemo", memos.append(textView.getText().toString()));
                editor.commit();
                memos.append("\n" + textView.getText().toString());


                Animation animation = new AlphaAnimation(1.0f, 0.0f);
                animation.setDuration(500);
                button.startAnimation(animation);
            }
        });
    }
}
