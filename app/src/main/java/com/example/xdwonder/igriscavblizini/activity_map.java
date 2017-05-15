package com.example.xdwonder.igriscavblizini;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_map extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        final Intent intent = new Intent(this, activity_addplayground.class);

        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //intent = new Intent(savedInstanceState.this, activity_map.class);
                //  EditText editText = (EditText) findViewById(R.id.editText);
                //  String message = editText.getText().toString();
                //intent.putExtra(EXTRA_MESSAGE, message);
                System.out.println("\nBTN1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(intent);
                System.out.println("\nBTN2");
            }
        });
    }
}
