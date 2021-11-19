package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Objects.DriveTrain;
import org.firstinspires.ftc.teamcode.Objects.Grabber;
import org.firstinspires.ftc.teamcode.Objects.Misc;


@TeleOp(name ="Test TeleOp - LS", group = "TeleOp")
public class TestTeleOp extends OpMode {
    //Initializing the servo objects:

    DriveTrain driveTrain;

    //Hello there
    @Override
    public void init(){
        //Hardware mapping the servos:
        driveTrain = DriveTrain.initDriveTrain(hardwareMap);

        driveTrain.resetEncoders();
        driveTrain.setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /*
    leftFront = 0
    leftBack = 3
    rightFront = 1
    rightBack = 2
     */

    @Override public void loop() {
        //Drive Train manual control system
        driveTrain.manualDrive(gamepad1);
        driveTrain.checkToggleSpeed(gamepad1);
        DriveTrain.logTelemetry(telemetry, driveTrain);

    }

}