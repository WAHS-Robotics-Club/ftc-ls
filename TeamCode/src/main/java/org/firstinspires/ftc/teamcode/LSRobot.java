package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.HardwareMapConstants;

public class LSRobot {

    final int ENCODERS_PER_ROTATION = 1120;
    final double WHEEL_CIRCUMFERENCE = Math.PI * 4; //probably in inches

    private DcMotorEx fl, bl, fr, br, shooterLift, mainLift;
    private CRServo shooterArmLeft, shooterArmRight, clawLeft, clawRight, collectorExtender, collectorLowererL, collectorLowererR;


    public void init(HardwareMap map) {
        fl = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_LEFT);
        fr = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_RIGHT);
        bl = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.MOTOR_BACK_LEFT);
        br = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.MOTOR_BACK_RIGHT);

        shooterLift = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.SHOOTER_LIFT);
        //shooterArmLeft = map.crservo.get(HardwareMapConstants.SHOOTER_ARM_LEFT);
        shooterArmRight = map.crservo.get(HardwareMapConstants.SHOOTER_ARM_RIGHT);

        clawLeft = map.crservo.get(HardwareMapConstants.COLLECTOR_CLAW_LEFT);
        clawRight = map.crservo.get(HardwareMapConstants.COLLECTOR_CLAW_RIGHT);
        collectorExtender = map.crservo.get(HardwareMapConstants.COLLECTOR_EXTENDER);
        collectorLowererL = map.crservo.get(HardwareMapConstants.COLLECTOR_LOWERER_LEFT);
        collectorLowererR = map.crservo.get(HardwareMapConstants.COLLECTOR_LOWERER_RIGHT);

        mainLift = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.LIFTER);


        final int TOLERANCE = 12;

        fl.setTargetPositionTolerance(TOLERANCE);
        fr.setTargetPositionTolerance(TOLERANCE);
        bl.setTargetPositionTolerance(TOLERANCE);
        br.setTargetPositionTolerance(TOLERANCE);

        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        SetRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void Move(double x, double y, double turnPower){
        if (Math.abs(x) > 0.05 || Math.abs(y) > 0.05 || Math.abs(turnPower) > 0.05) {

            fl.setPower(- x + y - turnPower);

            bl.setPower(+ x + y - turnPower);

            fr.setPower(- x - y - turnPower);

            br.setPower(+ x - y - turnPower);

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

    public void MoveCollector(boolean up, boolean down, double lefttrigger, double righttrigger, Telemetry telemetry){
        if(up){
            collectorLowererL.setPower(-0.05);
            collectorLowererR.setPower(0.05);
        } if(down) {
            collectorLowererL.setPower(0.05);
            collectorLowererR.setPower(-0.05);
        }
        else{
            collectorLowererL.setPower(0);
            collectorLowererR.setPower(0);
        }

        if(lefttrigger > 0.05){
            collectorExtender.setPower(lefttrigger);

        } else if(righttrigger > 0.05){
            collectorExtender.setPower(-righttrigger);
        } else {
            collectorExtender.setPower(0);
        }
    }

    public void Collect(boolean collect, boolean outofnames){
        if(collect){
            clawLeft.setPower(1.0);
            clawRight.setPower(-1.0);
        } else if(outofnames){
            clawLeft.setPower(-1.0);
            clawRight.setPower(1.0);
        }
        else {
            clawLeft.setPower(0);
            clawRight.setPower(0);
        }
    }

    public void MoveShooter(boolean up, boolean down){
        if(up){
            shooterLift.setPower(0.3);
        }else if(down){
            shooterLift.setPower(-0.3);
        } else {
            shooterLift.setPower(0);
        }
    }

    public void Shoot(boolean shoot, boolean unshoot) {
        if (shoot) {
            shooterArmLeft.setPower(1.0);
            shooterArmRight.setPower(-1.0);
        }
        else if (unshoot) {
            shooterArmLeft.setPower(-1.0);
            shooterArmRight.setPower(1.0);
        } else {
            shooterArmLeft.setPower(0);
            shooterArmRight.setPower(0);
        }
    }

    public void SetRunMode(DcMotor.RunMode runMode){
        //swaps motors between RUN_USING_ENCODER and RUN_WITHOUT_ENCODER
            fl.setMode(runMode);
            fr.setMode(runMode);
            br.setMode(runMode);
            bl.setMode(runMode);
    }

    public void SetEncoderTarget(int encoderTarget){
        fl.setTargetPosition(encoderTarget);
        fr.setTargetPosition(encoderTarget);
        br.setTargetPosition(encoderTarget);
        bl.setTargetPosition(encoderTarget);
    }

    public void LiftRobot(boolean up, boolean down){
        if(up){
            mainLift.setPower(0.05);
        } if (down){
            mainLift.setPower(-0.05);
        } else {
            mainLift.setPower(0);
        }
    }

//    public void EncoderMove (double x, double y, double speed, double turnradius){
//        Work in progress method for more general movement. Gonna pass on it for now because
//        we don't need to get that fancy.
//        SetRunMode();
//
//        fl.setTargetPosition();
//        fr.setTargetPosition();
//        br.setTargetPosition();
//        bl.setTargetPosition();
//
//        fl.setPower(speed);
//        fr.setPower(speed);
//        br.setPower(speed);
//        bl.setPower(speed);
//    }

    public void AutoMove (int direction, int distance, double power, Telemetry telemetry) throws InterruptedException {
        //this is pretty gross but it allows encoder movement in 8 directions w/o much math
        //SetRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        SetRunMode(DcMotor.RunMode.RUN_TO_POSITION);

        distance = (int)(distance / WHEEL_CIRCUMFERENCE * ENCODERS_PER_ROTATION);

        fl.setTargetPosition(0);
        fr.setTargetPosition(0);
        br.setTargetPosition(0);
        bl.setTargetPosition(0);

        switch(direction){
            case 0:
                fl.setTargetPosition(distance);
                fr.setTargetPosition(distance);
                br.setTargetPosition(-distance);
                bl.setTargetPosition(-distance);
                break;

            case 45:
                fl.setTargetPosition(distance);
                fr.setTargetPosition(0);
                br.setTargetPosition(-distance);
                bl.setTargetPosition(0);
                break;

            case 90:
                fl.setTargetPosition(distance);
                fr.setTargetPosition(-distance);
                br.setTargetPosition(-distance);
                bl.setTargetPosition(distance);
                break;

            case 135:
                fl.setTargetPosition(0);
                fr.setTargetPosition(-distance);
                br.setTargetPosition(0);
                bl.setTargetPosition(distance);
                break;

            case 180:
                fl.setTargetPosition(-distance);
                fr.setTargetPosition(-distance);
                br.setTargetPosition(distance);
                bl.setTargetPosition(distance);
                break;

            case 225:
                fl.setTargetPosition(0);
                fr.setTargetPosition(-distance);
                br.setTargetPosition(0);
                bl.setTargetPosition(distance);
                break;

            case 270:
                fl.setTargetPosition(-distance);
                fr.setTargetPosition(distance);
                br.setTargetPosition(distance);
                bl.setTargetPosition(-distance);
                break;

            case 315:
                fl.setTargetPosition(-distance);
                fr.setTargetPosition(0);
                br.setTargetPosition(distance);
                bl.setTargetPosition(0);
                break;

            default:
                Move(0, 0, 1);
                Thread.sleep(3000);
                telemetry.addData("didn't go to the right case", "bottom text");
                telemetry.update();
                Move(0, 0, 0);
                break;
        }

        while(fl.isBusy() || fr.isBusy() || br.isBusy() || bl.isBusy()){
            fl.setPower(-power);
            fr.setPower(power);
            bl.setPower(-power);
            br.setPower(power);
        }

        SetRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}