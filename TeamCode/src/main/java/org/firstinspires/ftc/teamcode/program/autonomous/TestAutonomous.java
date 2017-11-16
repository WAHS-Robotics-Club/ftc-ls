package org.firstinspires.ftc.teamcode.program.autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TestAutonomous {

    protected final int ENCODER_TICKS_PER_ROTATION = 1120;
    protected final double WHEEL_CIRCUMFERENCE = 4 * Math.PI;

    private DcMotor fr, fl, br, bl;

    public void setUp (HardwareMap hardwareMap) {
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");

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

    public void move(double power, double angle, double distance, double moveSpeed) {
        double x = power * Math.cos(angle); //Takes the angle in radians
        double y = power * Math.sin(angle); //Takes the angle in radians

        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        double distanceRequested = (distance / WHEEL_CIRCUMFERENCE) * ENCODER_TICKS_PER_ROTATION;

        while (fr.getCurrentPosition() <= distanceRequested) {
            fr.setPower((-x + y) * moveSpeed);
            fl.setPower((-x - y) * moveSpeed);
            br.setPower((+x + y) * moveSpeed);
            bl.setPower((-y + x) * moveSpeed);
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

