package com.example.xdwonder.igriscavblizini;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;

import java.util.ArrayList;

import static com.example.xdwonder.igriscavblizini.ApplicationMy.da;

public class osmoidmap extends AppCompatActivity {

     boolean xxx=true;

    MapView mMapView;
    ScaleBarOverlay mScaleBarOverlay;
    DisplayMetrics dm;
    ArrayList<OverlayItem> items;
    private ItemizedOverlayWithFocus<OverlayItem> mMyLocationOverlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osmoidmap);
        Context ctx = getApplicationContext();
        //important! set your user agent to prevent getting banned from the osm servers
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        setContentView(R.layout.activity_osmoidmap);







        Button button1 = (Button) findViewById(R.id.button6);
        Button button2 = (Button) findViewById(R.id.button7);
        Button button3 = (Button) findViewById(R.id.button8);
        final Intent intent1 = new Intent(this, activity_addplayground.class);
        final Intent intent2 = new Intent(this, activity_search.class);
        final Intent intent3 = new Intent(this, activity_addplayground.class);

        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(intent1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(intent2);

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                startActivity(intent3);
            }
        });

        mMapView = (MapView) findViewById(R.id.map);
        mMapView.setTileSource(TileSourceFactory.MAPNIK);
        mMapView.setBuiltInZoomControls(false);
        mMapView.setMultiTouchControls(true);
        IMapController mapController = mMapView.getController();
        mapController.setZoom(17);
        GeoPoint startPoint;
        if(getIntent().hasExtra("LongX"))
        {
            startPoint= new GeoPoint(getIntent().getExtras().getDouble("LongX",0),getIntent().getExtras().getDouble("LatY",0));//46.25139,15.2568);
        }else {
            startPoint = new GeoPoint(46.5589835, 15.6386926);//46.25139,15.2568);
        }  mapController.setCenter(startPoint);
        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        mScaleBarOverlay = new ScaleBarOverlay(mMapView);
        mScaleBarOverlay.setCentred(true);
//play around with these values to get the location on screen in the right place for your applicatio
        mScaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        mMapView.getOverlays().add(this.mScaleBarOverlay);


        MapUpdate();
