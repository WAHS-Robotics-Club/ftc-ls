package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.HardwareMapConstants;

public class LSRobot {
    final double ENCODERS_PER_ROTATION = 1120;
    final double WHEEL_CIRCUMFERENCE = Math.PI * 3; //probably in inches

    private DcMotorEx fl, bl, fr, br, shooterLift, collectorLift;
    private Servo shooterArm, collectorArm;

    public void init(HardwareMap map) {
        fl = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_LEFT);
        fr = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_RIGHT);
        bl = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.MOTOR_BACK_LEFT);
        br = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.MOTOR_BACK_RIGHT);

        shooterLift = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.SHOOTER_LIFT);
        shooterArm = map.servo.get(HardwareMapConstants.SHOOTER_ARM);

        collectorLift = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.COLLECTOR_LIFT);
        collectorArm = map.servo.get(HardwareMapConstants.COLLECTOR_ARM);

        final int TOLERANCE = 12;

        fl.setTargetPositionTolerance(TOLERANCE);
        fr.setTargetPositionTolerance(TOLERANCE);
        bl.setTargetPositionTolerance(TOLERANCE);
        br.setTargetPositionTolerance(TOLERANCE);

        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void Move(double x, double y, double turnPower){
        if (Math.abs(x) > 0.05 || Math.abs(y) > 0.05 || Math.abs(turnPower) > 0.05) {

            fl.setPower(+ x + y - turnPower);

            bl.setPower(- x + y - turnPower);

            fr.setPower(+ x - y - turnPower);

            br.setPower(- x - y - turnPower);

        } else {
            fl.setPower(0);
            bl.setPower(0);
            fr.setPower(0);
            br.setPower(0);
        }
    }

    public void Move(double x, double y, double turnPower, long time) throws InterruptedException {
        Move(x, y, turnPower);

        Thread.sleep(time * 1000);
    }

    public void MoveCollector(boolean up, boolean down){
        if(up){
            collectorLift.setPower(0.2);
        } else if(down) {
            collectorLift.setPower(-0.2);
        } else {
            collectorLift.setPower(0.0);
        }
    }

    public void Collect(boolean collect){
        if(collect){
            collectorArm.setPosition(2.5);
        } else {
            collectorArm.setPosition(0.0);
        }
    }

    public void MoveShooter(double up, double down){
        if(Math.abs(up) > 0.05){
            shooterLift.setPower(0.2);
        }else if(Math.abs(down) > 0.05){
            shooterLift.setPower(-0.2);
        } else {
            shooterLift.setPower(0);
        }
    }

    public void Shoot(boolean shoot){
        if(shoot){
            shooterArm.setPosition(2.5);
        } else {
            shooterArm.setPosition(0.0);
        }
    }
}
