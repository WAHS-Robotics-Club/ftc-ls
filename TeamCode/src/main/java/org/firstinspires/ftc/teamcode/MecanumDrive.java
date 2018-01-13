package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.program.HardwareMapConstants;

/**
 * Created by Wintermute on 1/9/18.
 */

public class MecanumDrive {
    final double ENCODERS_PER_ROTATION = 1120;
    final double WHEEL_CIRCUMFERENCE = Math.PI * 3; //probably in inches

    private DcMotorEx fl, bl, fr, br;

    public void init(HardwareMap map) {
        fl = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_LEFT);
        fr = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_RIGHT);
        bl = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.MOTOR_BACK_LEFT);
        br = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.MOTOR_BACK_RIGHT);

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

    public void stop () {
        moveAndTurn(0, 0, 0);
    }

    public void moveAndTurn(double x, double y, double turnPower) {
        if (Math.abs(x) > 0.05 || Math.abs(y) > 0.05 || Math.abs(turnPower) > 0.05) {
            fl.setPower((-x - y) - turnPower); //decreases x, increases y with positive power
            bl.setPower((-x + y)- turnPower);
            fr.setPower((+x - y)- turnPower);
            br.setPower((+x + y) - turnPower);
        } else {
            fl.setPower(0);
            bl.setPower(0);
            fr.setPower(0);
            br.setPower(0);
        }
    }
    public void polarMoveAndTurn(double power, double angle, double turnPower){
        moveAndTurn(power * Math.cos(Math.toRadians(angle)), power * Math.sin(Math.toRadians(angle)), turnPower);
    }
    public void encoderMove (double targetDistance, double power, double angle){
        int encoders = (int)(targetDistance / WHEEL_CIRCUMFERENCE  * ENCODERS_PER_ROTATION);

        fl.setTargetPosition(encoders);
        bl.setTargetPosition(encoders);
        fr.setTargetPosition(-encoders);
        br.setTargetPosition(-encoders);

        polarMoveAndTurn(power, 90, angle);
    }
    
    private void setRunMode (DcMotor.RunMode mode){
        fl.setMode(mode);
        bl.setMode(mode);
        fr.setMode(mode);
        br.setMode(mode);
    }

    public void turn(double power){
        moveAndTurn(0, 0, power);
    }
}
