package com.example.expresso.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * hergestellt von Björn on 15.12.16.
 */
public class run extends AppCompatActivity {

    Button r_up;
    Button r_down;
    Button l_up;
    Button l_down;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
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
}
