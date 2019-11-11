package org.firstinspires.ftc.teamcode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardwareconstants.DriveTrain;
import org.firstinspires.ftc.teamcode.hardwareconstants.HardwareMapConstants;

@TeleOp (name = "TestOp")
public class TestOp extends OpMode{

    DriveTrain driveTrain = new DriveTrain();
    HardwareMapConstants hardwareMap = new HardwareMapConstants();
    ElapsedTime runtime = new ElapsedTime();

    DcMotor frontLeftWheel, frontRightWheel, backLeftWheel, backRightWheel;

    @Override
    public void init() {
        hardwareMap.driveTrainWheels();

        frontLeftWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {runtime.reset(); }

    @Override
    public void loop() {

        driveTrain.mecanumDrive();



    }
}
