package com.example.xdwonder.igriscavblizini;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.solver.widgets.ConstraintHorizontalLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class activity_search extends AppCompatActivity {

    boolean toggle=false;
    float star_rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("Search:");

        final Intent intent = new Intent(this, activity_results_list.class);//
        final LinearLayout ll=(LinearLayout)findViewById(R.id.linear_layout);
        ConstraintLayout cl= (ConstraintLayout) findViewById(R.id.activity_search1);//new ConstraintLayout(this);
        TextView tv = new TextView(this);
        tv.setText("Type:");
        ll.addView(tv);

        for(int i=0;i<ApplicationMy.da.getTags().getTagList().size();i++){
            CheckBox x = new CheckBox(activity_search.this);//this
            x.setText(ApplicationMy.da.getTags().getTagList().get(i).toString());
            x.setId(i);
            //ll.setId(i);
            ll.addView(x);
            ll.getLayoutParams().height=ll.getLayoutParams().height+40;
            ll.requestLayout();
        }

        ConstraintHorizontalLayout hl=new ConstraintHorizontalLayout();



        TextView tv1 = new TextView(this);
        tv1.setText("Rating: (at least)");
        ll.addView(tv1);
        final Button btn1=new Button(this);
        btn1.setText("ANY");
        btn1.setId(R.id.RatingBtn);
        //tv2.setPadding(10,3,0,0);
        final RatingBar rb=new RatingBar(this);
       // System.out.println(rb.getNumStars());
        rb.setNumStars(5);
        rb.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        //rb.setMinimumWidth(20);
        rb.setScaleX((float)0.8);
        rb.setScaleY((float)0.8);

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                    star_rating=rating;
                    btn1.getBackground().setAlpha(64);
                    toggle=false;
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(toggle==false)
                {
                    System.out.println("SETALPHA255");
                    rb.setRating(0);
                    star_rating=0;
                    btn1.getBackground().setAlpha(255);
                    //btn1.setBackgroundColor(Color.rgb(250,250,245));
                    toggle=true;
                }else
                {
                    btn1.getBackground().setAlpha(64);
                   // btn1.setBackgroundResource(android.R.drawable.btn_default);
                             //btn1.setBackgroundDrawable(getResources().getDrawable(R.drawable.whatever));
                    toggle=false;
                }
            }
        });

        Button button1 = (Button) findViewById(R.id.btn_search);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               // star_rating=Float.parseFloat(star_rating);


                //čekiranje izbranih tagov
            //    LinearLayout ll=(LinearLayout)findViewById(R.id.linear_layout);
                ArrayList<String> check = new ArrayList<String>();
    if(ll==null)
    System.out.println("LL ne obstaja!");
                for(int k=0;k<ll.getChildCount();k++){
                    View vtmp = ll.getChildAt(k);
                    if(vtmp instanceof CheckBox){
                        if(((CheckBox)vtmp).isChecked()){
                            System.out.println("Dodan id : "+ll.getChildAt(k).getId());
                            check.add((String.valueOf(ll.getChildAt(k).getId())));
                        }
                    }
                }
                intent.putExtra("TAG_IDS",check);






                String s=String.valueOf(star_rating);
                intent.putExtra("EXTRA_SESSION_RATING", s);
                startActivity(intent);
                System.out.println("\nSEARCH");
            }
        });




        LinearLayout ll_h=new LinearLayout(this);
        ll_h.setOrientation(LinearLayout.HORIZONTAL);


       // ll.addView(rb);
        ll_h.addView(btn1);
        ll_h.addView(rb);
        ll.addView(ll_h);

        setContentView(cl);
    }


    /*public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromTouch) {

        final int numStars = ratingBar.getNumStars();
        ratingBar.getRating() ;
        final float ratingBarStepSize = ratingBar.getStepSize();
        System.out.println("\n"+numStars+","+ ratingBar.getRating()+","+ratingBarStepSize);
        Button btn1 = (Button) findViewById(R.id.RatingBtn);
        btn1.setBackgroundResource(android.R.drawable.btn_default);
        System.out.println("DSADSADSAD");
        toggle=false;
    }
*/
}
