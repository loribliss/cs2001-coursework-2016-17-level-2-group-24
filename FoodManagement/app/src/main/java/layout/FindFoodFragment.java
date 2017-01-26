package layout;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cs15fmk.foodmanagement.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.R.attr.id;
import static android.R.attr.name;
import static android.os.Build.VERSION_CODES.N;

public class FindFoodFragment extends Fragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    MapView mMapView;
    private GoogleMap mGoogleMap;
    private GoogleApiClient mGoogleApiClient;
    public static final int REQUEST_LOCATION = 0;
    LatLng mLatLng;
    Activity mActivity;
    private Context mContext;
    CameraUpdate cameraUpdate;
    LocationRequest mLocationRequest;
    private static final String TAG = "MyActivity";
    CharSequence mShopName;
    CharSequence mShopAddress;
    int mShopPriceLevel;
    float mRating;
    String LatLng;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_find_food, container, false);

        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        mMapView.getMapAsync(this);
        //NEED TO ADD LCOATION SETTING - https://developer.android.com/training/location/index.html
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageView imageView = (ImageView) v.findViewById(R.id.navigation);
        imageView.setOnClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.FabCurrentLocation);
        fab.setOnClickListener(this);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mActivity = getActivity();

    }

    protected synchronized void buildGoogleApiClient() {
//connect to google play service to use API's
        mGoogleApiClient = new GoogleApiClient.Builder(mActivity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        mGoogleApiClient.connect();
        Log.d(TAG, "Google API Client connected");
    }


    @Override
    public void onMapReady(GoogleMap mMap) {
        mGoogleMap = mMap;
        if (ContextCompat.checkSelfPermission(mContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Permission is granted :)");
            buildGoogleApiClient();
        } else {
            Log.d(TAG, "Permission not granted requesting");
            checkPermission();
            buildGoogleApiClient();

        }
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient,
                        builder.build());
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        /*if (mLocationRequest) {
            startLocationUpdates();
        }*/
        if (ContextCompat.checkSelfPermission(mContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            checkPermission();
            Log.d(TAG, "HAPPYT");
        }

        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            Log.d(TAG, "current location: " + mLastLocation.toString());
            mLatLng = new LatLng(mLastLocation.getLatitude(),
                    mLastLocation.getLongitude());
            LatLng = mLastLocation.toString();
        }


        LatLng world = new LatLng(51.5074, 0.1278);
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(world, 3);
        mGoogleMap.animateCamera(cameraUpdate);
        Log.d(TAG, "Moving Camera");
    }

   /* protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }*/


    @Override
    public void onResume() {
        super.onResume();

        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            Log.d(TAG, "Starting Client");
            mGoogleApiClient.connect();

        }
    }

    public void onStop() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
            super.onStop();
        }
    }


    public void checkPermission() {

        if (ContextCompat.checkSelfPermission(mContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mActivity,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
            Log.d(TAG, "Requesting fine location permission.");

        }
        Log.d(TAG, "Permission Granted");

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "Permission GRANTED");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    Log.d(TAG, "Permission Denied");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(mContext, "onConnectionSuspended", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Connection to Google API suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(mContext, "onConnectionFailed", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Failed connecting to Google APi");
        buildGoogleApiClient();

    }

    public void onClick(View v) {
        if (v.getId() == R.id.FabCurrentLocation) {
            mGoogleMap.addMarker(new MarkerOptions().position(mLatLng));
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mLatLng, 18);
            mGoogleMap.addMarker(new MarkerOptions().position(mLatLng));
            mGoogleMap.animateCamera(cameraUpdate);
            Toast.makeText(mContext, "Getting Current Location", Toast.LENGTH_SHORT).show();
            //draws circle
            Circle circle = mGoogleMap.addCircle(new CircleOptions()
                    .center(mLatLng)
                    .radius(50)
                    .strokeColor(Color.BLACK));
        } else if (v.getId() == R.id.navigation) {
            onClickMap(v);
        }

    }

    public void placeLikelihoodBuffer(View v) {
        Log.d(TAG, "Calling onPlaceLikelihood");
        checkPermission();
        if (mGoogleApiClient != null) {
            PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                    .getCurrentPlace(mGoogleApiClient, null);
            result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
                @Override
                public void onResult(PlaceLikelihoodBuffer likelyPlaces) {
                    for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                        Log.i(TAG, String.format("Place '%s' has likelihood: %g",
                                placeLikelihood.getPlace().getName(),
                                placeLikelihood.getLikelihood()));
                    }
                    likelyPlaces.release();
                }
            });
        }

    }

    public void onClickMap(View v) {
        Toast.makeText(mContext, "Opening Map", Toast.LENGTH_SHORT).show();
        googleMapIntent(v);
    }

    public void googleMapIntent(View v) {
        ImageView navigation = (ImageView) v.findViewById(R.id.navigation);

        Uri gmmIntentUri = Uri.parse("geo:" + LatLng);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(mActivity.getPackageManager()) != null) {
            startActivity(mapIntent);

        }

    }

    protected void createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

}






