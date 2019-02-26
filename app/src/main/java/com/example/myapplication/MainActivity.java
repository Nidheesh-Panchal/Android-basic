package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.Random;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_CODE=123;
    final String LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;
    final long MIN_TIME = 5000;//5000 milliseconds that is 5 seconds.
    final float MIN_DISTANCE = 1000; //1000m to location update. or 1 km

    final String WEATHER_SITE="http://api.openweathermap.org/data/2.5/weather";
    final String APP_ID="41135d7151f20a3b40619c398395330f";


    String longitude;
    String latitude;

    String locationmanager = LocationManager.GPS_PROVIDER; //gps

    LocationManager mLocationManager;//start or stop requesting location updates.
    LocationListener mLocationListener;//listen for any changes in location

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("weather", "" + locationmanager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("weather", "onResume() called");
        Log.d("weather", "Getting weather for current location");
        weatherlocation();
    }

    private void weatherlocation() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("weather","Location changed. listener active");
                longitude=String.valueOf(location.getLongitude());
                latitude=String.valueOf(location.getLatitude());
                Log.d("weather","Longitude : "+longitude);
                Log.d("weather","Latitude : "+latitude);

                RequestParams params=new RequestParams();
                params.put("lat",latitude);
                params.put("lon",longitude);
                params.put("appid",APP_ID);
                networking(params);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {
                Log.d("weather", "Location service is on");
            }

            @Override
            public void onProviderDisabled(String s) {
                Log.d("weather", "Location services turned off");
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        mLocationManager.requestLocationUpdates(LOCATION_PROVIDER, MIN_TIME, MIN_DISTANCE, mLocationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //to check if the callback is to our request of location only.
        if(requestCode==REQUEST_CODE)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                Log.d("weather","Location permission granted");
                weatherlocation();
            }
            else
            {
                Log.d("weather","Location permission denied");
            }
        }
        else
        {
            Log.d("weather","Location permission denied");
        }
    }

    private void networking(RequestParams params)
    {
        //using Async for asynchronization of networking because we do not want our app to freeze
        //until request is fulfilled.
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(WEATHER_SITE,params,new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                //use json mate to get the proper keys and corresponding values of the response
                Log.d("weather","Response : "+response.toString());
                weatherdata weather=weatherdata.fromJSON(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.e("weather","Error : "+throwable.toString());
                Log.d("weather","Status Code : "+statusCode);
                Toast.makeText(MainActivity.this, "Network Request Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

