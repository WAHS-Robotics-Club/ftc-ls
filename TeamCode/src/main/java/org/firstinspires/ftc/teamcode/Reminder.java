package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.hardwareconstants.DriveTrain;
import org.firstinspires.ftc.teamcode.hardwareconstants.HardwareMapConstants;

class Reminder extends OpMode {

    DriveTrain driveTrain = new DriveTrain();

    DcMotor frontLeft, frontRight, backLeft, backRight;

    double leftX, leftY, rightX;

    @Override
    public void init() {
        
        //Maps the hardware to the phone. See @HardwareMapConstants

        //Puts the controller stick values into a variable
        leftX = gamepad1.left_stick_x;
        leftY = gamepad1.left_stick_y;
        rightX = gamepad1.right_stick_x;

        //
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        //Mecanum drive train controls for moving the robot
        if (Math.abs(gamepad1.left_stick_x) > .01 || Math.abs(gamepad1.left_stick_y) > .01 || Math.abs(gamepad1.right_stick_x) > .01){
            frontLeft.setPower(leftX + leftY - rightX);
            frontRight.setPower(leftX - leftY - rightX);
            backLeft.setPower(-leftX + leftY - rightX);
            backRight.setPower(-leftX - leftY - rightX);
        }else{
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
        }

    }
}
