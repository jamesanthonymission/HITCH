package com.itproject.hitch_v1;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Step;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class Transit extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener,DirectionCallback{

    private Button btnRequestDirection;
    private GoogleMap googleMap;
    private String serverKey = "AIzaSyA2DouhfVNrNWRBZrPDfnWr7qaqphpYsGw";
    private LatLng camera = new LatLng(13.7457211, 100.5646619);
    private LatLng origin = new LatLng(13.7371063, 100.5642539);
    private LatLng destination = new LatLng(13.7604896, 100.5594266);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transit);

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
        GoogleDirection.withServerKey(serverKey)
                .from(origin)
                .to(destination)
                .transportMode(TransportMode.TRANSIT)
                .execute(this);
    }
    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {

        ArrayList<LatLng> sectionPositionList = direction.getRouteList().get(0).getLegList().get(0).getSectionPoint();
        for (LatLng position : sectionPositionList) {
            googleMap.addMarker(new MarkerOptions().position(position));
        }

        List<Step> stepList = direction.getRouteList().get(0).getLegList().get(0).getStepList();
        ArrayList<PolylineOptions> polylineOptionList = DirectionConverter.createTransitPolyline(this, stepList, 5, Color.RED, 3, Color.BLUE);
        for (PolylineOptions polylineOption : polylineOptionList) {
            googleMap.addPolyline(polylineOption);
        }
        Toast.makeText(this, "Transit", Toast.LENGTH_SHORT).show();
        btnRequestDirection.setVisibility(View.GONE);
    }


    @Override
    public void onDirectionFailure(Throwable t) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(camera, 14));
    }
}
