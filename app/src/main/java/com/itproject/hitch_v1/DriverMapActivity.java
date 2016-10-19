package com.itproject.hitch_v1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Step;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.itproject.hitch_v1.R.id.map;

//RoutingListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener

public class DriverMapActivity extends FragmentActivity implements OnMapReadyCallback,
        View.OnClickListener,DirectionCallback {

    private Button btnRequestDirection;
    private GoogleMap googleMap;
    private String serverKey = "AIzaSyA2DouhfVNrNWRBZrPDfnWr7qaqphpYsGw";
    private LatLng camera = new LatLng(35.1773909, 136.9471357);
    private LatLng origin = new LatLng(35.1766982, 136.9413508);
    private LatLng destination = new LatLng(35.1800441, 136.9532567);
    private String[] colors = {"#7fff7272", "#7f31c7c5", "#7fff8a00"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_map);

        btnRequestDirection = (Button) findViewById(R.id.BtnFindLocation);
        btnRequestDirection.setOnClickListener(this);

        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }



    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.BtnFindLocation) {
            requestDirection();

        }
    }
    public void requestDirection() {
        //Snackbar.make(btnRequestDirection, "Direction Requesting...", Snackbar.LENGTH_SHORT).show();
        GoogleDirection.withServerKey(serverKey)
                .from(origin)
                .to(destination)
                .transportMode(TransportMode.WALKING)
                .alternativeRoute(true)
                .execute(this);


    }


    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {


            googleMap.addMarker(new MarkerOptions().position(origin));
            googleMap.addMarker(new MarkerOptions().position(destination));


            Log.d("OK", "okay");
            for (int i = 0; i < direction.getRouteList().size(); i++) {
                com.akexorcist.googledirection.model.Route route = direction.getRouteList().get(i);
                String color = colors[i % colors.length];

                //ArrayList<LatLng> directionPositionList = route.getLegList().get(0).getDirectionPoint();
                //googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 5, Color.parseColor(color)));

                this.googleMap.addPolyline(DirectionConverter.createPolyline(this, route.getLegList().get(0).getDirectionPoint(), 5, Color.parseColor(color)));

                Toast.makeText(this, "onDirecitonSuccess", Toast.LENGTH_SHORT).show();

        }
//        ArrayList<LatLng> sectionPositionList = direction.getRouteList().get(0).getLegList().get(0).getSectionPoint();
//        for(LatLng position : sectionPositionList){
//            googleMap.addMarker(new MarkerOptions().position(position));
//        }
//
//        List<Step> stepList = direction.getRouteList().get(0).getLegList().get(0).getStepList();
//        ArrayList<PolylineOptions> polylineOptionList = DirectionConverter.createTransitPolyline(this,stepList,5,Color.RED,3,Color.BLUE);
//            for(PolylineOptions polylineOptions : polylineOptionList){
//                googleMap.addPolyline(polylineOptions);
//            }

        btnRequestDirection.setVisibility(View.GONE);
    }

    @Override
    public void onDirectionFailure(Throwable t) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(camera, 15));
    }





































