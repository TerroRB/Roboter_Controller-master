package com.example.expresso.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by lucasgobel on 15.12.16.
 */

public class Connector {


    private static final int REQUEST_ENABLE_BT = 1;
    public Set<BluetoothDevice> BT_PairedDevices;
    public BluetoothDevice BT_Device;
    public BluetoothSocket BT_Socket;
    public OutputStream BT_OutStream;
    public InputStream BT_InStream;
    BluetoothAdapter BT_Adapter = BluetoothAdapter.getDefaultAdapter();


    public void checkBluetoothState() {
        if (!BT_Adapter.isEnabled()) {
            Global.mainActivity.textView1.append("Kein Gerät per Bluetooth verbunden");
            enableBluetooth();
            return;
        } else {
            if (BT_Adapter.isEnabled()) {
                Global.mainActivity.textView1.append("Bluetooth ist aktiviert");
                createDeviceList();
                return;
            }
        }
    }


    public void enableBluetooth() {
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        Global.mainActivity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        //checkBluetoothState();
    }


    public void setStatus(String string) {
        ((TextView) Global.mainActivity.findViewById(R.id.status)).setText(string);
    }

    //---------------------------------------------------------------


    //---------------------------------------------------------------
    public void send(byte[] buffer) {
        setStatus("send to " + BT_Device.getName());

        // for example {0x06, 0x00 ,0x00 ,0x03 ,0x0B, 0x02 ,0x05, 0x01}
        //byte[] buffer = new byte[]{0x0C,0x00,0x00,0x04,0x00,0x32,0x05,0x01,0x32,0x20,0x00,0x00,0x00,0x00};

        try {
            BT_OutStream.write(buffer);
        } catch (IOException e) {
            setStatus("Error ");
        }
    }

    //---------------------------------------------------------------


    //---------------------------------------------------------------
    public void createDeviceList() {
        BT_PairedDevices = BT_Adapter.getBondedDevices();

        ArrayList<String> list = new ArrayList<String>();

        for (BluetoothDevice BT_Device : BT_PairedDevices) {
            list.add(new String(BT_Device.getName()));
        }
        // add device list to the main activity
        Global.mainActivity.addListView(list);
    }

}
