package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Flywheel {
    DcMotorEx rightMotor;
    DcMotorEx leftMotor;

    public void initiate (HardwareMap hardwareMap){
        rightMotor = hardwareMap.get(DcMotorEx.class, "LeftFlywheelMotor");
        leftMotor = hardwareMap.get(DcMotorEx.class, "RightFlywheelMotor");
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public enum State{
        RESTING,
        SHOOTING,
    }
    State currentState = State.RESTING;

    double kF = .00035;
    double targetTPM = 1250;
    public State getState(){
        return currentState;
    }
    public void setState(State newState){
        currentState = newState;
    }
    public void update(){
        double targetPower = targetTPM * kF;
        switch (currentState){
            case RESTING:
                rightMotor.setPower(0);
                leftMotor.setPower(0);
                break;
            case SHOOTING:
                rightMotor.setPower(targetPower);
                leftMotor.setPower(targetPower);
                break;
        }
    }
    public void status(Telemetry telemetry){
        telemetry.addData("POWER", rightMotor.getPower());
        telemetry.addData("VELOCITY", rightMotor.getVelocity());
    }
}
