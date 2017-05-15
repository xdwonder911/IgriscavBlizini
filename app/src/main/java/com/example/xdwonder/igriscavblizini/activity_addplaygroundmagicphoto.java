package com.example.xdwonder.igriscavblizini;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.Toast;

import com.frosquivel.magicalcamera.Functionallities.PermissionGranted;
import com.frosquivel.magicalcamera.MagicalCamera;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.xdwonder.igriscavblizini.ApplicationMy.da;

public class activity_addplaygroundmagicphoto extends AppCompatActivity {

    Intent intent2=new Intent(activity_addplaygroundmagicphoto.this,osmoidmap.class);

    final Intent intent = new Intent(this, activity_addplaygroundmagicphoto.class);
    PermissionGranted permissionGranted;
    MagicalCamera magicalCamera;
    ApplicationMy app;//ka je point tega
    private int RESIZE_PHOTO_PIXELS_PERCENTAGE = 40;
    private LocationUpdateReceiver dataUpdateReceiver;
    Location mLocation;
    private class LocationUpdateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(GPSTracker.GPSTrackerEvent)) {
                mLocation = intent.getParcelableExtra(GPSTracker.GPSTrackerKeyLocation);
                app.setLastLocation(mLocation);
                System.out.println("Ali TTTdela 2"+ System.currentTimeMillis()+" "+mLocation.toString());
            }
        }
    }





    @Override
    protected void onResume(){
        super.onResume();
        app = (ApplicationMy) getApplication(); // pa tega ???

//SHRANIMO GPS KORDINATE
        startService(new Intent(app, GPSTracker.class));//start service
        if (dataUpdateReceiver == null) dataUpdateReceiver = new LocationUpdateReceiver();
        IntentFilter intentFilter = new IntentFilter(GPSTracker.GPSTrackerEvent);
        registerReceiver(dataUpdateReceiver, intentFilter);

        //stateNew = true;
if(app.getLastLocation()!=null){
    da.getPlaygroundByID(getIntent().getExtras().getString("PLAYGROUND_ID","default")).setX(app.getLastLocation().getLongitude());
    da.getPlaygroundByID(getIntent().getExtras().getString("PLAYGROUND_ID","default")).setY(app.getLastLocation().getLatitude());

   // intent2.putExtra("LongX",app.getLastLocation().getLongitude());
   // intent2.putExtra("LatY",app.getLastLocation().getLatitude());
    Bundle extras=new Bundle();
    extras.putDouble("LongX",app.getLastLocation().getLongitude());
    extras.putDouble("LatY",app.getLastLocation().getLatitude());
intent2.putExtras(extras);

    Geocoder geocoder;
    List<Address> addresses;
    geocoder = new Geocoder(this, Locale.getDefault());


    try {
        addresses = geocoder.getFromLocation(app.getLastLocation().getLatitude(), app.getLastLocation().getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
        da.getPlaygroundByID(getIntent().getExtras().getString("PLAYGROUND_ID","default")).setAddress(address);
        da.getPlaygroundByID(getIntent().getExtras().getString("PLAYGROUND_ID","default")).setCity(city);
        app.save();
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("FAK");
    }






    System.out.println("Dodane kordinate lokacije x: "+da.getPlaygroundByID(getIntent().getExtras().getString("PLAYGROUND_ID","default")).getX());
}else System.out.println("Ni šlo dodati kordinat ");



        //  setContentView(R.layout.activity_addplaygroundphoto);
        setTitle("Add a photo onresume+");
        // if(mCurrentPhotoPath!=null){
        LinearLayout ll=(LinearLayout)findViewById(R.id.lll_photo);

        ll.removeAllViews();
        // ImageView img = new ImageView(activity_addplaygroundphoto.this);
        // img.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));
        //    ll.addView(img);
        //  mCurrentPhotoPath=null;

        for(int i = 0; i< da.getPlaygroundByID(getIntent().getExtras().getString("PLAYGROUND_ID","default")).getImgPathList().size(); i++)
        {   LinearLayout ll2=new LinearLayout(this);
            HorizontalScrollView hsv=new HorizontalScrollView(this);

            ll2.setOrientation(LinearLayout.HORIZONTAL);
            ImageView img = new ImageView(activity_addplaygroundmagicphoto.this);
            img.setImageBitmap(BitmapFactory.decodeFile(da.getPlaygroundByID(getIntent().getExtras().getString("PLAYGROUND_ID","default")).getImgPathList().get(i)));
            img.setLayoutParams(new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 800));
img.setPadding(0,0,0,0);
            img.setId(i+1000);



            //System.out.println(ApplicationMy.da.getPlaygroundByID(getIntent().getExtras().getString("PLAYGROUND_ID","default")).getImgPathList().get(i));
           // Space space=new Space(this);
           // space.setLayoutParams(new FrameLayout.LayoutParams(0,50));
          //  ll.addView(space);
            System.out.println("Slika : "+i);
            Button btn =new Button(this);
            btn.setId(i);
            btn.setText("DEL");
            btn.setPadding(0,0,0,0);
            btn.setLayoutParams(new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT));
            //btn.setLayoutParams(new FrameLayout.LayoutParams(200,500));
            ll2.addView(btn); ll2.addView(img);
            ll.addView(ll2);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    da.getPlaygroundByID(getIntent().getExtras().getString("PLAYGROUND_ID","default")).getImgPathList().remove(v.getId());
                    app.save();
                    onResume();
                }
            });

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addoplaygroundmagicphoto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Add a photo onresume");

        Button button = (Button) findViewById(R.id.button5);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        System.out.println("Button5 clicked");

        startActivity(intent2);
    }
});

        permissionGranted = new PermissionGranted(this);

        if (android.os.Build.VERSION.SDK_INT >= 24) {
            permissionGranted.checkAllMagicalCameraPermission();
        }else{
            permissionGranted.checkCameraPermission();
            permissionGranted.checkReadExternalPermission();
            permissionGranted.checkWriteExternalPermission();
            permissionGranted.checkLocationPermission();
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (magicalCamera ==null) magicalCamera =  new MagicalCamera(activity_addplaygroundmagicphoto.this,RESIZE_PHOTO_PIXELS_PERCENTAGE,permissionGranted);

                    magicalCamera.takePhoto();
                    System.out.println("TakePhoto112");

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if (magicalCamera ==null)    magicalCamera =  new MagicalCamera(this,permissionGranted);
        //CALL THIS METHOD EVER IN THIS OVERRIDE FOR ACTIVATE PERMISSIONS
        magicalCamera.permissionGrant(requestCode, permissions, grantResults);
    }

    public void onPicture(View v){

        if (magicalCamera ==null) magicalCamera =  new MagicalCamera(this,permissionGranted);

        System.out.println("Klik Save magicalCamera1 method");
        magicalCamera.takePhoto();
        System.out.println("Klik Save magicalCamera2 method");
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //CALL THIS METHOD EVER
        magicalCamera.resultPhoto(requestCode, resultCode, data);

        //this is for rotate picture in this method
        //magicalCamera.resultPhoto(requestCode, resultCode, data, MagicalCamera.ORIENTATION_ROTATE_180);

        //with this form you obtain the bitmap (in this example set this bitmap in image view)
       // ivSlika.setImageBitmap(magicalCamera.getPhoto());

        //if you need save your bitmap in device use this method and return the path if you need this
        //You need to send, the bitmap picture, the photo name, the directory name, the picture type, and autoincrement photo name if           //you need this send true, else you have the posibility or realize your standard name for your pictures.
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String path = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(),"img_"+timeStamp,  true);


        if(path != null){
            //l = new Lokacija("Poimenuj ", magicalCamera.getPrivateInformation().getLatitude(), magicalCamera.getPrivateInformation().getLongitude(),app.getAll().getUserMe().getIdUser(),path,System.currentTimeMillis());
           // update(l);
            da.getPlaygroundByID(getIntent().getExtras().getString("PLAYGROUND_ID", "default")).addImgPath(path);
            System.out.println("PATHH:"+path);
            app.save();
            Toast.makeText(this, "The photo is save in device, please check this path: " + path, Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Sorry your photo dont write in devide, please contact with fabian7593@gmail and say this error", Toast.LENGTH_SHORT).show();
        }
    }

}
