package org.firstinspires.ftc.teamcode.program.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.program.HardwareMapConstants;

@TeleOp (name = "Jan the Man")
public class Jan extends OpMode{

    DcMotor fl,fr,bl,br,arm;
    Servo leftClaw,rightClaw;
    final int ZERO = 0;

    @Override
    public void init() {
        fl = hardwareMap.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_LEFT);
        fr = hardwareMap.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_RIGHT);
        bl = hardwareMap.dcMotor.get(HardwareMapConstants.MOTOR_BACK_LEFT);
        br = hardwareMap.dcMotor.get(HardwareMapConstants.MOTOR_BACK_RIGHT);

        arm = hardwareMap.dcMotor.get(HardwareMapConstants.MOTOR_ARM);

        leftClaw = hardwareMap.servo.get(HardwareMapConstants.LEFT_CLAW);
        rightClaw = hardwareMap.servo.get(HardwareMapConstants.RIGHT_CLAW);

        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        leftClaw.setPosition(1);
//        rightClaw.setPosition(0.5);

        telemetry.addData ("Left claw is at position ", leftClaw.getPosition());
        telemetry.addData ("Right claw is at position ", rightClaw.getPosition());
        telemetry.update();
    }

    @Override
    public void loop() {
        double change = 0.0001;

        if (gamepad1.left_bumper){
            leftClaw.setPosition(leftClaw.getPosition() + change);
            rightClaw.setPosition(rightClaw.getPosition() + change);
        }
        if (gamepad1.right_bumper) {
            rightClaw.setPosition(rightClaw.getPosition() - change);
            leftClaw.setPosition(leftClaw.getPosition() - change);
        }

        telemetry.addData ("Left claw is at position ", leftClaw.getPosition());
        telemetry.addData ("Right claw is at position ", rightClaw.getPosition());
        telemetry.update();
    }
}
