package com.como.comolake.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.graphics.Rect;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.como.comolake.R;
import com.como.comolake.util.Constants;
import com.como.comolake.util.Descriptions;
import com.como.comolake.util.Variables;
import com.como.comolake.view.MyMapView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.util.GeoPoint;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

public class MapActivity extends BaseFragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener{

    ArrayList<ArrayList<Marker>> markers = new ArrayList<ArrayList<Marker>>();
    ArrayList<ArrayList<ItemizedIconOverlay<OverlayItem>>> overlays = new ArrayList<ArrayList<ItemizedIconOverlay<OverlayItem>>>();

    GoogleMap mMap;

    MyMapView mOfflineMap;

    int nPopupWidth = 0;
    int nPopupHeight = 0;

    int nTitlebarHeight;
    int nDescriptionHeight = 0;
    int nDescriptionWidth = 0;

    int nCategoryIndex;
    int nVideoIndex;

    double fLatitude;
    double fLongitude;

    String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    LocationManager locationManager;
    LocationListener locationListener;

    boolean bIsOnline = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            bIsOnline = true;
            findViewById(R.id.map).setVisibility(View.VISIBLE);
            findViewById(R.id.offline_map).setVisibility(View.INVISIBLE);
            findViewById(R.id.helperView).setVisibility(View.VISIBLE);
        }
        else {
            bIsOnline = false;
            findViewById(R.id.map).setVisibility(View.INVISIBLE);
            findViewById(R.id.offline_map).setVisibility(View.VISIBLE);
            findViewById(R.id.helperView).setVisibility(View.INVISIBLE);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        findViewById(R.id.layout_popup).addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (nPopupWidth == 0) {
                    nPopupWidth = right - left;
                }
                if (nPopupHeight == 0) {
                    nPopupHeight = bottom - top;
                }
            }
        });

        findViewById(R.id.title_bar).addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                nTitlebarHeight = bottom - top;
            }
        });

        findViewById(R.id.txt_description).addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (nDescriptionHeight == 0) {
                    nDescriptionHeight = bottom - top;
                }
                if (nDescriptionWidth == 0) {
                    nDescriptionWidth = right - left;
                }
            }
        });

        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.img_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.layout_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Variables.nCategoryIndex = nCategoryIndex;
                Variables.nVideoIndex = nVideoIndex;

                Variables.nFragmentIndex = Constants.VIDEO_LIST;
                Intent intent = new Intent(MapActivity.this, PlayerActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.layout_navigate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Variables.nCategoryIndex = nCategoryIndex;
                Variables.nVideoIndex = nVideoIndex;
                Variables.myPosition = new LatLng(fLatitude, fLongitude);

                Intent intent = new Intent(MapActivity.this, NavigationActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.img_zoomin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bIsOnline == true)
                    mMap.animateCamera(CameraUpdateFactory.zoomIn());
                else
                    mOfflineMap.getController().zoomIn();
            }
        });

        findViewById(R.id.img_zoomout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bIsOnline == true)
                    mMap.animateCamera(CameraUpdateFactory.zoomOut());
                else
                    mOfflineMap.getController().zoomOut();
            }
        });

        findViewById(R.id.img_geolocalize).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bIsOnline == true) {
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(new LatLng(fLatitude, fLongitude));
                    mMap.addMarker(markerOptions);
                } else {
                    ArrayList<OverlayItem> overlayItems = new ArrayList<OverlayItem>();
                    overlayItems.add(new OverlayItem("", "", new GeoPoint(fLatitude, fLongitude)));

                    final ItemizedIconOverlay<OverlayItem> locationOverlay = new ItemizedIconOverlay<OverlayItem>(overlayItems, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                        @Override
                        public boolean onItemSingleTapUp(int index, OverlayItem item) {
                            return false;
                        }

                        @Override
                        public boolean onItemLongPress(int index, OverlayItem item) {
                            return false;
                        }
                    }, new DefaultResourceProxyImpl(getApplicationContext()));

                    mOfflineMap.getOverlays().add(locationOverlay);
                    mOfflineMap.invalidate();
                }
            }
        });

        findViewById(R.id.img_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.layout_popup).setVisibility(View.GONE);
            }
        });

        findViewById(R.id.layout_particular).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkers(0);
            }
        });

        findViewById(R.id.layout_comocity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkers(1);
            }
        });

        findViewById(R.id.layout_town).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkers(2);
            }
        });

        findViewById(R.id.layout_villa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMarkers(3);
            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                fLongitude = location.getLongitude();
                fLatitude = location.getLatitude();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (Build.VERSION.SDK_INT >= 23) {
            checkPermissions();
        }
        else {
            getMyLocation();
        }

        ((TextView) findViewById(R.id.txt_video)).setText(Constants.MAP_VIDEO[Variables.nLanguageIndex]);
        ((TextView) findViewById(R.id.txt_navigate)).setText(Constants.MAP_NAVIGATION[Variables.nLanguageIndex]);

        findViewById(R.id.layout_popup).setVisibility(View.INVISIBLE);

        ((TextView) findViewById(R.id.txt_particularity)).setText(Constants.MAIN_CATEGORIES[0][Variables.nLanguageIndex]);
        ((TextView) findViewById(R.id.txt_comocity)).setText(Constants.MAIN_CATEGORIES[1][Variables.nLanguageIndex]);
        ((TextView) findViewById(R.id.txt_town)).setText(Constants.MAIN_CATEGORIES[2][Variables.nLanguageIndex]);
        ((TextView) findViewById(R.id.txt_villa)).setText(Constants.MAIN_CATEGORIES[3][Variables.nLanguageIndex]);

        if (bIsOnline == false) {
            mOfflineMap = (MyMapView) findViewById(R.id.offline_map);
            mOfflineMap.setTileSource(TileSourceFactory.MAPQUESTOSM);
            mOfflineMap.setClickable(true);
            mOfflineMap.setUseDataConnection(false);

            mOfflineMap.getController().setZoom(13);
            mOfflineMap.getController().setCenter(new GeoPoint(45.7993, 9.0927));
            mOfflineMap.setMaxZoomLevel(16);
            mOfflineMap.setMinZoomLevel(10);

            View mHelperView = findViewById(R.id.helperView);
            mHelperView.setOnTouchListener(new View.OnTouchListener() {

                private float scaleFactor = 1f;

                private ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(
                        MapActivity.this, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
                    @Override
                    public boolean onScale(ScaleGestureDetector detector) {
                        scaleFactor = detector.getScaleFactor();
                        return true;
                    }
                });

                private GestureDetector simpleGestureDetector = new GestureDetector(
                        MapActivity.this, new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onDoubleTap(MotionEvent e) {
                        return true;
                    }
                });

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (simpleGestureDetector.onTouchEvent(event)) {
                        mMap.animateCamera(CameraUpdateFactory.zoomIn());
                    }

                    if (scaleGestureDetector.onTouchEvent(event)) {
                        mMap.moveCamera(CameraUpdateFactory.zoomBy((mMap.getCameraPosition().zoom * scaleFactor - mMap.getCameraPosition().zoom) / 5));
                    }

                    if (event.getPointerCount() == 1) {
                        getSupportFragmentManager().findFragmentById(R.id.map).getView().dispatchTouchEvent(event);
                    }
                    return true;
                }
            });
        }
    }

    public void checkPermissions() {
        List<String> permissions = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!permissions.isEmpty()) {
            String[] params = permissions.toArray(new String[permissions.size()]);
            requestPermissions(params, 100);
        }
        else {
            getMyLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 100:
                getMyLocation();
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(45.7993, 9.0927)).zoom(13).build();

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        for (int i = 0; i < Constants.MAP_POSITIONS.length; i++) {

            ArrayList<Marker> markers = new ArrayList<>();
            ArrayList<OverlayItem> overlayItems = new ArrayList<>();
            ArrayList<ItemizedIconOverlay<OverlayItem>> overlays = new ArrayList<>();

            for (int j = 0; j < Constants.MAP_POSITIONS[i].length; j++) {
                MarkerOptions markerOptions = new MarkerOptions();

                markerOptions.position(Constants.MAP_POSITIONS[i][j]);
                markerOptions.title(Constants.CAT_VIDEO_TITLES[i][j][Variables.nLanguageIndex]);
                markerOptions.icon(BitmapDescriptorFactory.fromResource(Constants.MAP_MARKERS[i]));

                Marker marker = mMap.addMarker(markerOptions);
                markers.add(marker);


                OverlayItem overlayItem = new OverlayItem("", "", new GeoPoint(Constants.MAP_POSITIONS[i][j].latitude, Constants.MAP_POSITIONS[i][j].longitude));
                overlayItem.setMarker(getResources().getDrawable(Constants.MAP_MARKERS[i]));
                overlayItems.add(overlayItem);

                final int nMajorIndex = i;
                final ItemizedIconOverlay<OverlayItem> locationOverlay = new ItemizedIconOverlay<OverlayItem>(overlayItems, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(int index, OverlayItem item) {
                        showPanel(nMajorIndex, index, null, true, mOfflineMap.fXPos, mOfflineMap.fYPos);
                        return false;
                    }

                    @Override
                    public boolean onItemLongPress(int index, OverlayItem item) {
                        return false;
                    }
                }, new DefaultResourceProxyImpl(getApplicationContext()));

                mOfflineMap.getOverlays().add(locationOverlay);
                overlays.add(locationOverlay);
            }

            this.markers.add(markers);
            this.overlays.add(overlays);
        }


        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapClickListener(this);
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        for (int i = 0; i < markers.size(); i++) {

            ArrayList<Marker> markers = this.markers.get(i);

            for (int j = 0; j < markers.size(); j++) {
                if (marker.equals(markers.get(j))) {
                    showPanel(i, j, marker, false, 0, 0);
                }
            }
        }
        return true;
    }

    public void showPanel(int i, int j, Marker marker, boolean bOffline, float x, float y) {

        TextView txtTitle = (TextView) findViewById(R.id.txt_title);
        TextView txtDescription = (TextView) findViewById(R.id.txt_description);

        findViewById(R.id.layout_popup).setVisibility(View.VISIBLE);

        Point screenPosition;
        if (bOffline == false) {
            Projection projection = mMap.getProjection();
            LatLng markerLocation = marker.getPosition();
            screenPosition = projection.toScreenLocation(markerLocation);
        }
        else {
            screenPosition = new Point((int) x, (int) y);
        }

        String strTitle = Constants.CAT_VIDEO_TITLES[i][j][Variables.nLanguageIndex];
        String strDescription = Descriptions.descriptions[i][j][Variables.nLanguageIndex];

        String subString = new String();
        Rect bounds = new Rect();
        txtDescription.getPaint().getTextBounds(strDescription, 0, strDescription.length(), bounds);

        for (int k = 0; k < strDescription.length(); k++) {
            if (strDescription.charAt(k) == ' ') {
                txtDescription.setText(strDescription.substring(0, k));
                if (txtDescription.getLineCount() > 5) {
                    break;
                }
                subString = strDescription.substring(0, k);
            }
        }
        txtDescription.setText(subString.substring(0, subString.length() - 3) + "...");


        txtTitle.getPaint().getTextBounds(strTitle, 0, strTitle.length(), bounds);
        int k = 0;
        for (k = 0; k < strTitle.length(); k++) {
            txtTitle.getPaint().getTextBounds(strTitle.substring(0, k) + "...", 0, new String(strTitle.substring(0, k) + "...").length(), bounds);
            if (bounds.width() > findViewById(R.id.layout_titlebar).getMeasuredWidth() - findViewById(R.id.layout_titlebar).getMeasuredHeight()) {
                break;
            }
            subString = strTitle.substring(0, k) + "...";
        }

        if (k == strTitle.length()) {
            txtTitle.setText(strTitle);
        } else {
            txtTitle.setText(subString);
        }

        ((ImageView) findViewById(R.id.img_thumbnail)).setImageResource(Constants.MAP_THUMBNAIL_IMAGES[i][j]);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(nPopupWidth, nPopupHeight);

        int nMarginLeft = 0, nMarginRight = 0, nMarginTop = 0, nMarginBottom = 0;
        if (screenPosition.x > findViewById(R.id.map).getMeasuredWidth() - nPopupWidth - 10) {
            nMarginLeft = screenPosition.x - 10 - nPopupWidth;
        }
        else {
            nMarginLeft = screenPosition.x + 10;
        }

        if (screenPosition.y < nPopupHeight / 2) {
            nMarginTop = nTitlebarHeight;
        }
        else if (screenPosition.y > findViewById(R.id.map).getMeasuredHeight() - nPopupHeight / 2) {
            nMarginTop = nTitlebarHeight + findViewById(R.id.map).getMeasuredHeight() - nPopupHeight;
        }
        else {
            nMarginTop = nTitlebarHeight + screenPosition.y - nPopupHeight / 2 - 7;
        }
        layoutParams.setMargins(nMarginLeft, nMarginTop, 0, 0);
        findViewById(R.id.layout_popup).setLayoutParams(layoutParams);

        findViewById(R.id.layout_titlebar).setBackgroundColor(getResources().getColor(Constants.MAP_CATEGORY_RGBCOLOR[i]));
        findViewById(R.id.layout_video).setBackgroundColor(getResources().getColor(Constants.MAP_CATEGORY_RGBCOLOR[i]));

        nCategoryIndex = i;
        nVideoIndex = j;
    }

    @Override
    public void onMapClick(LatLng position) {
        findViewById(R.id.layout_popup).setVisibility(View.GONE);
    }

    public void showMarkers(int nCategoryIndex) {
        for (int i = 0; i < markers.size(); i++) {
            for (int j = 0; j < markers.get(i).size(); j++) {
                if (i == nCategoryIndex) {
                    markers.get(i).get(j).setVisible(true);
                }
                else {
                    markers.get(i).get(j).setVisible(false);
                }
            }
        }

        for (int i = 0; i < overlays.size(); i++) {
            for (int j = 0; j < overlays.get(i).size(); j++) {
                if (i == nCategoryIndex) {
                    overlays.get(i).get(j).setEnabled(true);
                }
                else {
                    overlays.get(i).get(j).setEnabled(false);
                }
            }
        }

        mOfflineMap.invalidate();

        for (int i = 0; i < 4; i++) {
            if (i == nCategoryIndex) {
                ((ImageView) findViewById(Constants.MAP_CATEGORY_IMAGES[i])).setImageResource(Constants.MAP_CATEGORY_COLORIMAGE[i]);
                ((RelativeLayout) findViewById(Constants.MAP_CATEGORY_LAYOUTS[i])).setBackgroundColor(getResources().getColor(Constants.MAP_CATEGORY_RGBCOLOR[i]));
            }
            else {
                ((ImageView) findViewById(Constants.MAP_CATEGORY_IMAGES[i])).setImageResource(Constants.MAP_CATEGORY_GRAYSCALEIMAGE[i]);
                ((RelativeLayout) findViewById(Constants.MAP_CATEGORY_LAYOUTS[i])).setBackgroundColor(getResources().getColor(Constants.MAP_CATEGORY_GRAYSCALECOLOR[i]));
            }
        }
    }

    public void getMyLocation() {
        ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION);

        List<String> providers = locationManager.getAllProviders();
        Location location = null;

        if (providers.contains("gps")) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        else if (location == null && providers.contains("network")) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        else if (location == null && providers.contains("passive")) {
            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, locationListener);
            location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }

        if (location != null) {
            fLongitude = location.getLongitude();
            fLatitude = location.getLatitude();
        }
    }
}
