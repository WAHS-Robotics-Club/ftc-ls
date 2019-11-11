package org.firstinspires.ftc.teamcode.hardwareconstants;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain {
    //Wheel names representing location on robot
    static DcMotor frontLeft, frontRight, backLeft, backRight;
    //Identifying the which game controller is being used, similarly to @OpMode
    static Gamepad gamepad1 = null, gamepad2 = null;
    //Axis the robot moves along
    static float xAxis = gamepad1.left_stick_x, yAxis = -gamepad1.left_stick_y, rotation = gamepad1.right_stick_x;

    //Sets power to the wheels for a holonomic drive train
    public static void holonomicDrive() {
        if(Math.abs(xAxis) > 0.01 || Math.abs(yAxis) > 0.01 || Math.abs(rotation) > 0.01){
            frontLeft.setPower(xAxis + yAxis - rotation);
            frontRight.setPower(xAxis - yAxis - rotation);
            backLeft.setPower(-xAxis + yAxis - rotation);
            backRight.setPower(-xAxis - yAxis - rotation);
        } else{
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
        }
    }

    //Sets power to the wheels for a mecanum drive train
    public static void mecanumDrive() {
        if (Math.abs(xAxis) > 0.01 || Math.abs(yAxis) > 0.01 || Math.abs(rotation) > 0.01){
            frontLeft.setPower(xAxis + yAxis - rotation);
            frontRight.setPower(xAxis - yAxis - rotation);
            backLeft.setPower(xAxis + yAxis - rotation);
            backRight.setPower(xAxis - yAxis - rotation);
        } else{
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
        }
    }
}
