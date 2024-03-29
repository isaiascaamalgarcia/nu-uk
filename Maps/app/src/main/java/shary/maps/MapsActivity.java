package shary.maps;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

import static android.app.PendingIntent.getActivity;

public class MapsActivity extends FragmentActivity {
    private CameraUpdate mcamera;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    Location location; // location
    double latitude=0; // latitude
    double longitude=0; // longitude
    LocationManager locationManager;
    private Activity context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        addOnMapClickListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                try {
                    locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    longitude = location.getLongitude();
                    latitude = location.getLatitude();
                    LatLng latLng=new LatLng(latitude,longitude);

                    List<Address> addresses;
                    Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
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

    public void addOnMapClickListener()
    {
        SupportMapFragment fm = (SupportMapFragment) this.getSupportFragmentManager().findFragmentById(R.id.map);
        mMap = fm.getMap();
        mMap.setOnMapClickListener(new OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                try {
                    List<Address> addresses;
                    Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
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
    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    private void setMarker(LatLng position, String titulo, String info) {
        // Agregamos marcadores para indicar sitios de interéses.
        mMap.addMarker(new MarkerOptions()
                .position(position)
                .title(titulo)  //Agrega un titulo al marcador
                .snippet(info)   //Agrega información detalle relacionada con el marcador
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))); //Color del marcador
                mcamera= CameraUpdateFactory.newLatLngZoom((position), 14);
                mMap.animateCamera(mcamera);
    }




}
