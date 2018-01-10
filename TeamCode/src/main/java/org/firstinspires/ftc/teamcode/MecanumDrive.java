package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.program.HardwareMapConstants;

/**
 * Created by Wintermute on 1/9/18.
 */

public class MecanumDrive {
    final double ENCODERS_PER_ROTATION = 1120;
    final double WHEEL_CIRCUMFERENCE = Math.PI * 3;

    private DcMotor fl, bl, fr, br;

    public void init(HardwareMap map) {
        fl = map.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_LEFT);
        bl = map.dcMotor.get(HardwareMapConstants.MOTOR_BACK_LEFT);
        fr = map.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_RIGHT);
        br = map.dcMotor.get(HardwareMapConstants.MOTOR_BACK_RIGHT);
    }

    public void stop () {
        moveAndTurn(0, 0, 0);
    }

    public void moveAndTurn(double x, double y, double turnPower) {
        if (Math.abs(x) > 0.05 || Math.abs(y) > 0.05 || Math.abs(turnPower) > 0.05) {
            fl.setPower(y + x - turnPower);
            bl.setPower(y - x - turnPower);
            fr.setPower(-y - x - turnPower);
            br.setPower(-y + x - turnPower);
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
}
