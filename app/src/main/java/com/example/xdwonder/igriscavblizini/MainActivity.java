package com.example.xdwonder.igriscavblizini;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    ApplicationMy app;
    //TextView textView1;
  //  EditText edName;
    Intent intent,intent2,intent3;


    @Override
    public void onCreate(Bundle savedInstanceState) {//protected
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
       // textView1 = (TextView) findViewById(R.id.textView1);
       // textView1.setText("Pozdravljen");
        //edName = (EditText) findViewById(R.id.editTextName);
      //  edName.setText("DSA");
        intent = new Intent(this, osmoidmap.class);//osmoidmap//activity_addplayground.
        intent2 = new Intent(this, activity_search.class);

                intent3 = new Intent(this, activity_addplayground.class);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //intent = new Intent(savedInstanceState.this, activity_map.class);
                //  EditText editText = (EditText) findViewById(R.id.editText);
                //  String message = editText.getText().toString();
                //  intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
                System.out.println("\nBTN1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(intent3);
                System.out.println("\nBTN2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(intent2);
                System.out.println("\nBTN3");
            }
        });




        app = (ApplicationMy) getApplication();
    }


/*

    public void button1OnClick(View v) {
        Intent intent = new Intent(this, activity_map.class);
        //  EditText editText = (EditText) findViewById(R.id.editText);
        //  String message = editText.getText().toString();
        //  intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        System.out.println("\nBTN1");
    }
    public void button2OnClick(View v) {
        // Button button=(Button) v;
        //   setContentView(R.layout.activity_map);
        System.out.println("\nBTN2");
    }
    public void button3OnClick(View v) {
        System.out.println("\nBTN3");
    }
      /* switch(v.getId())
       {
           case R.id.button1:


               break;
           case R.id.button2:
               break;
           case R.id.button3:
               break;
       }*/


}
