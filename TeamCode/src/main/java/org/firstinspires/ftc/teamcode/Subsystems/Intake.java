package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake {
    DcMotor intake;
    DcMotor transfer;

    public enum State{
        RESTING,
        INTAKING,
        EJECTING
    }
    State currentState = State.RESTING;

    public State getState(){
        return currentState;
    }
    public void setState(State newState){
        currentState = newState;
    }
    public void initiate(HardwareMap hardwareMap){
        intake = hardwareMap.dcMotor.get("intake");
        transfer = hardwareMap.dcMotor.get("transfer");
        transfer.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void update(){
        switch (currentState){
            case RESTING:
                intake.setPower(0);
                transfer.setPower(0);
                break;
            case INTAKING:
                intake.setPower(1);
                transfer.setPower(1);
                break;
            case EJECTING:
                intake.setPower(-1);
                transfer.setPower(-1);
                break;
        }
    }
}
