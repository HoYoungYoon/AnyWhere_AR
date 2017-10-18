package io.github.anywhere.ui.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.maps.model.LatLng;

import io.github.anywhere.ui.activity.Map_FragmentActivity;

/**
 * Created by PCPC on 2017-06-04.
 */

public class Location_Criteria {
    static LatLng result = null;
    LocationListener locationListener;
    public static LatLng getMyLocation(Context context) {

        Criteria criteria = null;


        if (criteria == null) {


            criteria = new Criteria();

            criteria.setAccuracy(Criteria.ACCURACY_COARSE); // 정확도

            criteria.setAltitudeRequired(false); // 고도

            criteria.setBearingRequired(false); // ..

            criteria.setSpeedRequired(false); // 속도

        }


        LocationManager locationManager;


        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);


        //true=현재 이용가능한 공급자 제한

        String provider = locationManager.getBestProvider(criteria, true);// "gps";


        if (provider == null)

            provider = "network";

        // for ActivityCompat#requestPermissions for more details.     }
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
        Location location = locationManager.getLastKnownLocation(provider);

        if(location==null){


        }else{

            result = new LatLng(location.getLatitude(), location.getLongitude());


        }

        return result;

    }


}
