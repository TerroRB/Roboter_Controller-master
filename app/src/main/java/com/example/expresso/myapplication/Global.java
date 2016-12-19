package com.example.expresso.myapplication;

import android.widget.Button;

/**
 * Created by lucasgobel on 15.12.16.
 */

public class Global {
    // main gui
    public static MainActivity mainActivity;
    // connects android with a device
    public static Connector connector;
    public static final int REQUEST_ENABLE_BT = 1;
    // provides roboter controlling
    public static Motor_Controller motorController = new Motor_Controller();

    // Controller gui buttons
    public static Button r_up;


}
