package com.example.hossam.parashotApp.presentation.screens.getUserLocation;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.helper.LocationAddressHelper;
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
import com.google.android.gms.maps.model.MarkerOptions;

public class GetUserLOcationActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

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
    String address;
    Double longitude;
    Double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_location);

        mMarker = findViewById(R.id.mMarker);
        try {
            // Loading map
            MapFragment mapFragment;
            ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.usermap)).getMapAsync(this);
         //   mapFragment.getMapAsync(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        LocationManager locationManager;
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                this.mMap = googleMap;
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mMap.getUiSettings().setZoomControlsEnabled(true);
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
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();

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
        mMarker.setVisibility(View.VISIBLE);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mlatitude, mLogitude), 16.0f));
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            public void onCameraChange(CameraPosition cameraPosition) {
                latitude = cameraPosition.target.latitude;
                longitude = cameraPosition.target.longitude;
                address = LocationAddressHelper.getLocationAddress(getBaseContext(), cameraPosition.target.latitude, cameraPosition.target.longitude);
                if (address == null) {
                    Toast.makeText(GetUserLOcationActivity.this, getString(R.string.cannot_fetch_address), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GetUserLOcationActivity.this, address, Toast.LENGTH_SHORT).show();
                   // addresss.setText(address);
                    //send_lat = String.valueOf(latitude);
                    //send_lng = String.valueOf(longitude);
                }
            }
        });
    }


}
