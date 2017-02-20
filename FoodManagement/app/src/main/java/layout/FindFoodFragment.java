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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
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
import java.util.List;

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
    final static CharSequence mShopName = "";
    final static CharSequence mShopAddress = "";
    final static int mShopPriceLevel = 0;
    final static float mShopRating = 0;
    ArrayList<Integer> filter = new ArrayList<>();
    String LatLng = null;
    private GoogleMap mGoogleMap;
    private GoogleApiClient mGoogleApiClient;
    private Context mContext;
    double value;
    List<CharSequence> arrayName = new ArrayList<CharSequence>();
    List<CharSequence> arrayAddress = new ArrayList<CharSequence>();
    List<Integer> arrayPrice = new ArrayList<Integer>();
    List<Float> arrayRating = new ArrayList<Float>();
    List<LatLng> arrayLatLng = new ArrayList<LatLng>();

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
            Log.d(TAG, "Error: " + e.toString());
            e.printStackTrace();
        }
       /* Listens for click on google map intent*/
        ImageView imageView = (ImageView) v.findViewById(navigation);
        imageView.setOnClickListener(this);
        /* Listen for click for Floating action button*/
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.FabCurrentLocation);
        fab.setOnClickListener(this);
//check when user click on textfield
        radius(v);


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

    protected static void createLocationRequest() {
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
                == PackageManager.PERMISSION_GRANTED) {


            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (mLastLocation != null) {
                mLatLng = new LatLng(mLastLocation.getLatitude(),
                        mLastLocation.getLongitude());
                LatLng = mLastLocation.toString();
            }
            LatLng london = new LatLng(51.509865, -0.118092);
            cameraUpdate = CameraUpdateFactory.newLatLngZoom(london, 10);
            mGoogleMap.animateCamera(cameraUpdate);

        } else {
            checkPermission();
            onConnected(connectionHint);
        }
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
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
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
        double radius = 50;
        if (v.getId() == R.id.FabCurrentLocation) {
            Log.d(TAG, "Moving camera");
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mLatLng, 18);

            if (mGoogleMap == null) {
                mGoogleMap.addMarker(new MarkerOptions().position(mLatLng));
                Log.d(TAG, "ADDING MARKER");
            } else {
                mGoogleMap.clear();
                Log.d(TAG, "REMOVING MARKER");
                mGoogleMap.addMarker(new MarkerOptions().position(mLatLng));
            }
            mGoogleMap.animateCamera(cameraUpdate);
            Toast.makeText(mContext, "Getting Current Location", Toast.LENGTH_SHORT).show();
            //draws circle
            Circle circle = mGoogleMap.addCircle(new CircleOptions()
                    .center(mLatLng)
                    .radius(radius)
                    .fillColor(Color.parseColor("#332196F3"))
                    .strokeColor(1));

        } else if (v.getId() == navigation) {
            onClickMap(mLatLng);
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

                        Log.i(TAG, String.format("Place '%s' has likelihood: %g",
                                placeLikelihood.getPlace().getName(),
                                placeLikelihood.getLikelihood()));
                        for (int i = 0; i < 10; i++) {

                            CharSequence mShopName = likelyPlaces.get(i).getPlace().getName();

                            CharSequence mShopAddress = likelyPlaces.get(i).getPlace().getAddress();

                            int mShopPriceLevel = likelyPlaces.get(i).getPlace().getPriceLevel();

                            float mShopRating = likelyPlaces.get(i).getPlace().getRating();

                            mLatLng = likelyPlaces.get(i).getPlace().getLatLng();
                            Log.d(TAG, "Shop name " + mShopName);
                            Log.d(TAG, "Shop Address " + mShopAddress);
                            Log.d(TAG, "Shop price level " + mShopPriceLevel);
                            Log.d(TAG, "Shop Rating " + mShopRating + "\n");
                            if (i == 1) {
                                arrayName.add(mShopName);
                                arrayAddress.add(mShopAddress);
                                arrayPrice.add(mShopPriceLevel);
                                arrayRating.add(mShopRating);
                                arrayLatLng.add(mLatLng);

                                slideUpPanelData();

                            }

                        }
                    }

                    likelyPlaces.release();
                }

            });
        }

    }

    public void slideUpPanelData() {
        String pound = "\u00a3";
        for (int i = 0; i < arrayName.size(); i++) {
            asd = (TextView) getView().findViewById(R.id.shopName);
            asd.setText(arrayName.get(i));
            asd = (TextView) getView().findViewById(R.id.shopAddress);
            asd.setText(arrayAddress.get(i));
            RatingBar saasd = (RatingBar) getView().findViewById(R.id.shopRating);
            saasd.setRating(arrayRating.get(i));
if(mShopPriceLevel>0) {
    asd = (TextView) getView().findViewById(R.id.shopPrice);
    String multiLineText = "\u00a3";
    multiLineText = new String(new char[arrayPrice.get(i)]).replace("\0", pound);
    asd.setText(multiLineText);
}
            else
{
    asd.setText("");
}
           /* asd = (TextView) getView().findViewById(R.id.shopDistance);
            asd.setText(arrayLatLng.get(i).toString());
            https://developers.google.com/maps/documentation/distance-matrix/intro*/

        }
    }


    public void onClickMap(LatLng mLatLng) {
        Toast.makeText(mContext, "Opening Map", Toast.LENGTH_SHORT).show();
        googleMapIntent(mLatLng);
    }

    public void googleMapIntent(LatLng mLatLng) {
        mLatLng.toString();
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + mLatLng);
        Log.d(TAG, "you " + mLatLng);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(mActivity.getPackageManager()) != null) {
            startActivity(mapIntent);

        }

    }

    public void radius(View v) {

        EditText radius = (EditText) v.findViewById(R.id.radius);
        radius.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() ==1){
                    Log.d(TAG, "WAITING"  + s.length());
                    radius.setText(" mile");
                }
                // TODO Auto-generated method stub
            }
        });
radius.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.d(TAG, "WAITING");
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode) == KeyEvent.KEYCODE_ENTER) {
                    String radiusText  = radius.getText().toString();
                     Toast.makeText(mContext, radiusText, Toast.LENGTH_LONG).show();

                    Log.d(TAG, "problem " + radiusText);
                    String[] part = radiusText.split("(?<=\\D)(?=\\d)");
                    String you = (part[0].toString());
                    //int mo = Integer.parseInt(part[1]);
                    Log.d(TAG, "part 0  :  " + you);
                    Log.d(TAG, "part 1 :  " + you.length());
                    value = Integer.parseInt(you.substring(0, you.length()-5));
                    Log.d(TAG, "part 5 :  " + value);
                    drawCircle();

                    return true;


                }


                return false;}
            });
    }

    public void drawCircle() {
        Log.d(TAG, "DRAWING CIRCLE ");
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mLatLng, 18);

        if (mGoogleMap == null) {
            mGoogleMap.addMarker(new MarkerOptions().position(mLatLng));
            Log.d(TAG, "ADDING MARKER");
        } else {
            mGoogleMap.clear();
            Log.d(TAG, "REMOVING MARKER");
            mGoogleMap.addMarker(new MarkerOptions().position(mLatLng));
        }
        mGoogleMap.animateCamera(cameraUpdate);
        //draws circle
        //converts miles into meters
        value = value * 1609.344;
        Circle circle = mGoogleMap.addCircle(new CircleOptions()
                .center(mLatLng)
                .radius(value)
                .fillColor(Color.parseColor("#332196F3"))
        .strokeColor(1));
        Log.d(TAG, mLatLng + "ADDD");
    }
}

