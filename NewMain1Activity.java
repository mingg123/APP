package org.techtown.hello2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.techtown.hello2.R;

public class NewMain1Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmain1);
    }
    public void onButton1Clicked(View v){
        Toast.makeText(this,"Search... ",Toast.LENGTH_LONG).show();
        Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.kr/maps/place/인하대학교+학생식당(학생회관)/@37.4496396,126.6543569,17z/data=!3m1!4b1!4m5!3m4!1s0x357b79aa48142309:0x1c8aa868cf43ab98!8m2!3d37.4496354!4d126.6565509"));
        startActivity(intent);
    }
}








