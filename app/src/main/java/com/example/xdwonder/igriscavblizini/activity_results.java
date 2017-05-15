package com.example.xdwonder.igriscavblizini;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import static com.example.xdwonder.igriscavblizini.ApplicationMy.da;

public class activity_results extends AppCompatActivity {

    boolean SearchFilter(Playground x,ArrayList<String> array)
    {boolean found=false;
      //  System.out.println("array.size(): "+array.size()+"||x.getTagList().size()"+x.getTagList().size());

        if (array.size() != 0)
        {
            //if(array.size()==1)
            //{
                for(int i=0;i<x.getTagList().size();i++)
                {
                    for(int j=0;j<array.size();j++)
                    {
                        if(x.getTagList().get(i).getId().equals(array.get(j)))
                        {
                            return true;
                        }
                    }
                }
                return false;
           // }
       /* for(int i=0;i<x.getTagList().size();i++)
        {
            for(int j=0;j<array.size();j++)
            {System.out.println("Iteracija: "+i);
                if(x.getTagList().get(i).getId().equals(array.get(j)))
                {
                    System.out.println("Found tag with id"+array.get(j));
                    //found=true;
                    //break;
                    return true;
                }
            }
            if(!found)
                return false;
            else found=false;
        }*/
        }return true;
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

      //  ConstraintLayout cl= (ConstraintLayout) findViewById(R.id.activity_results);
        LinearLayout ll=(LinearLayout)findViewById(R.id.linearlayout_results);
        setTitle("Results:");
        final Intent intent = new Intent(this, activity_playground.class);

       // LinearLayout x=(LinearLayout) findViewById(R.id.linearlayout_results);
        LinearLayout x=new LinearLayout(this);
        x.setOrientation(LinearLayout.HORIZONTAL);
        //x.getLayoutParams(,);
        x.setLayoutParams(new FrameLayout.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.MATCH_PARENT));
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            System.out.println("NIČ");
        }else System.out.println();

        boolean EmptyResults=true;
        ArrayList<String> tagList = (ArrayList<String>) getIntent().getSerializableExtra("TAG_IDS");


      // System.out.println("SearchFilterRes: "+SearchFilter(da.getPlaygroundsList().get(0),tagList));
for(int i=0;i<da.getPlaygroundsList().size();i++)
{
    if(da.getPlaygroundsList().get(i).getRating()>=Float.parseFloat(getIntent().getExtras().getString("EXTRA_SESSION_RATING","default")) && SearchFilter(da.getPlaygroundsList().get(i),tagList))
    {

    EmptyResults=false;
    LinearLayout lltmp=new LinearLayout(this);
    lltmp.setOrientation(LinearLayout.VERTICAL);
    //x.getLayoutParams(,);
    lltmp.setLayoutParams(new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));



    TextView tv_address = new TextView(activity_results.this);
    tv_address.setText("Street address based on x,y");


        //tv_address.setText(tagList.get(0).toString());
        //tv_address.setText(getIntent().getExtras().getString("TAG_IDS","default_id"));
    lltmp.addView(tv_address);
    TextView tv_xy = new TextView(activity_results.this);
    tv_xy.setText("Coordinates x: "+da.getPlaygroundsList().get(i).getX()+ "    y: " + da.getPlaygroundsList().get(i).getY());
    lltmp.addView(tv_xy);
    TextView tv_date = new TextView(activity_results.this);
    tv_date.setText("Date added: dd.mm.yyyy");
    lltmp.addView(tv_date);

    TextView tv_tags=new TextView(this);
    tv_tags.setText("Tags: ");
    for(int j=0;j<ApplicationMy.da.getPlaygroundsList().get(i).getTagList().size();j++)
        tv_tags.setText(tv_tags.getText()+ApplicationMy.da.getPlaygroundsList().get(i).getTagList().get(j).getIme()+", ");

    lltmp.addView(tv_tags);


    RatingBar rb=new RatingBar(this);
    rb.setScaleX((float)0.8);
    rb.setScaleY((float)0.8);
    rb.setRating(ApplicationMy.da.getPlaygroundsList().get(i).getRating());
    LinearLayout.LayoutParams lp= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    lp.weight=1.0f;
    lp.gravity= Gravity.LEFT;
    lp.setMargins(0,0,0,0);
    rb.setLayoutParams(lp);
    rb.setIsIndicator(true);
    rb.setFocusable(false);
    lltmp.addView(rb);

   // LinearLayout llh =new LinearLayout(this);
    //llh.setOrientation(LinearLayout.HORIZONTAL);

    //x.getLayoutParams().height=ll.getLayoutParams().height+60;
    //lltmp.requestLayout();


    final int i_tmp=i;
    lltmp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent.putExtra("EXTRA_SESSION_ID",da.getPlaygroundsList().get(i_tmp).getIdPlayground());
            startActivity(intent);
        }
    });
    View v = new View(this);
    v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5));
    v.setBackgroundColor(Color.parseColor("#B3B3B3"));
    lltmp.addView(v);

        Space space=new Space(this);
        space.setLayoutParams(new FrameLayout.LayoutParams(0,50));
        lltmp.addView(space);

    ll.addView(lltmp);
    }else System.out.println("Ni izpisalo igrisca st "+i);
}
        if(EmptyResults)
        {   TextView tv_empty = new TextView(activity_results.this);
            tv_empty.setText("No results, Please try changing the filter.");
            ll.addView(tv_empty);
        }
       // x.addView(tv1);
        //x.addView(tv);
        //ll.addView(x);

    }
}
