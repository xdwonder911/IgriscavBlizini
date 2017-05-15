package com.example.xdwonder.igriscavblizini;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.xdwonder.igriscavblizini.R.id.fab;
import static com.example.xdwonder.igriscavblizini.R.id.ll_photo;

public class activity_addplaygroundphoto extends AppCompatActivity {
    ApplicationMy app;//ka je point tega


    @Override
    protected void onResume(){
        super.onResume();


        app = (ApplicationMy) getApplication(); // pa tega ???




      //  setContentView(R.layout.activity_addplaygroundphoto);
            setTitle("Add a photo onresume");
       // if(mCurrentPhotoPath!=null){
            LinearLayout ll=(LinearLayout)findViewById(R.id.ll_photo);

        ll.removeAllViews();
           // ImageView img = new ImageView(activity_addplaygroundphoto.this);
           // img.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));
        //    ll.addView(img);
          //  mCurrentPhotoPath=null;

            for(int i=0;i<ApplicationMy.da.getPlaygroundByID(getIntent().getExtras().getString("PLAYGROUND_ID","default")).getImgPathList().size();i++)
            {
                ImageView img = new ImageView(activity_addplaygroundphoto.this);
                img.setImageBitmap(BitmapFactory.decodeFile(ApplicationMy.da.getPlaygroundByID(getIntent().getExtras().getString("PLAYGROUND_ID","default")).getImgPathList().get(i)));
                img.setLayoutParams(new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 500));
                ll.addView(img);
                System.out.println(ApplicationMy.da.getPlaygroundByID(getIntent().getExtras().getString("PLAYGROUND_ID","default")).getImgPathList().get(i));
                Space space=new Space(this);
                space.setLayoutParams(new FrameLayout.LayoutParams(0,50));
                ll.addView(space);

                Button btn =new Button(this);
                btn.setId(i);
                btn.setText("REMOVE");
                ll.addView(btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ApplicationMy.da.getPlaygroundByID(getIntent().getExtras().getString("PLAYGROUND_ID","default")).getImgPathList().remove(v.getId());
                        app.save();
                        onResume();
                    }
                });

                //TextView tv=new TextView(activity_addplaygroundphoto.this);
               // tv.setText(ApplicationMy.da.getPlaygroundByID(getIntent().getExtras().getString("PLAYGROUND_ID","default")).getImgPathList().get(i));
                //ll.addView(tv);
         //   }
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplaygroundphoto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Add a photo");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG);
                //  .setAction("Action", null).show();
                dispatchTakePictureIntent();
               // LinearLayout ll=(LinearLayout)findViewById(R.id.ll_photo);
                //Uri uri=Uri.fromFile(image);
                //img.setImageURI(uri);
                System.out.println("PIZDATIAMSAFHUDHFIUSDAI");
                // System.out.println("Image.getpath:"+mCurrentPhotoPath);
                //TextView tv=new TextView(activity_addplaygroundphoto.this);
                //tv.setText("Img path:"+mCurrentPhotoPath);
                //ll.addView(tv);






            }
        });

        //LinearLayout ll=(LinearLayout)findViewById(R.id.ll_photo);

    }









//shranjevanje slike na telefon






    String mCurrentPhotoPath;
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //takePictureIntent.setType("image/*");


        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                System.out.println("Error med kreacijo slike");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);


                takePictureIntent.putExtra("crop", "true");
                takePictureIntent.putExtra("outputX", 150);
                takePictureIntent.putExtra("outputY", 150);
                takePictureIntent.putExtra("aspectX", 1);
                takePictureIntent.putExtra("aspectY", 1);
                takePictureIntent.putExtra("scale", true);
                // takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                startActivityForResult(takePictureIntent, 1);
                ApplicationMy.da.getPlaygroundByID(getIntent().getExtras().getString("PLAYGROUND_ID", "default")).addImgPath(mCurrentPhotoPath);
                System.out.println("Added img path: " + mCurrentPhotoPath + " to Playground with id" + getIntent().getExtras().getString("PLAYGROUND_ID", "default"));
                app.save();

            }
        }

    }
}
