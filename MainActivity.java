package org.techtown.hello2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButton1Clicked(View v) {
        Toast.makeText(this, "실시간자리를 표시합니다..", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), NewMainActivity.class);
        startActivity(intent);
    }
}

