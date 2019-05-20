package com.example.edwin.turismo;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class Ubicacion extends Fragment implements OnMapReadyCallback {
GoogleMap mgoogleMap;

MapView mapView;
View view;
String Titulo;
    String ubicacion;


    @SuppressLint("ValidFragment")
    public Ubicacion(String titulo,String  ubi) {
        Titulo = titulo;
        ubicacion=ubi;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ubicacion, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView=(MapView)view.findViewById(R.id.mapView);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mgoogleMap = googleMap;
        String[] parts = ubicacion.split(",");
        String lat = parts[0]; // 123
        String log = parts[1]; // 654321
Double lo,lg;
lo=Double.parseDouble(lat);
lg=Double.parseDouble(log);
        MapsInitializer.initialize(getContext());
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lo,lg);
        mgoogleMap.addMarker(new MarkerOptions().position(sydney).title(Titulo));
        mgoogleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        CameraUpdate ZoomCam = CameraUpdateFactory.zoomTo(10);
        mgoogleMap.animateCamera(ZoomCam);
    }
}
