package layout;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs15fmk.foodmanagement.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
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

import java.util.ArrayList;

import static com.example.cs15fmk.foodmanagement.R.id.navigation;

public class FindFoodFragment extends Fragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {
    public static final int REQUEST_LOCATION = 0;
    private static final String TAG = "MyActivity";
    TextView asd;
    MapView mMapView;
    LatLng mLatLng;
    Activity mActivity;
    CameraUpdate cameraUpdate;
    LocationRequest mLocationRequest;
    Marker marker;
    CharSequence mShopName;
    CharSequence mShopAddress;
    int mShopPriceLevel;
    float mShopRating;
    ArrayList<Integer> filter = new ArrayList<>();
    String LatLng;
    private GoogleMap mGoogleMap;
    private GoogleApiClient mGoogleApiClient;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_find_food, container, false);

        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        mMapView.getMapAsync(this);
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
       /* Listen for click for google map intent*/
        ImageView imageView = (ImageView) v.findViewById(navigation);
        imageView.setOnClickListener(this);
        /* Listen for click for Floating action button*/
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
        /*connects to google play service to use API's*/
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


        locationSetting();
    }

    protected void createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public void locationSetting() {
        final int REQUEST_CHECK_SETTINGS = 0;
        createLocationRequest();
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient,
                        builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates locationSettingsStates = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can
                        // initialize location requests here.

                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied, but this can be fixed
                        // by showing the user a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(
                                    mActivity,
                                    REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;

                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }

            }
        });
    }

    @Override
    public void onConnected(Bundle connectionHint) {

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
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mLatLng, 18);
            //does NOT WORK
            if (marker == null) {
                marker = mGoogleMap.addMarker(new MarkerOptions().position(mLatLng));
            }
            mGoogleMap.animateCamera(cameraUpdate);
            Toast.makeText(mContext, "Getting Current Location", Toast.LENGTH_SHORT).show();
            //draws circle
            Circle circle = mGoogleMap.addCircle(new CircleOptions()
                    .center(mLatLng)
                    .radius(50)
                    .strokeColor(Color.BLACK));
        } else if (v.getId() == navigation) {
            onClickMap(v);
        }
        if (ContextCompat.checkSelfPermission(mContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            placeLikelihoodBuffer(v);
        }

    }

    public void placeLikelihoodBuffer(final View v) {

        Log.d(TAG, "Calling onPlaceLikelihood");
        checkPermission();
        if (mGoogleApiClient != null) {
            Log.d(TAG, "GETTING NEARBY ");
            PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                    .getCurrentPlace(mGoogleApiClient, null);
            result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
                @Override
                public void onResult(PlaceLikelihoodBuffer likelyPlaces) {
                    for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                        //for(int i=0;i<filter.size();i++){
                        Log.i(TAG, String.format("Place '%s' has likelihood: %g",
                                placeLikelihood.getPlace().getName(),
                                placeLikelihood.getLikelihood()));

                        //   }
                        final CharSequence mShopName = likelyPlaces.get(1).getPlace().getName();
                        final CharSequence mShopAddress = likelyPlaces.get(1).getPlace().getAddress();
                        final int mShopPriceLevel = likelyPlaces.get(1).getPlace().getPriceLevel();
                        final float mShopRating = likelyPlaces.get(1).getPlace().getRating();
                        mLatLng = likelyPlaces.get(1).getPlace().getLatLng();
                    }
                    //for(int i=0;i<10;i++) {


                    Log.d(TAG, "dasdasd " + mShopName);
                    Log.d(TAG, "dasdasd " + mShopAddress);
                    Log.d(TAG, "dasdasd " + mShopPriceLevel);
                    Log.d(TAG, "dasdasd " + mShopRating);
                    apple(mShopName, mShopAddress, mShopRating, mShopPriceLevel);
                    // }

                    likelyPlaces.release();
                }

            });
        }

    }

    public void apple(CharSequence mShopName, CharSequence mShopAddress, Float mShopRating, int mShopPrice) {

        asd = (TextView) getView().findViewById(R.id.shopName);
        asd.setText(mShopName);
        asd = (TextView) getView().findViewById(R.id.shopAddress);
        asd.setText(mShopAddress);
        asd = (TextView) getView().findViewById(R.id.shopRating);
        asd.setText(String.valueOf(mShopRating));
        /*asd = (TextView) getView().findViewById(R.id.shopPrice);
        asd.setText(mShopPrice);*/

    }


    public void onClickMap(View v) {
        Toast.makeText(mContext, "Opening Map", Toast.LENGTH_SHORT).show();
        googleMapIntent(v);
    }

    public void googleMapIntent(View v) {
        mLatLng.toString();
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + mLatLng);
        Log.d(TAG, "you " + mLatLng);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(mActivity.getPackageManager()) != null) {
            startActivity(mapIntent);

        }

    }


}







