package org.techtown.hello2;

import android.util.Base64;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.techtown.hello2.NewMain1Activity;
import org.techtown.hello2.R;

import java.nio.charset.StandardCharsets;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;





public class NewMainActivity extends AppCompatActivity {


    private BluetoothSPP bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmain);
        bt = new BluetoothSPP(this);

        if (!bt.isBluetoothAvailable()) { //블루투스 사용 불가
          //  Toast.makeText(getApplicationContext()
            //        , "Bluetooth is not available"
              //      , Toast.LENGTH_SHORT).show();
            finish();

        }

        bt.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() { //데이터 수신
            public void onDataReceived(byte[] data, String message) {
             //   Toast.makeText(NewMainActivity.this, message, Toast.LENGTH_SHORT).show();
                if(message.equals("00")) {

                    Intent intent = new Intent(getApplicationContext(), NewMainActivity.class);
                    startActivity(intent);
                    finish();
                }
               else if(message.equals("01")) {

                    Intent intent = new Intent(getApplicationContext(), MainBus1.class);
                    startActivity(intent);
                    finish();


                }
                else if(message.equals("10")) {

                    Intent intent = new Intent(getApplicationContext(), MainBus02.class);
                    startActivity(intent);
                    finish();

                }
                else if(message.equals("11")) {
                    Intent intent = new Intent(getApplicationContext(), MainBus2.class);
                    startActivity(intent);
                    finish();

                }
            }
        });



        bt.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() { //연결됐을 때
            public void onDeviceConnected(String name, String address) {
               // Toast.makeText(getApplicationContext()
                 //       , "Connected to " + name + "\n" + address
                   //     , Toast.LENGTH_SHORT).show();
            }

            public void onDeviceDisconnected() { //연결해제
               // Toast.makeText(getApplicationContext()
                      //  , "Connection lost", Toast.LENGTH_SHORT).show();
            }

            public void onDeviceConnectionFailed() { //연결실패
                //Toast.makeText(getApplicationContext()
                  //      , "Unable to connect", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton btnConnect = findViewById(R.id.btnConnect); //연결시도
        btnConnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (bt.getServiceState() == BluetoothState.STATE_CONNECTED) {
                    bt.disconnect();
                } else {
                    Intent intent = new Intent(getApplicationContext(), DeviceList.class);
                    startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);
                }
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        bt.stopService(); //블루투스 중지
    }

    public void onStart() {
        super.onStart();
        if (!bt.isBluetoothEnabled()) { //
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, BluetoothState.REQUEST_ENABLE_BT);
        } else {
            if (!bt.isServiceAvailable()) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER); //DEVICE_ANDROID는 안드로이드 기기 끼리

            }
        }
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK)
                bt.connect(data);
        } else if (requestCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                bt.setupService();
                bt.startService(BluetoothState.DEVICE_OTHER);

            } else {
                //Toast.makeText(getApplicationContext()
                  //      , "Bluetooth was not enabled."
                    //    , Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    /*    public void onButton1Clicked(View v){
            Toast.makeText(this,"실시간자리를 표시합니다.",Toast.LENGTH_LONG).show();
        }*/
    public void onButton1Clicked(View v){
        Toast.makeText(this,"기사님께 전화가 연결됩니다..",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-4711-1464"));
        startActivity(intent);
    }
    public void onButton2Clicked(View v){

        Toast.makeText(this,"운행정보 업로딩중..",Toast.LENGTH_LONG).show();
        Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://blog.naver.com/miwook3025/221349483584"));
        startActivity(intent);

    }

    public void onButton3Clicked(View v){
        Toast.makeText(this,"우리아이위치를 표시합니다..",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), NewMain1Activity.class);
        startActivity(intent);
    }

}
