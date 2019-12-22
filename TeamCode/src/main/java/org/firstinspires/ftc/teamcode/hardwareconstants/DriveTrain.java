package org.firstinspires.ftc.teamcode.hardwareconstants;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveTrain {
    //Wheel names representing location on robot
    public static DcMotor frontLeftWheel, frontRightWheel, backLeftWheel, backRightWheel;

    //Sets power to the wheels for a holonomic drive train
    public void holonomicDrive(double xAxis, double yAxis, double rotation) {

        if(Math.abs(xAxis) > 0.01 || Math.abs(yAxis) > 0.01 || Math.abs(rotation) > 0.01){
            frontLeftWheel.setPower(xAxis + yAxis - rotation);
            frontRightWheel.setPower(xAxis - yAxis - rotation);
            backLeftWheel.setPower(-xAxis + yAxis - rotation);
            backRightWheel.setPower(-xAxis - yAxis - rotation);
        } else{
            frontLeftWheel.setPower(0);
            frontRightWheel.setPower(0);
            backLeftWheel.setPower(0);
            backRightWheel.setPower(0);
        }
    }

    //Sets power to the wheels for a mecanum drive train
    public void mecanumDrive(double xAxis, double yAxis, double rotation, Telemetry telemetry) {
        if (Math.abs(xAxis) > 0.01 || Math.abs(yAxis) > 0.01 || Math.abs(rotation) > 0.01){
            frontLeftWheel.setPower(-xAxis + yAxis - rotation);
            frontRightWheel.setPower(-xAxis - yAxis - rotation);
            backLeftWheel.setPower(xAxis + yAxis - rotation);
            backRightWheel.setPower(xAxis - yAxis - rotation);

            telemetry.addData("left stick x", xAxis);
            telemetry.addData("left stick y", yAxis);
            telemetry.addData("right stick x", rotation);
            telemetry.update();

        } else{
            frontLeftWheel.setPower(0);
            frontRightWheel.setPower(0);
            backLeftWheel.setPower(0);
            backRightWheel.setPower(0);
        }
    }
}
