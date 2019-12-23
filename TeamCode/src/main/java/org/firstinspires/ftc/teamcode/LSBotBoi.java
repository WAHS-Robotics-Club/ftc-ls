package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardwareconstants.HardwareMapConstants;

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

        foundationHook = map.servo.get(HardwareMapConstants.foundationHook);


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

    //Will stop movement for Autonomous program
    public void stop(Telemetry telemetry) {move(0, 0, 0, false, telemetry);}

    //The program rotates both the crane arm and the servos that rotate the grabber arms
    public void craneMove(double rightTrigger, double leftTrigger, int craneMinimumPosistion, Telemetry telemetry){

        double cranePower = rightTrigger - leftTrigger;

        if ((rightTrigger > .01 ^ leftTrigger > 0.1) && craneMotorRight.getCurrentPosition() > craneMinimumPosistion){

            //
            craneMotorRight.setPower(cranePower * .75);
            craneMotorLeft.setPower(-cranePower * .75);

            //These servos are supposed to spin at the same rate as the motors.
            craneOrientationLeft.setPower(-craneMotorLeft.getPower());
            craneOrientationRight.setPower(-craneMotorRight.getPower());

            telemetry.addData("Crane LServo Spd:", craneOrientationLeft.getPower());
            telemetry.addData("Crane RServo Spd:", craneOrientationRight.getPower());
            telemetry.update();

        }else{
            craneMotorLeft.setPower(0);
            craneMotorRight.setPower(0);

            craneOrientationLeft.setPower(0);
            craneOrientationRight.setPower(0);
        }

    }


    public void setRunMode(DcMotor.RunMode runMode) {
        fl.setMode(runMode);
        bl.setMode(runMode);
    }


    int bumperCount = -1;
    double timeSinceButtonPress = 0;
    public void setFoundationHook (boolean leftBumper, Telemetry telemetry) {
        if (leftBumper == true){
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

}
