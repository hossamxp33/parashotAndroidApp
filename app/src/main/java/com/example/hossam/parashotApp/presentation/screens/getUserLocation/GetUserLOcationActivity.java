package com.example.hossam.parashotApp.presentation.screens.getUserLocation;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.hossam.parashotApp.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetUserLOcationActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener, View.OnClickListener {

    protected Location location,myLocation;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;
    GoogleMap m_map;
    ImageView mMarker;
    private LatLng curretLatLng;
    String address="";
    Double longitude,latitude;


    TextView address_txt,latlog_txt,map_shap,satalite,hagintxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_location);

        address_txt = findViewById(R.id.address_txt);
        latlog_txt = findViewById(R.id.latlog_txt);
        map_shap = findViewById(R.id.map_shap);
        satalite = findViewById(R.id.satalite);
        hagintxt = findViewById(R.id.hagintxt);

        map_shap.setOnClickListener(this);
        satalite.setOnClickListener(this);
        hagintxt.setOnClickListener(this);

            ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.usermap)).getMapAsync(this);

        if (servicesOK()) {
            buildGoogleApi();
            mGoogleApiClient.connect();
        }
    }

    protected synchronized void buildGoogleApi() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    public boolean servicesOK() {
        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable,this, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "please check you opened GPS", Toast.LENGTH_LONG).show();
        }
        return false;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        LocationManager locationManager;
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                if (currentLatitude != 0d && currentLongitude != 0d) {
                    goToAddress(currentLatitude, currentLongitude);
                }
            }
            else {
                showAlertDialog();
            }
        }
    }

    private void showAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("Device GPS is currently OFF. Please Turn ON.");
        builder.setNegativeButton("not now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        } );
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 111);
            }
        }).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111) {
                    ((SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.usermap)).getMapAsync(this);

                    if (servicesOK()) {
                        buildGoogleApi();
                        mGoogleApiClient.connect();
                    }
        }
    }


    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
        myLocation = location;
        curretLatLng = new LatLng(location.getLatitude(), location.getLongitude());
      }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //// permission
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //// permission

        location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);
        LocationServices
                .FusedLocationApi
                .requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        if (location != null) {
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();
            myLocation = location;

            if (mMap!=null)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLatitude, currentLongitude), 16.0f));
            goToAddress(currentLatitude, currentLongitude);
            curretLatLng = new LatLng(currentLatitude,currentLongitude);
        }
    }


    private void goToAddress(Double mlatitude, Double mLogitude) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mlatitude, mLogitude), 16.0f));
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            public void onCameraChange(CameraPosition cameraPosition) {
                latitude = cameraPosition.target.latitude;
                longitude = cameraPosition.target.longitude;
                latlog_txt.setText(latitude+","+longitude);
                address =   getLocationAddress(latitude,longitude);
              //  address = LocationAddressHelper.getLocationAddress(getBaseContext(), cameraPosition.target.latitude, cameraPosition.target.longitude);
                if (address == null) {
                    Toast.makeText(GetUserLOcationActivity.this, getString(R.string.cannot_fetch_address), Toast.LENGTH_SHORT).show();
                } else {
                    address_txt.setText(address);
                }
            }
        });
    }


    public  String getLocationAddress(Double Lat, Double Lng){
        String address="";
        Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
        try {
            List<Address> listAddresses = geocoder.getFromLocation(Lat,Lng, 1);
            if (null != listAddresses && listAddresses.size() > 0) {
                String _Location = "";
                if(listAddresses.get(0).getMaxAddressLineIndex()>0){
                    for (int i = 0; i < listAddresses.get(0).getMaxAddressLineIndex(); i++) {
                        _Location += listAddresses.get(0).getAddressLine(i) + ", ";
                    }
                }
                else {
                    _Location += listAddresses.get(0).getAddressLine(0);
                }

                address = _Location;
            }

            return address;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.map_shap:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                map_shap.setBackgroundColor(ContextCompat.getColor(this, R.color.maptextcolor));
                satalite.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
                hagintxt.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
                ////////////// text color
                map_shap.setTextColor(ContextCompat.getColor(this, R.color.white));
                satalite.setTextColor(ContextCompat.getColor(this, R.color.maptextcolor));
                hagintxt.setTextColor(ContextCompat.getColor(this, R.color.maptextcolor));
                break;

            case R.id.hagintxt:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                map_shap.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
                satalite.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
                hagintxt.setBackgroundColor(ContextCompat.getColor(this, R.color.maptextcolor));
                ////////////// text color
                map_shap.setTextColor(ContextCompat.getColor(this, R.color.maptextcolor));
                satalite.setTextColor(ContextCompat.getColor(this, R.color.maptextcolor));
                hagintxt.setTextColor(ContextCompat.getColor(this, R.color.white));
                break;

            case R.id.satalite:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                map_shap.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
                satalite.setBackgroundColor(ContextCompat.getColor(this, R.color.maptextcolor));
                hagintxt.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
                ////////////// text color
                map_shap.setTextColor(ContextCompat.getColor(this, R.color.maptextcolor));
                satalite.setTextColor(ContextCompat.getColor(this, R.color.white));
                hagintxt.setTextColor(ContextCompat.getColor(this, R.color.maptextcolor));
                break;
        }
    }

    public void finish(View view) {
        if (!address.matches("")) {
            Intent data = new Intent();
            data.putExtra("full_address", address);
            data.putExtra("user_late", latitude + "");
            data.putExtra("user_lang", longitude + "");
            setResult(RESULT_OK, data);
            finish();
        }
        Snackbar.make(findViewById(R.id.usermap),getText(R.string.noaddress),Snackbar.LENGTH_LONG).show();

    }
}
