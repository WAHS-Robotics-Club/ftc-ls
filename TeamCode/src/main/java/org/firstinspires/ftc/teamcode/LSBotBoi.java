package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardwareconstants.HardwareMapConstants;

import java.sql.Time;
import java.util.Timer;

public class LSBotBoi {

    final int ENCODER_TICKS_PER_ROTATION = 1120;
    final double WHEEL_CIRCUMFERENCE = Math.PI * 4; //In inches


    private DcMotor fr, fl, bl, br;
    public DcMotor craneMotorLeft, craneMotorRight;
    private CRServo craneOrientationLeft, craneOrientationRight, grabServoLeft, grabServoRight;
    private Servo foundationHook;


    public void initialize(HardwareMap map) {

        fr = map.dcMotor.get(HardwareMapConstants.frontRightWheel);
        fl = map.dcMotor.get(HardwareMapConstants.frontLeftWheel);
        bl = map.dcMotor.get(HardwareMapConstants.backLeftWheel);
        br = map.dcMotor.get(HardwareMapConstants.backRightWheel);

        craneMotorLeft = map.dcMotor.get(HardwareMapConstants.craneLeft);
        craneMotorRight = map.dcMotor.get(HardwareMapConstants.craneRight);

        craneOrientationLeft = map.crservo.get(HardwareMapConstants.craneServoLeft);
        craneOrientationRight = map.crservo.get(HardwareMapConstants.craneServoRight);
        grabServoLeft = map.crservo.get(HardwareMapConstants.grabLeft);
        grabServoRight = map.crservo.get(HardwareMapConstants.grabRight);

//        foundationHook = map.servo.get(HardwareMapConstants.foundationHook);


        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        craneMotorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        craneMotorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        final int CRANE_START_POSITION = 50;


        craneMotorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        craneMotorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
    }

    //Mecanum drive train
    public void move(double x, double y, double rotation, boolean rightBumper, Telemetry telemetry) {

        //pressing 'right bumper' will reduce the power on movement
        if (Math.abs(x) > .01 || Math.abs(y) >.01 || Math.abs(rotation) > 0.01 && (rightBumper = true)) {

            fl.setPower((-x + y - rotation) * .75);
            fr.setPower((-x - y - rotation) * .75);
            bl.setPower((x + y - rotation) * .75);
            br.setPower((x - y - rotation) * .75);

            telemetry.addData("left stick x", x);
            telemetry.addData("left stick y", y);
            telemetry.addData("right stick x", rotation);
            telemetry.update();

        }else if (Math.abs(x) > .01 || Math.abs(y) >.01 || Math.abs(rotation) > 0.01) {

            fl.setPower(-x + y - rotation);
            fr.setPower(-x - y - rotation);
            bl.setPower(x + y - rotation);
            br.setPower(x - y - rotation);

            telemetry.addData("left stick x", x);
            telemetry.addData("left stick y", y);
            telemetry.addData("right stick x", rotation);
            telemetry.update();

        }else{
            fl.setPower(0);
            fr.setPower(0);
            bl.setPower(0);
            br.setPower(0);
        }
    }

    //Will stop movement for AutonomousRed program
    public void stop(Telemetry telemetry) {move(0, 0, 0, false, telemetry);}

    //The program rotates both the crane arm and the servos that rotate the grabber arms
    public void craneMove(double rightTrigger, double leftTrigger, Telemetry telemetry) {
        double t = 0;
        double cranePower = rightTrigger - leftTrigger;

        if (rightTrigger > 0.01 ^ leftTrigger > 0.01) {
            //This rotates the crane arms at a lower speed
            craneMotorRight.setPower(cranePower * .6);
            craneMotorLeft.setPower(-cranePower * .6);

            //These servos are supposed to spin at the same rate as the motors.
            craneOrientationLeft.setPower(-cranePower * .6);
            craneOrientationRight.setPower(cranePower * .6);

            telemetry.addData("Crane LServo Spd:", craneOrientationLeft.getPower());
            telemetry.addData("Crane RServo Spd:", craneOrientationRight.getPower());
            telemetry.update();

            t = -craneMotorRight.getPower();

        }else if(t != 0) {
            t = Math.sqrt(-t);
            craneMotorRight.setPower(-t);
        }
        else{
            craneMotorLeft.setPower(0);
            craneMotorRight.setPower(0);

            craneOrientationLeft.setPower(0);
            craneOrientationRight.setPower(0);
        }

    }

    private int xCount = -1;
    private double TimeSinceXPress = 0;
    public void grab(boolean x){
        if (x){
            if(System.nanoTime() - TimeSinceXPress < 400){
                TimeSinceXPress = System.nanoTime();
                xCount *= -1;
            }

            if (xCount == 1){
                grabServoRight.setPower(.4);
                grabServoLeft.setPower(-.4);
            }else{
                foundationHook.setPosition(0);
            }
        }
    }


    public void setRunMode(DcMotor.RunMode runMode) {
        fl.setMode(runMode);
        bl.setMode(runMode);
    }


    private int bumperCount = -1;
    private double timeSinceButtonPress = 0;
    public void setFoundationHook (boolean leftBumper, Telemetry telemetry) {
        if (leftBumper){
            if(System.nanoTime() - timeSinceButtonPress < 400){
                timeSinceButtonPress = System.nanoTime();
                bumperCount *= -1;
            }

            if (bumperCount == 1){
                foundationHook.setPosition(.7);
            }else{
                foundationHook.setPosition(0);
            }
        }
    }

    public void autoMove(double distance, double angle, double rotation, boolean slow, Telemetry telemetry) throws InterruptedException {
        double x = Math.cos(angle) * distance;
        double y = Math.sin(angle) * distance;

        move(x / distance, y / distance, rotation, slow, telemetry);

        setRunMode(DcMotor.RunMode.RUN_TO_POSITION);

        fl.setTargetPosition((int) (distance * Math.cos(Math.toRadians(45 - angle)) / WHEEL_CIRCUMFERENCE * ENCODER_TICKS_PER_ROTATION));
        bl.setTargetPosition((int) (distance * Math.cos(Math.toRadians(45 + angle)) / WHEEL_CIRCUMFERENCE * ENCODER_TICKS_PER_ROTATION));


        move(x / distance, y / distance, rotation, slow, telemetry);

        telemetry.addData("I made it past the first step", "it works the easy way");
        telemetry.update();

        while (fl.isBusy() || bl.isBusy()) {
            Thread.sleep(1);
            telemetry.addData("fl is busy?", fl.isBusy());
            telemetry.addData("bl is busy?", bl.isBusy());
            telemetry.update();
        }
        stop(telemetry);
    }
}
