package org.firstinspires.ftc.teamcode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardwareconstants.DriveTrain;
import org.firstinspires.ftc.teamcode.hardwareconstants.HardwareMapConstants;

@TeleOp (name = "Wheel Test")
public class TestOp extends OpMode{

    DriveTrain driveTrain = new DriveTrain();
//    ElapsedTime runtime = new ElapsedTime();

    DcMotor frontLeftWheel, frontRightWheel, backLeftWheel, backRightWheel;

    @Override
    public void init() {

        frontLeftWheel = hardwareMap.dcMotor.get(HardwareMapConstants.frontLeftWheel);
        backLeftWheel = hardwareMap.dcMotor.get(HardwareMapConstants.backLeftWheel);
        frontRightWheel = hardwareMap.dcMotor.get(HardwareMapConstants.frontRightWheel);
        backRightWheel = hardwareMap.dcMotor.get(HardwareMapConstants.backRightWheel);

        frontLeftWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        double xAxis = gamepad1.left_stick_x, yAxis = -gamepad1.left_stick_y, rotation = gamepad1.right_stick_x;
//        driveTrain.mecanumDrive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, telemetry);
        if (Math.abs(xAxis) > 0.01 || Math.abs(yAxis) > 0.01 || Math.abs(rotation) > 0.01){
            frontLeftWheel.setPower(-xAxis + yAxis - rotation);
            frontRightWheel.setPower(-xAxis - yAxis - rotation);
            backLeftWheel.setPower(xAxis + yAxis - rotation);
            backRightWheel.setPower(xAxis - yAxis - rotation);

            telemetry.addData("left stick x", xAxis);
            telemetry.addData("left stick y", yAxis);
            telemetry.addData("right stick x", rotation);
            telemetry.update();

        } else {
            frontLeftWheel.setPower(0);
            frontRightWheel.setPower(0);
            backLeftWheel.setPower(0);
            backRightWheel.setPower(0);
        }
    }
}
