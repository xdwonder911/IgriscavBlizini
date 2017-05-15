package com.example.xdwonder.igriscavblizini;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.frosquivel.magicalcamera.Functionallities.PermissionGranted;

import static com.example.xdwonder.igriscavblizini.ApplicationMy.da;

public class activity_addplayground extends AppCompatActivity {
    ApplicationMy app;//ka je point tega
    PermissionGranted permissionGranted;
    float ratingx=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplayground);

        app = (ApplicationMy) getApplication(); // pa tega ???

        setTitle("Add a new playground");
        final LinearLayout ll=(LinearLayout)findViewById(R.id.LinearLayoutAddPlay);
        //final Intent intent = new Intent(this, activity_addplaygroundphoto.class);
        final Intent intent = new Intent(this, activity_addplaygroundmagicphoto.class);
        for(int i = 0; i< da.getTags().getTagList().size(); i++){
            CheckBox x = new CheckBox(activity_addplayground.this);//this
            x.setText(da.getTags().getTagList().get(i).toString());
            x.setId(i);
            //ll.setId(i);
            ll.addView(x);
            //ll.getLayoutParams().height=ll.getLayoutParams().height+40;
            //ll.requestLayout();
        }

        final RatingBar rb=(RatingBar)findViewById(R.id.ratingBarAdd);
        // System.out.println(rb.getNumStars());
        rb.setNumStars(5);
        //rb.setMinimumWidth(20);
        rb.setScaleX((float)0.8);
        rb.setScaleY((float)0.8);

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                ratingx=rating;
            }
        });






        permissionGranted = new PermissionGranted(this); //need for magic camera also checkPermissions
        if (android.os.Build.VERSION.SDK_INT >= 24) {
            permissionGranted.checkAllMagicalCameraPermission();
        }else{
            permissionGranted.checkCameraPermission();
            permissionGranted.checkReadExternalPermission();
            permissionGranted.checkWriteExternalPermission();
            permissionGranted.checkLocationPermission();
        }



        Button btn1=(Button)findViewById(R.id.buttonNext);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Playground tmp=ApplicationMy.da.addPlayground(999,999);

                for(int k=0;k<ll.getChildCount();k++){
                    View vtmp = ll.getChildAt(k);
                    if(vtmp instanceof CheckBox){
                        if(((CheckBox)vtmp).isChecked()){
                            System.out.println("Dodan id : "+ll.getChildAt(k).getId());
                           // check.add((String.valueOf(ll.getChildAt(k).getId())));
                            da.addPlaygroundtag(tmp,da.getTags().getTagList().get(ll.getChildAt(k).getId()));

                        }
                    }
                }
tmp.setRating(ratingx);
                app.save();
                intent.putExtra("PLAYGROUND_ID",tmp.getIdPlayground());
                startActivity(intent);
            }
        });


    }
}
