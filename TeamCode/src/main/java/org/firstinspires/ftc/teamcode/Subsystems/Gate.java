package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Gate {
    public enum State{
        OPEN,
        CLOSE
    }
    State currentState = State.CLOSE;
    Servo gate;
    public static double openPos = 0.24;
    public static double closePos = 0.42;
    public static long openTime = 3000;
    long startTime = System.currentTimeMillis();
    public void shoot(){
        currentState = State.OPEN;
        startTime = System.currentTimeMillis();
    }

    public void initiate(HardwareMap hardwareMap){
        gate = hardwareMap.servo.get("gate");
    }
    public void update(){
        switch (currentState){
            case CLOSE:
                gate.setPosition(closePos);
                break;
            case OPEN:
                if (System.currentTimeMillis() - startTime > openTime){
                    currentState = State.CLOSE;
                }
                gate.setPosition(openPos);
                break;
        }

    }
}
