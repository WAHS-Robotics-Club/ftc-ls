package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.hardwareconstants.HardwaremapConstants;

class Reminder extends OpMode {

    DcMotor frontLeft, frontRight, backLeft, backRight;

    double leftX, leftY, rightX;

    @Override
    public void init() {

        //Wheels hardware map
        HardwaremapConstants map = new HardwaremapConstants();
        map.wheels(hardwareMap);

        //Gets the controller stick values into a variable
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
