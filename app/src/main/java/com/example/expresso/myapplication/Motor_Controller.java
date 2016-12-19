package com.example.expresso.myapplication;

/**
 * Created by donborito on 15.12.16.
 */

public class Motor_Controller {
    public enum Motor_Side {left, right}

    ;

    // none = motor off
    public enum Driving_Direction {forwards, backwards, none}

    ;

    public void drive(Motor_Side motor_side, Driving_Direction direction) {

        byte[] command;
        // motor id
        byte motor = 0x00;
        byte motorSpeed = 0x00;
        byte motorRunState = 0x20;
        byte motorRegulationMode = 0x01;
        // select motor id
        switch (motor_side) {
            case left:
                motor = 0x02;
                break;
            case right:
                motor = 0x01;
                break;
        }
        // select driving direction (motorSpeed)
        switch (direction) {
            case forwards:
                motorSpeed = 0x32;
                break;
            case backwards:
                motorSpeed = -0x32;
                break;
            case none:
                motorRegulationMode = 0x00;
                motorSpeed = 0x00;
                motorRunState = 0x00;
                break;
            default:
                motorSpeed = 0x00;
                break;
        }
        // create command byte
        command = new byte[]{0x0C, 0x00, 0x00, 0x04, motor, motorSpeed, 0x05, motorRegulationMode, motorSpeed, motorRunState, 0x00, 0x00, 0x00, 0x00};
        // send command to robot
        Global.connector.send(command);

    }
}
