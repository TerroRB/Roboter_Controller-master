package com.example.expresso.myapplication;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends Activity {

    TextView textView1;
    ListView deviceLV;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Global.mainActivity = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView) findViewById(R.id.textView1);

        Global.connector = new Connector();
        Global.connector.checkBluetoothState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Global.mainActivity.getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    class myOnItemClickListener implements AdapterView.OnItemClickListener {
        //-----------------------------------------------------------

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
            Global.connector.setStatus("onItemClick:" + pos);

            Global.connector.BT_Device = (BluetoothDevice) Global.connector.BT_PairedDevices.toArray()[pos];

            try {
                Global.connector.BT_Socket = Global.connector.BT_Device.createRfcommSocketToServiceRecord(MY_UUID);
                Global.connector.BT_Socket.connect();

                Global.connector.BT_OutStream = Global.connector.BT_Socket.getOutputStream();
                Global.connector.BT_InStream = Global.connector.BT_Socket.getInputStream();

                Global.connector.setStatus("connected to " + Global.connector.BT_Device.getName() + " (" + Global.connector.BT_Device.getAddress() + " )");
                // run aufrufen

            } catch (IOException e) {
                Global.connector.setStatus("not connected");
            }

        }

    }

    public void addListView(ArrayList<String> list) {


        final ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                list);


        deviceLV = (ListView) findViewById(R.id.devices);
        deviceLV.setAdapter(adapter);
        deviceLV.setOnItemClickListener(new myOnItemClickListener());
    }

    public void onClickSend(View view) {
        // example for left motor forwards
        //Global.motorController.drive(Motor_Controller.Motor_Side.left, Motor_Controller.Driving_Direction.forwards);
        //Björn 17.12.2016
        //Umgecodet um über Send auf den Controller zu kommen.
        Intent myIntent = new Intent(MainActivity.this, run.class);
        startActivity(myIntent);

    }
}