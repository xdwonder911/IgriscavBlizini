package com.example.xdwonder.igriscavblizini;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

import static com.example.xdwonder.igriscavblizini.ApplicationMy.da;

public class activity_playground extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground);
        TextView tv1 = (TextView) findViewById(R.id.tVTitle);
        setTitle("Distance: 0.5 km");
        tv1.setText("Location X: "+da.getPlaygroundByID(getIntent().getExtras().getString("EXTRA_SESSION_ID","default")).getX()+" Y: "+da.getPlaygroundByID(getIntent().getExtras().getString("EXTRA_SESSION_ID","default")).getY());
        TextView tv2 = (TextView) findViewById(R.id.tVDate);
        tv2.setText("Date added: 6. 3. 2017");

        RatingBar rb=(RatingBar)findViewById(R.id.ratingBar);
        rb.setRating(da.getPlaygroundByID(getIntent().getExtras().getString("EXTRA_SESSION_ID","default")).getRating());


        TextView tv3=(TextView)findViewById(R.id.textViewtags);
      //  for(int j=0;j<ApplicationMy.da.getPlaygroundsList().get(i).getTagList().size();i++)
        for(int j=0;j<da.getPlaygroundByID(getIntent().getExtras().getString("EXTRA_SESSION_ID","default")).getTagList().size();j++)
            tv3.setText(tv3.getText()+ ApplicationMy.da.getPlaygroundByID(getIntent().getExtras().getString("EXTRA_SESSION_ID","default")).getTagList().get(j).getIme()+", ");


        LinearLayout llv=(LinearLayout)findViewById(R.id.ll_playground);
        LinearLayout ll=new LinearLayout(this);
        HorizontalScrollView hsv=new HorizontalScrollView(this);

        ll.setOrientation(LinearLayout.HORIZONTAL);


        for(int i=0;i<ApplicationMy.da.getPlaygroundByID(getIntent().getExtras().getString("EXTRA_SESSION_ID","default")).getImgPathList().size();i++)
        {
            ImageView img = new ImageView(activity_playground.this);
            /*img.setImageBitmap(BitmapFactory.decodeFile(ApplicationMy.da.getPlaygroundByID(getIntent().getExtras().getString("EXTRA_SESSION_ID","default")).getImgPathList().get(i)));
            img.setLayoutParams(new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            ll.addView(img);*/

            File f = new File(ApplicationMy.da.getPlaygroundByID(getIntent().getExtras().getString("EXTRA_SESSION_ID","default")).getImgPathList().get(i)); //
            Picasso.with(this.getApplicationContext())
                    .load(f) //URL
                    .placeholder(R.drawable.downloading_icon)
                    .error(R.drawable.no_image_icon)
                    // To fit image into imageView
                    //.fit()
                    // To prevent fade animation
                    //.noFade()
                    .into(img);
            ll.addView(img);










            Space space=new Space(this);
            space.setLayoutParams(new FrameLayout.LayoutParams(75,0));
            ll.addView(space);

            //TextView tv=new TextView(activity_addplaygroundphoto.this);
            //TextView tv=new TextView(activity_addplaygroundphoto.this);
            // tv.setText(ApplicationMy.da.getPlaygroundByID(getIntent().getExtras().getString("PLAYGROUND_ID","default")).getImgPathList().get(i));
            //ll.addView(tv);
            //   }
        }
        hsv.addView(ll);
        llv.addView(hsv);

    }
}
