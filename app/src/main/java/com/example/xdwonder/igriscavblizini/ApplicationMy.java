package com.example.xdwonder.igriscavblizini;

import android.app.Application;
import android.location.Location;

import java.io.File;

/**
 * Created by xdwonder on 4.3.2017.
 */

public class ApplicationMy extends Application {
    static DataAll da;
    private static final String DATA_MAP = "igriscadatamap";
    private static final String FILE_NAME = "igrisca.json";
    private Location mLastLocation;

    @Override
    public void onCreate(){
        mLastLocation=null;
       // da=DataAll.scenarijA();
        super.onCreate();
        if (!load()) // t kliče funkcijo ?? ?al ne
            da = DataAll.scenarijA();

    }

    public Location getLastLocation() {
        return mLastLocation;
    }

    public void setLastLocation(Location mLastLocation) {
        this.mLastLocation = mLastLocation;
    }
    public boolean hasLocation() {
        if (mLastLocation==null) return false;
        return true;
    }

    public DataAll getAll() {
        return da;
    }


    public boolean save() {
        File file = new File(this.getExternalFilesDir(DATA_MAP), ""
                + FILE_NAME);

        return ApplicationJson.save(da,file);
    }
    public boolean load(){
        File file = new File(this.getExternalFilesDir(DATA_MAP), ""
                + FILE_NAME);
        DataAll tmp = ApplicationJson.load(file);
        if (tmp!=null) da = tmp;
        else return false;
        return true;
    }

}
