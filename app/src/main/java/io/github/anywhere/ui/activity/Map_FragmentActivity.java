package io.github.anywhere.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Map;

import io.github.anywhere.R; ;
import io.github.anywhere.ui.utils.DataModel;
import io.github.anywhere.ui.utils.Data_Model;
import io.github.anywhere.ui.utils.Location_Criteria;

/**
 * Created by PCPC on 2017-06-04.
 */

public class Map_FragmentActivity extends Fragment implements OnMapReadyCallback {

    private ArrayList<DataModel> sampleList = new ArrayList();
    private GoogleMap googleMap;
    private MapView mapView;
    private final int REQUEST_PERMISSION = 1000;
    private boolean mapsSupported = true;
    private double lat;
    private double lon;
    private boolean gpsEnabled = false;
    private LocationManager locationManager;
    private Marker marker;

    public Map_FragmentActivity(ArrayList<DataModel>dataModelArrayList){
        sampleList = dataModelArrayList;
    }

    public void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION);

        } else {

            gpsEnabled = true;
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, REQUEST_PERMISSION);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.item_map_fragment, container, false);
        requestLocationPermission();
        Log.i("MAP  Array: " , sampleList.get(0).getName());
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        if (gpsEnabled == true) {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            }
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, // 등록할 위치제공자
                    1000, // 통지사이의 최소 시간간격 (miliSecond)
                    0, // 통지사이의 최소 변경거리 (m)
                    locationListener);
            locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                    1000, // 통지사이의 최소 시간간격 (miliSecond)
                    0, // 통지사이의 최소 변경거리 (m)
                    locationListener);
        }
        return view;
    }


    private void InitializeMap() {
        if (googleMap == null && mapsSupported) {

            mapView = (MapView) getActivity().findViewById(R.id.map);

            mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap mGoogleMap) {
                    googleMap = mGoogleMap;
                    if (gpsEnabled) {
                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        }
                        googleMap.setMyLocationEnabled(true);
                        LatLng currentLocation;
                        currentLocation = Location_Criteria.getMyLocation(getActivity());
                        googleMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(lat,lon)));
                        googleMap.setInfoWindowAdapter(infoWindowAdapter);
                    }
                }
            });
        }
    }





    GoogleMap.InfoWindowAdapter infoWindowAdapter = new GoogleMap.InfoWindowAdapter() {

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            View Ballon_View = getActivity().getLayoutInflater().inflate(R.layout.item_map_baloon, null);


            for (int i = 0; i < sampleList.size(); i++) //입력된 정보 모음 만큼 반복
            {
                if (marker.getTitle().equals(sampleList.get(i).getName()))//마커 생성하면서 입력한 타이틀(링크)로 탐색
                {
                    ((TextView) Ballon_View.findViewById(R.id.Name)).setText("이름 : "+sampleList.get(i).getName()+"\n");
                    ((TextView) Ballon_View.findViewById(R.id.host)).setText("주소: " +sampleList.get(i).getAddress()+"\n");
                    ((TextView) Ballon_View.findViewById(R.id.telnum)).setText("전화번호: "+sampleList.get(i).getTel_num()+"\n");
                    ((TextView) Ballon_View.findViewById(R.id.like_count)).setText("좋아요!:" + sampleList.get(i).getLike_cnt()+"\n");

                }
            }
            return Ballon_View;
        }
    };

    @Override
    public void onMapReady(GoogleMap googleMap) {
        InitializeMap();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MapsInitializer.initialize(getActivity().getApplicationContext());
        if (mapView != null)
            mapView.onCreate(savedInstanceState);
        InitializeMap();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        if (gpsEnabled == true) {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            }
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, // 등록할 위치제공자
                    1000, // 통지사이의 최소 시간간격 (miliSecond)
                    0, // 통지사이의 최소 변경거리 (m)
                    locationListener);
            locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                    1000, // 통지사이의 최소 시간간격 (miliSecond)
                    0, // 통지사이의 최소 변경거리 (m)
                    locationListener);
        }    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        locationManager.removeUpdates(locationListener);

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            lat = location.getLatitude();
            lon = location.getLongitude();
            CircleOptions circleOptions = new CircleOptions()
                    .center(new LatLng(lat, lon))
                    .radius(1000)
                    .strokeColor(Color.argb(0 , 96, 135, 0));


            Circle circle = googleMap.addCircle(circleOptions);


            for (int i = 0; i < sampleList.size(); i++) {
                float[] distance = new float[2];
                Location.distanceBetween(sampleList.get(i).getLat(), sampleList.get(i).getLon(),
                        circle.getCenter().latitude, circle.getCenter().longitude, distance);

                if (distance[0] > circle.getRadius()) {
                    marker.remove();
                } else {
                    LatLng currnet = new LatLng(sampleList.get(i).getLat(), sampleList.get(i).getLon());
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.title(sampleList.get(i).getName());
                    markerOptions.position(currnet);

                    marker = googleMap.addMarker(markerOptions);
                }
            }
            }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }


    };
}

