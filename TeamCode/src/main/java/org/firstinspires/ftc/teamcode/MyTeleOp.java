package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Subsystems.Intake.State.RESTING;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

@TeleOp
public class MyTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {


        Drivetrain drivetrain = new Drivetrain();
        drivetrain.initiate(hardwareMap);
        Intake intake = new Intake();
        intake.initiate(hardwareMap);
        Flywheel flywheel = new Flywheel();
        flywheel.initiate(hardwareMap);
        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            if (gamepad1.circleWasPressed()){
                switch (intake.getState()){
                    case RESTING:
                        intake.setState(Intake.State.INTAKING);
                        break;
                    default:
                        intake.setState(RESTING);
                        break;
                }
            }

            if (gamepad1.crossWasPressed()){
                switch (intake.getState()){
                    case RESTING:
                        intake.setState(Intake.State.EJECTING);
                        break;
                    default:
                        intake.setState(RESTING);
                        break;
                }
            }

            if (gamepad1.triangleWasPressed()){
                switch (flywheel.getState()){
                    case RESTING:
                        flywheel.setState(Flywheel.State.SHOOTING);
                        break;
                    default:
                        flywheel.setState(Flywheel.State.RESTING);
                        break;
                }
            }

            drivetrain.run(x,y,rx);
            intake.update();
            flywheel.update();

            flywheel.status(telemetry);
            telemetry.update();
        }
    }
}