/*
    Bitmap square1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.basketball);
    Bitmap square2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.basketball);
    Bitmap square3 = BitmapFactory.decodeResource(this.getResources(), R.drawable.basketball);
    Bitmap square4 = BitmapFactory.decodeResource(this.getResources(), R.drawable.basketball);

    Bitmap big = Bitmap.createBitmap(square1.getWidth(), square1.getHeight() * 4, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(big);
    canvas.drawBitmap(square1, 0, 0, null);
    canvas.drawBitmap(square2, 0, square1.getHeight(), null);
    canvas.drawBitmap(square3, 0, square1.getHeight()*2, null);
    canvas.drawBitmap(square4, 0, square1.getHeight()*3, null);

    Drawable d = new BitmapDrawable(getResources(), big);




        OverlayItem myLocationOverlayItem = new OverlayItem("Here", "Current Position", new GeoPoint(46.5589835,15.6386926));
        myLocationOverlayItem.setMarker(d);
       // Drawable myCurrentLocationMarker = this.getResources().getDrawable(R.drawable.common_google_signin_btn_icon_light);
       // myLocationOverlayItem.setMarker(myCurrentLocationMarker);

        items.add(myLocationOverlayItem);


*/
        mMyLocationOverlay = new ItemizedOverlayWithFocus<OverlayItem>(items,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        IMapController mapController = mMapView.getController();
                        mapController.setCenter(item.getPoint());
                        mapController.zoomTo(mMapView.getMaxZoomLevel());
                        return true;
                    }

                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        Toast.makeText(osmoidmap.this
                                ,
                                "Item '" + item.getTitle() + "' (index=" + index
                                        + ") got long pressed", Toast.LENGTH_LONG).show();
                        return false;
                    }
                }, this);


        mMyLocationOverlay.setFocusItemsOnTap(true);
        mMapView.getOverlays().add(mMyLocationOverlay);
        //Track path
       /* GooglePolylineOverlay gp = new GooglePolylineOverlay(Color.RED,8);
        gp.addPoints( new GeoPoint(46.25139,15.2568), new GeoPoint(46.25139,15.2578), new GeoPoint(46.25300,15.2572),new GeoPoint(46.25139,15.2568));
        mMapView.getOverlays().add(gp);*/
    }
    public void onResume(){
        MapUpdate();
        super.onResume();

        //super.onCreate();

        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
    }





    void MapUpdate(){
        System.out.println("VASS");
        items = new ArrayList<OverlayItem>();
        // items.add(new OverlayItem("Smeti2", "Veliko smeti", new GeoPoint(46.25139,15.2568)));
        // items.add(new OverlayItem("Smeti", "Veliko smeti3", new GeoPoint(46.25139,15.2578)));

        for(int i=0;i<ApplicationMy.da.getPlaygroundsList().size();i++){

            Bitmap big;
            Canvas canvas;
            Bitmap tmp;
            if(ApplicationMy.da.getPlaygroundsList().get(i).getTagList().size()==0) {
                tmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.tag_generic);
                big = Bitmap.createBitmap(tmp.getWidth(), tmp.getHeight(), Bitmap.Config.ARGB_8888);
                System.out.println("WIDTH="+tmp.getWidth());
                canvas= new Canvas(big);
            }
            else{
                big = Bitmap.createBitmap(360, 336 * ApplicationMy.da.getPlaygroundsList().get(i).getTagList().size()-(150*(ApplicationMy.da.getPlaygroundsList().get(i).getTagList().size()-1)), Bitmap.Config.ARGB_8888);
                canvas= new Canvas(big);
            }
            for(int j=0;j<ApplicationMy.da.getPlaygroundsList().get(i).getTagList().size();j++)
            {
                // tv3.setText(tv3.getText()+ ApplicationMy.da.getPlaygroundByID(getIntent().getExtras().getString("EXTRA_SESSION_ID","default")).getTagList().get(j).getIme()+", ");
                tmp=BitmapFactory.decodeResource(this.getResources(), R.drawable.tag_generic);;
                switch(ApplicationMy.da.getPlaygroundsList().get(i).getTagList().get(j).getIme()){
                    case "basketball":
                        tmp=BitmapFactory.decodeResource(this.getResources(), R.drawable.basketball);
                        break;
                    case "football":
                        tmp=BitmapFactory.decodeResource(this.getResources(), R.drawable.soccer);
                        break;
                    case "playground":
                        tmp=BitmapFactory.decodeResource(this.getResources(), R.drawable.playground);
                        break;
                    case "volleyball":
                        tmp=BitmapFactory.decodeResource(this.getResources(), R.drawable.volleyball);
                        break;
                    case "tennis":
                        tmp=BitmapFactory.decodeResource(this.getResources(), R.drawable.tennis);
                        break;
                    case "golf":
                        tmp=BitmapFactory.decodeResource(this.getResources(), R.drawable.golf);
                        break;
                }

                if(j==0)
                    canvas.drawBitmap(tmp, 0, tmp.getHeight()*j, null);
                else
                    canvas.drawBitmap(tmp, 0, tmp.getHeight()*j-(150*j), null);
                System.out.println("Adding img "+tmp.toString()+". j="+j+" For Igrisce "+i);
            }

            // items.add(new OverlayItem("Igrisce "+i, "Opis", new GeoPoint(ApplicationMy.da.getPlaygroundsList().get(i).getY(),ApplicationMy.da.getPlaygroundsList().get(i).getX())));
            Drawable d = new BitmapDrawable(getResources(), big);
            d.setBounds(0,0,0,0);
            OverlayItem myLocationOverlayItem = new OverlayItem("Igrisce "+i, "Jači opis", new GeoPoint(ApplicationMy.da.getPlaygroundsList().get(i).getY(),ApplicationMy.da.getPlaygroundsList().get(i).getX()));
            myLocationOverlayItem.setMarker(d);
            // Drawable myCurrentLocationMarker = this.getResources().getDrawable(R.drawable.common_google_signin_btn_icon_light);
            // myLocationOverlayItem.setMarker(myCurrentLocationMarker);

            items.add(myLocationOverlayItem);
            System.out.println("X:"+ApplicationMy.da.getPlaygroundsList().get(i).getY()+"Y:"+ApplicationMy.da.getPlaygroundsList().get(i).getX());
        }

    }
}
