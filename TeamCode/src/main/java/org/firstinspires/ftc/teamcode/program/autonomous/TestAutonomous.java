package org.firstinspires.ftc.teamcode.program.autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.program.HardwareMapConstants;

public class TestAutonomous {

    protected final int ENCODER_TICKS_PER_ROTATION = 1120;
    protected final double WHEEL_CIRCUMFERENCE = 4 * Math.PI;

    private DcMotor fr, fl, br, bl;
    private DcMotor arm;
    private Servo leftClaw, rightClaw;

    public void setUp (HardwareMap hardwareMap) {
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
    }

    private void setRunMode(DcMotor.RunMode mode) {
        fl.setMode(mode);
        fr.setMode(mode);
        bl.setMode(mode);
        fr.setMode(mode);
    }

    public void move(double power, double angle, double distance) {
        double x = power * Math.cos(angle);

        double y = power * Math.sin(angle); //Takes the angle in radians

        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        double distanceRequested = (distance / WHEEL_CIRCUMFERENCE) * ENCODER_TICKS_PER_ROTATION;

        while (fr.getCurrentPosition() <= distanceRequested) {
            fr.setPower((-x + y));
            fl.setPower((-x - y));
            br.setPower((+x + y));
            bl.setPower((-y + x));
        }
    }

    protected void turn(double turnSpeed, double turnAngle) {
        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        double turnTo = (turnAngle / WHEEL_CIRCUMFERENCE) * ENCODER_TICKS_PER_ROTATION;

        while (fr.getCurrentPosition() <= turnTo) {
            fl.setPower(turnSpeed);
            fr.setPower(turnSpeed);
            bl.setPower(turnSpeed);
            br.setPower(turnSpeed);
        }
    }
}