//    private static LatLng start = new LatLng(18.015365, -77.499382);
//    private static LatLng end = new LatLng(18.012590, -77.500659);
//    private static LatLng waypoint = new LatLng(18.01455, -77.499333);
//
//    private static LatLng Magallanes = new LatLng(10.294445, 123.897424);
//    private static final LatLng usjrBasak = new LatLng(10.287374, 123.862595);
//
//    private List<Polyline>polylines;
//
//    private TextToSpeech tts;
//
//
//
//    private GoogleMap mMap;
//    Button BtnFindLocation;
//    EditText SearchLocation;
//
//    private static final int[] COLORS = new int[]{R.color.colorPrimaryDark,R.color.colorPrimary,R.color.colorPrimaryLight,R.color.accent,R.color.primary_dark_material_light};
//
//    MarkerOptions markerOptions;
//    LatLng latLng;
//
//    protected GoogleApiClient mGoogleApiClient;
//
//    final Context context = this;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_driver_map);
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(map);
//        mapFragment.getMapAsync(this);
//
//        BtnFindLocation = (Button) findViewById(R.id.BtnFindLocation);
//        SearchLocation = (EditText) findViewById(R.id.SearchLocation);
//
//        polylines = new ArrayList<>();
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//               .addApi(Places.GEO_DATA_API)
//                  .addConnectionCallbacks(this)
//                  .addOnConnectionFailedListener(this)
//                .build();
//        MapsInitializer.initialize(this);
//        mGoogleApiClient.connect();
//
//
//
//
//        BtnFindLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                String Location = SearchLocation.getText().toString();
//
//                if (Location != null && !Location.equals("")) {
//                    new GeocoderTask().execute(Location);
//                    mMap.addPolyline(new PolylineOptions().add(Magallanes,usjrBasak));
//                    //onRoutingStart();
//                }
//            }
//        });
//
//
//
//        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int status) {
//                if(status == TextToSpeech.SUCCESS){
//                    int result = tts.setLanguage(Locale.US);
//
//                    if(result == TextToSpeech.LANG_MISSING_DATA
//                            || result == TextToSpeech.LANG_NOT_SUPPORTED)
//                    {
//                        Log.e("TTS","This Language is not supported");
//                    }
//                    else {
//                        speakout();
//                    }
//                }
//                else{
//                    Log.e("TTS","Initialization Failed");
//                }
//            }
//        });
//
//
//    }
//
//    public void requestDirection(){
//
//
//    }
//
//
//
//    private void speakout(){
//
//        String text = "John Doe wants to HITCH, say HIITCH if you want to Accept his request and no if you want to decline";
//
//        tts.setSpeechRate(1);
//        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null);
//
//    }
//
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng usjr = new LatLng(10.287374, 123.862595);
//        mMap.addMarker(new MarkerOptions().position(usjr).title("Marker in University of San Jose - Recoletos"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(usjr));
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        mMap.setMyLocationEnabled(true);
//
//
//        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//
//
//                alertDialogBuilder.setMessage("John Doe wants to HITCH!");
//
//                alertDialogBuilder.setPositiveButton("HITCH", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                                            createNotification();
//                        speakout();
//                    }
//
//                });
//
//                alertDialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                        Toast.makeText(DriverMapActivity.this, "cancelled", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                    alertDialogBuilder.show();
//
//              //  speakout();
//                Toast.makeText(getBaseContext(),"i was clicked", Toast.LENGTH_LONG).show();
//         return true;
//
//            }
//        });
//
//
//
//        //the update starts here
//
//        mMap.addPolyline(new PolylineOptions().add(Magallanes,usjrBasak));
//
//        // Rectangle centered at Sydney.  This polyline will be mutable.
//        int radius = 5;
//        PolylineOptions options = new PolylineOptions()
//                .add(new LatLng(usjr.latitude + radius, usjr.longitude + radius));
//
//    }
//
//    public void createNotification(){
//        Intent intent = new Intent(this,SearchResult.class);
//
//        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(getApplication());
//        taskStackBuilder.addParentStack(SearchResult.class);
//        taskStackBuilder.addNextIntent(intent);
//
//        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(12, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this);
//        nBuilder.setContentText("John doe wants to Hitch");
//        nBuilder.setSmallIcon(R.mipmap.ic_launcher);
//        nBuilder.setSound(defaultSoundUri);
//        nBuilder.setContentIntent(pendingIntent);
//
//        Notification notification = nBuilder.build();
//        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//
//        nm.notify(1,notification);
//
//
//        Toast.makeText(DriverMapActivity.this, "Create Notification!!!", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onRoutingFailure(RouteException e) {
//
//    }
//
//    @Override
//    public void onRoutingStart() {
//
////        Routing routing = new Routing.Builder()
////                .travelMode(AbstractRouting.TravelMode.DRIVING)
////                .withListener(this)
////                .alternativeRoutes(true)
////                .waypoints(start,waypoint, end)
////                .build();
////        routing.execute();
////
////        Toast.makeText(this, "distance"+routing , Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onRoutingSuccess(ArrayList<Route> arrayList, int i) {
//
//        CameraUpdate center = CameraUpdateFactory.newLatLng(start);
//        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
//
//        mMap.moveCamera(center);
//
//        if(polylines.size() > 0){
//            for(Polyline poly : polylines){
//                poly.remove();
//            }
//        }
//
//        polylines = new ArrayList<>();
//
//        for (int j = 0; j < arrayList.size(); j++){
//
//            int colorIndex = i % COLORS.length;
//
//           PolylineOptions polylineOptions = new PolylineOptions();
//            polylineOptions.color(getResources().getColor(COLORS[colorIndex]));
//            polylineOptions.width(10 + j * 3);
//            polylineOptions.addAll(arrayList.get(j).getPoints());
//            Polyline polyline = mMap.addPolyline(polylineOptions);
//            polylines.add(polyline);
//
//            Toast.makeText(getApplicationContext(),"Route "+ (i+1) +": distance - "+ arrayList.get(i).getDistanceValue()+": duration - "+ arrayList.get(i).getDurationValue(),Toast.LENGTH_SHORT).show();
//        }
//
//        MarkerOptions options = new MarkerOptions();
//        options.position(start);
//        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker_1));
//        mMap.addMarker(options);
//
//    }
//
//    @Override
//    public void onRoutingCancelled() {
//
//    }
//
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//
//
//    private class GeocoderTask extends AsyncTask<String,Void,List<Address>>{
//
//        @Override
//        protected List<Address> doInBackground(String... locationName) {
//            Geocoder geocoder = new Geocoder(getBaseContext());
//
//            List <Address> addresses = null;
//
//            try{
//                addresses = geocoder.getFromLocationName(locationName[0],3);
//            }catch(IOException e){
//                e.printStackTrace();
//            }
//
//            return addresses;
//        }
//
//        @Override
//        protected void onPostExecute(List<Address>addresses){
//            if(addresses == null || addresses.size()==0){
//                Toast.makeText(getBaseContext(),"No Location Found", Toast.LENGTH_LONG).show();
//            }
//            mMap.clear();
//
//            for(int i=0; i<addresses.size();i++){
//
//                Address address = (Address) addresses.get(i);
//
//                latLng = new LatLng(address.getLatitude(),address.getLongitude());
//
//
//                String addressText = String.format("%s , %s, ",
//                        address.getMaxAddressLineIndex()>0? address.getAddressLine(0): "",
//                        address.getCountryName());
//
//                markerOptions = new MarkerOptions();
//                markerOptions.position(latLng);
//                markerOptions.title(addressText)
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car_marker_1));
//
//
//                mMap.addMarker(markerOptions);
//
//                if( i == 0)
//                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//
//            }
//        }
//    }

}