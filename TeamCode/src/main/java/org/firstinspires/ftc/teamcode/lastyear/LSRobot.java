package org.firstinspires.ftc.teamcode.lastyear;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.lastyear.util.HardwareMapConstants;
import org.firstinspires.ftc.teamcode.lastyear.util.Navigator;

public class LSRobot {

    final int ENCODERS_PER_ROTATION = 1120;
    final double WHEEL_CIRCUMFERENCE = Math.PI * 4; //probably in inches

    private DcMotorEx fl, bl, fr, br, shooterLift, mainLift;
    private Servo clawLeft, clawRight;
    private CRServo shooterArmLeft, shooterArmRight, collectorExtender, collectorLowererL, collectorLowererR;

    Navigator navigator = new Navigator();

    public void init(HardwareMap map) {
        fl = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_LEFT);
        fr = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_RIGHT);
        bl = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.MOTOR_BACK_LEFT);
        br = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.MOTOR_BACK_RIGHT);

        shooterLift = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.SHOOTER_LIFT);
        //shooterArmLeft = map.crservo.get(HardwareMapConstants.SHOOTER_ARM_LEFT);
        shooterArmRight = map.crservo.get(HardwareMapConstants.SHOOTER_ARM_RIGHT);

        clawLeft = map.servo.get(HardwareMapConstants.COLLECTOR_CLAW_LEFT);
        clawRight = map.servo.get(HardwareMapConstants.COLLECTOR_CLAW_RIGHT);
        collectorExtender = map.crservo.get(HardwareMapConstants.COLLECTOR_EXTENDER);
        collectorLowererL = map.crservo.get(HardwareMapConstants.COLLECTOR_LOWERER_LEFT);
        collectorLowererR = map.crservo.get(HardwareMapConstants.COLLECTOR_LOWERER_RIGHT);

        mainLift = (DcMotorEx) map.dcMotor.get(HardwareMapConstants.LIFTER);


        final int TOLERANCE = 12;

        fl.setTargetPositionTolerance(TOLERANCE);
        fr.setTargetPositionTolerance(TOLERANCE);
        bl.setTargetPositionTolerance(TOLERANCE);
        br.setTargetPositionTolerance(TOLERANCE);

        mainLift.setTargetPositionTolerance(TOLERANCE);


        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        mainLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        SetRunMode(DcMotor.RunMode.RUN_USING_ENCODER);

        navigator.init(map);
    }

    public void LiftAuto(Telemetry telemetry) {
        mainLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        telemetry.addData("lifter at", mainLift.getCurrentPosition());
        telemetry.update();
    }

    public void Lower() throws InterruptedException {
        mainLift.setPower(-0.7);
        Thread.sleep(2000);
        mainLift.setPower(0);
    }

    public void Move(double x, double y, double turnPower, Telemetry telemetry) {
        if (Math.abs(x) > 0.05 || Math.abs(y) > 0.05 || Math.abs(turnPower) > 0.05) {
            fl.setPower(- x + y - turnPower);

            bl.setPower(+ x + y - turnPower);

            fr.setPower(- x - y - turnPower);

            br.setPower(+ x - y - turnPower);

//            telemetry.addData("fl", fl.getCurrentPosition());
            telemetry.addData("fr", fr.getCurrentPosition());
            telemetry.addData("bl", bl.getCurrentPosition());
//            telemetry.addData("br", br.getCurrentPosition());

            telemetry.update();

        } else {
            fl.setPower(0);
            bl.setPower(0);
            fr.setPower(0);
            br.setPower(0);
        }
    }

    public void Move(double x, double y, double turnPower, long time, Telemetry telemetry) throws InterruptedException {
        Move(x, y, turnPower, telemetry);

        Thread.sleep(time * 1000);
    }

    public void MoveCollector(boolean up, boolean down, double lefttrigger, double righttrigger, Telemetry telemetry) {
        if (up) {
            collectorLowererL.setPower(-0.3);
            collectorLowererR.setPower(0.3);
        } else if (down) {
            collectorLowererL.setPower(-0.6);
            collectorLowererR.setPower(0.6);
        }

        if (lefttrigger > 0.05) {
            collectorExtender.setPower(lefttrigger);
        } else if (righttrigger > 0.05) {
            collectorExtender.setPower(-righttrigger);
        } else {
            collectorExtender.setPower(0);
        }
    }

    public void Collect(boolean collect, boolean outofnames) {
        if (collect) {
            clawLeft.setPosition(clawLeft.getPosition() + 0.03);
            clawRight.setPosition(clawRight.getPosition() - 0.03);
        } else if (outofnames) {
            clawLeft.setPosition(clawLeft.getPosition() - 0.03);
            clawRight.setPosition(clawRight.getPosition() + 0.03);
        }
    }

    public void MoveShooter(boolean up, boolean down) {
        if (up) {
            shooterLift.setPower(0.6);
        } else if (down) {
            shooterLift.setPower(-0.6);
        } else {
            shooterLift.setPower(0);
        }
    }

    public void Shoot(boolean shoot, boolean unshoot) {
        if (shoot) {
            //shooterArmLeft.setPower(1.0);
            shooterArmRight.setPower(-1.0);
        } else if (unshoot) {
            //shooterArmLeft.setPower(-1.0);
            shooterArmRight.setPower(1.0);
        } else {
            //shooterArmLeft.setPower(0);
            shooterArmRight.setPower(0);
        }
    }

    public void SetRunMode(DcMotor.RunMode runMode) {
        //swaps motors between RUN_USING_ENCODER and RUN_WITHOUT_ENCODER
        fl.setMode(runMode);
        bl.setMode(runMode);
    }

    public void SetEncoderTarget(int encoderTarget) {
        fl.setTargetPosition(encoderTarget);
        bl.setTargetPosition(encoderTarget);
    }

    public void LiftRobot(boolean up, boolean down, Telemetry telemetry) {
        if (up) {
            mainLift.setPower(1.0);
        } else if (down) {
            mainLift.setPower(-1.0);
        } else {
            mainLift.setPower(0);
        }

        telemetry.addData("lifter at", mainLift.getCurrentPosition());
        telemetry.addData("current power", mainLift.getPower());

        telemetry.update();
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

    public void Stop(Telemetry telemetry){
        Move(0, 0, 0, telemetry);
    }

    public void CameraTest(HardwareMap map){navigator.Tfod(map);
    }



    public void AutoMove(double distance, double angle, double turnpower, Telemetry telemetry) throws InterruptedException {
//        Move(0, 1, 0, telemetry);
//
//        SetRunMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        fl.setTargetPosition(2120);
//        fr.setTargetPosition(2120);
//
//        Move(0, 1, 0, telemetry);
//
//        while(fl.isBusy() || bl.isBusy()){
//            telemetry.addData("fl at", fl.getCurrentPosition());
//            telemetry.addData("bl at", bl.getCurrentPosition());
//            telemetry.update();
//            Thread.sleep(1);
//        }
//
//        Stop(telemetry);

        double x = Math.cos(angle) * distance;
        double y = Math.sin(angle) * distance;

        Move(x / distance, y / distance, turnpower, telemetry);

        SetRunMode(DcMotor.RunMode.RUN_TO_POSITION);

        fl.setTargetPosition((int) (distance * Math.cos(Math.toRadians(45 - angle)) / WHEEL_CIRCUMFERENCE * ENCODERS_PER_ROTATION));
        bl.setTargetPosition((int) (distance * Math.cos(Math.toRadians(45 + angle)) / WHEEL_CIRCUMFERENCE * ENCODERS_PER_ROTATION));


        Move(x / distance, y / distance, turnpower, telemetry);

        telemetry.addData("I made it past the first step", "it works the easy way");
        telemetry.update();

        while (fl.isBusy() || bl.isBusy()) {
            Thread.sleep(1);
            telemetry.addData("fl is busy?", fl.isBusy());
            telemetry.addData("bl is busy?", bl.isBusy());
            telemetry.update();
        }
        Stop(telemetry);
    }

//    public void AutoMove (int direction, int distance, double power, Telemetry telemetry) throws InterruptedException {
//        //this is pretty gross but it allows encoder movement in 8 directions w/o much math
//        //SetRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        SetRunMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        distance = (int)(distance / WHEEL_CIRCUMFERENCE * ENCODERS_PER_ROTATION);
//
//        fl.setTargetPosition(0);
//        bl.setTargetPosition(0);
//
//        switch(direction){
//            case 0:
//                fl.setTargetPosition(distance);
//                bl.setTargetPosition(-distance);
//                break;
//
//            case 45:
//                fl.setTargetPosition(distance);
//                bl.setTargetPosition(0);
//                break;
//
//            case 90:
//                fl.setTargetPosition(distance);
//                bl.setTargetPosition(distance);
//                break;
//
//            case 135:
//                fl.setTargetPosition(0);
//                bl.setTargetPosition(distance);
//                break;
//
//            case 180:
//                fl.setTargetPosition(-distance);
//                bl.setTargetPosition(distance);
//                break;
//
//            case 225:
//                fl.setTargetPosition(0);
//                bl.setTargetPosition(distance);
//                break;
//
//            case 270:
//                fl.setTargetPosition(-distance);
//                bl.setTargetPosition(-distance);
//                break;
//
//            case 315:
//                fl.setTargetPosition(-distance);
//                bl.setTargetPosition(0);
//                break;
//
//            default:
//                Move(0, 0, 1, telemetry);
//                Thread.sleep(3000);
//                telemetry.addData("didn't go to the right case", "bottom text");
//                telemetry.update();
//                Move(0, 0, 0, telemetry);
//                break;
//        }
//
//        while(fl.isBusy() || bl.isBusy()){
//            fl.setPower(-power);
//            fr.setPower(power);
//            bl.setPower(-power);
//            br.setPower(power);
//        }
//
//        SetRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//    }
}