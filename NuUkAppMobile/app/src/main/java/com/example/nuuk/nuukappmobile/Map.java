package com.example.nuuk.nuukappmobile;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;


public class Map extends Fragment {
    private GoogleMap googleMap;
    private CameraUpdate mcamera;
    private Location location;
    private double latitude=0;
    private double longitude=0;
    private LocationManager locationManager;
    private Activity context;
    private View rootView;
    public Map(){

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_map, container, false);
        googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                R.id.map)).getMap();
        createMapView();
        addOnMapClickListener();
        setOnMarkerDragListener();
        return rootView;
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createMapView() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
            if (googleMap != null) {
                googleMap.setMyLocationEnabled(true);
                try {
                    locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    longitude = location.getLongitude();
                    latitude = location.getLatitude();
                    LatLng latLng=new LatLng(latitude,longitude);

                    List<Address> addresses;
                    Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                    addresses = geocoder.getFromLocation(latLng.latitude,latLng.longitude, 1);
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String region = addresses.get(0).getSubLocality();
                    String street = addresses.get(0).getThoroughfare();
                    String country = addresses.get(0).getCountryName();
                    setMarker(latLng,city+" "+" "+state+" "+region+" "+street+" "+country, "Latitud: " + latLng.latitude+ " Longitud: " + latLng.longitude);
                }
                catch (Exception e)
                {
                }
            }
        }
    }

    private void setMarker(LatLng position, String titulo, String info) {
        // Agregamos marcadores para indicar sitios de interéses.
        googleMap.addMarker(new MarkerOptions()
                .position(position)
                .title(titulo)  //Agrega un titulo al marcador
                .snippet(info)   //Agrega información detalle relacionada con el marcador
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))//Color del marcador
                .draggable(true));

        // mMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude)).title("Yo aqui").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)).snippet("App Nu'uk"));
        mcamera= CameraUpdateFactory.newLatLngZoom((position), 14);
        googleMap.animateCamera(mcamera);


    }



    public void setOnMarkerDragListener()
    {
        googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                marker.getId();
            }

            @Override
            public void onMarkerDrag(Marker marker) {
                marker.getId();
                marker.getPosition();
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                marker.getId();
            }
        });

    }

    public void addOnMapClickListener()
    {
        googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                R.id.map)).getMap();
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                try {
                    List<Address> addresses;
                    Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                    addresses = geocoder.getFromLocation(latLng.latitude,latLng.longitude, 1);
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String region = addresses.get(0).getSubLocality();
                    String street = addresses.get(0).getThoroughfare();
                    String country = addresses.get(0).getCountryName();
                    setMarker(latLng,city+" "+" "+state+" "+region+" "+street+" "+country, "Latitud: " + latLng.latitude+ " Longitud: " + latLng.longitude);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
    }
}
