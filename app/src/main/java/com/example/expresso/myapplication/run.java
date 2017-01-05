package com.example.expresso.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.widget.Toast;


import static android.bluetooth.BluetoothAdapter.*;

/**
 * hergestellt von Björn on 15.12.16.
 */
public class run extends AppCompatActivity {
    Button r_up;
    Button r_down;
    Button l_up;
    Button l_down;
//Versuch Nr.1
/*
    protected void btcheck(Intent intent) {
        String action = intent.getAction();

        if(BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action))

        {
            Intent myIntent = new Intent(run.this, MainActivity.class);
            startActivity(myIntent);
        }
    }

*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//Hier beginnt versuch zwei nach:
        // http://de.androids.help/q24586
        IntentFilter filter1 = new IntentFilter(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        this.registerReceiver(mReceiver, filter1);
//Ende versuch zwei (Weiter geht es unten)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run)   ;

        r_up = (Button) findViewById(R.id.r_up);
        r_down = (Button) findViewById(R.id.r_down);
        l_up = (Button) findViewById(R.id.l_up);
        l_down = (Button) findViewById(R.id.l_down);

        r_up.setOnTouchListener(new View.OnTouchListener(){
            public  boolean onTouch(View v , MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    //button pressed
                    Global.motorController.drive(Motor_Controller.Motor_Side.right, Motor_Controller.Driving_Direction.forwards);
                    return true;
                }else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    // released
                    Global.motorController.drive(Motor_Controller.Motor_Side.right, Motor_Controller.Driving_Direction.none);
                    return true;
                }
                return false;
            }
        });
        r_down.setOnTouchListener(new View.OnTouchListener(){
            public  boolean onTouch(View v , MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    //button pressed
                    Global.motorController.drive(Motor_Controller.Motor_Side.right, Motor_Controller.Driving_Direction.backwards);
                    return true;
                }else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    // released
                    Global.motorController.drive(Motor_Controller.Motor_Side.right, Motor_Controller.Driving_Direction.none);
                    return true;
                }
                return false;
            }
        });
        l_up.setOnTouchListener(new View.OnTouchListener(){
            public  boolean onTouch(View v , MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    //button pressed
                    Global.motorController.drive(Motor_Controller.Motor_Side.left, Motor_Controller.Driving_Direction.forwards);
                    return true;
                }else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    // released
                    Global.motorController.drive(Motor_Controller.Motor_Side.left, Motor_Controller.Driving_Direction.none);
                    return true;
                }
                return false;
            }
        });
        l_down.setOnTouchListener(new View.OnTouchListener(){
            public  boolean onTouch(View v , MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    //button pressed
                    Global.motorController.drive(Motor_Controller.Motor_Side.left, Motor_Controller.Driving_Direction.backwards);
                    return true;
                }else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    // released
                    Global.motorController.drive(Motor_Controller.Motor_Side.left, Motor_Controller.Driving_Direction.none);
                    return true;
                }
                return false;
            }
        });
        }
    //Der Listener ob sich der State auf DIsconnected befindet.
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                 //Device found
            }
            else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                Intent myIntent = new Intent(run.this, MainActivity.class);
                startActivity(myIntent);
            }
        }
    };
    }


