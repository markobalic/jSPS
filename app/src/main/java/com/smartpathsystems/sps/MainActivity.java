package com.smartpathsystems.sps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static int counter = 0;
    private Marker marker1;
    private Marker marker2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng latLng) {
                //static int counter = 0;

                if(counter < 2) {

                    if(counter == 0)
                        marker1 = mMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                    else
                        marker2 = mMap.addMarker(new MarkerOptions().position(latLng));

                    Toast.makeText(MainActivity.this, "Marker created", Toast.LENGTH_SHORT).show();
                    counter++;
                }
                else
                    Toast.makeText(MainActivity.this, "Both markers already created", Toast.LENGTH_SHORT).show();
                }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            /*@Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(MainActivity.this, "Ne upiri pinpoint", Toast.LENGTH_SHORT).show();
                Marker
                return true;*/
            @Override
            //nakon uklanjanja drugog, sljedeci dodan ce biti zelen!
            public boolean onMarkerClick(Marker marker) {
                marker.remove();
                if(marker2.isVisible() == true)
                    counter = 0;
                else
                    counter = 1;
                Toast.makeText(MainActivity.this, "Marker removed", Toast.LENGTH_SHORT).show();
                //counter--;
                return true;
            }
        });
        /*mMap.setOnMarkerDragListener({

        });*/

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Ako lokacije nisu dopustene, salje se prompt window sa pitanjem da se dopuste
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, 5);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 6);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {
                    Manifest.permission.INTERNET
            }, 7);
            mMap.setMyLocationEnabled(true);
        }
        else
            mMap.setMyLocationEnabled(true);
    }
}
