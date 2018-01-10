package org.firstinspires.ftc.teamcode.program.autonomous;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.program.HardwareMapConstants;
import static java.lang.Math.*;

public class TestAutonomous {

    protected final int ENCODER_TICKS_PER_ROTATION = 1120;
    protected final double WHEEL_CIRCUMFERENCE = 4 * PI;

    private DcMotorEx fr, fl, br, bl;
    private DcMotor arm;
    private Servo jewelWhacker;

    private Servo leftClaw, rightClaw;

    private boolean isRed;

    ColorSensor cs;

    public TestAutonomous(boolean isRedFieldSide) {
        isRed = isRedFieldSide;
    }

    public void setUp(HardwareMap hardware) {
        fl = (DcMotorEx) hardware.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_LEFT);
        fr = (DcMotorEx) hardware.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_RIGHT);
        bl = (DcMotorEx) hardware.dcMotor.get(HardwareMapConstants.MOTOR_BACK_LEFT);
        br = (DcMotorEx) hardware.dcMotor.get(HardwareMapConstants.MOTOR_BACK_RIGHT);

        arm = hardware.dcMotor.get(HardwareMapConstants.MOTOR_ARM);

        jewelWhacker = hardware.servo.get(HardwareMapConstants.COLOR_SERVO);

        cs = hardware.colorSensor.get(HardwareMapConstants.COLOR_SENSOR);

        leftClaw = hardware.servo.get(HardwareMapConstants.LEFT_CLAW);
        rightClaw = hardware.servo.get(HardwareMapConstants.RIGHT_CLAW);

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

    public void initArm() throws InterruptedException {
        arm.setPower(0.4);
        Thread.sleep(600);
        arm.setPower(0);
        leftClaw.setPosition(0.74);
        rightClaw.setPosition(0.20);
    }

    public void lowerArm() throws InterruptedException {
        arm.setPower(-0.4);
        Thread.sleep(600);
        arm.setPower(0);
    }

    private void setRunMode(DcMotor.RunMode mode) {
        fl.setMode(mode);
        fr.setMode(mode);
        bl.setMode(mode);
        br.setMode(mode);
    }

    public void stop() {
        fr.setPower(0);
        fl.setPower(0);
        br.setPower(0);
        bl.setPower(0);

        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    void getTelemetry(Telemetry telemetry) {
        telemetry.addData("fl current position", fl.getCurrentPosition());
        telemetry.addData("fr current position", fr.getCurrentPosition());
        telemetry.addData("bl current position", bl.getCurrentPosition());
        telemetry.addData("br current position", br.getCurrentPosition());

        telemetry.addData("fl power", fl.getPower());
        telemetry.addData("fr power", fr.getPower());
        telemetry.addData("bl power", bl.getPower());
        telemetry.addData("br power", br.getPower());

//        telemetry.addData("how warm it", Temperature);
    }

    public void move(double power, double angle, double distance, Telemetry telemetry) throws InterruptedException {

        double distanceRequested = (distance / WHEEL_CIRCUMFERENCE) * ENCODER_TICKS_PER_ROTATION;

        double x = power * cos(toRadians(angle)); //takes the angle in degrees
        double y = power * sin(toRadians(angle));

        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        holonomicMove(x, y, 0);

        double flbrTarget = (abs(distanceRequested * sin(toRadians(angle + 45))));
        double frblTarget = (abs(distanceRequested * cos(toRadians(angle + 45))));

        fl.setTargetPosition((int) (flbrTarget * signum(fl.getPower())));
        br.setTargetPosition((int) (flbrTarget * signum(br.getPower())));
        fr.setTargetPosition((int) (frblTarget * signum(fr.getPower())));
        bl.setTargetPosition((int) (frblTarget * signum(bl.getPower())));

        stop();
        Thread.sleep(1000);

        setRunMode(DcMotor.RunMode.RUN_TO_POSITION);

        holonomicMove(x, y, 0);

        while(fr.isBusy() || fl.isBusy() || br.isBusy() || bl.isBusy()) {
            Thread.sleep(1);
//            getTelemetry(telemetry);
//            telemetry.update();
        }

        stop();
    }

    final int TIME = 300;

    public void whackJewel() throws InterruptedException {
//        if(cs.red() - cs.blue() > 10){
//            turn(0.3 * (isRed ? 1 : -1));
//            Thread.sleep(TIME);
//        }
        jewelWhacker.setPosition(1);
    }

    public void holonomicMove(double x, double y, double turnPower) {
        fr.setPower(-x - y - turnPower);
        fl.setPower(-x + y + turnPower);
        br.setPower(+x - y + turnPower);
        bl.setPower(-y + x - turnPower);
    }

    public void clamp() {
        //closed
        leftClaw.setPosition(0.225);
        rightClaw.setPosition(0.725);
    }

//    protected void turn(double turnSpeed, double turnAngle) {
//        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//
//        double turnTo = (turnAngle / WHEEL_CIRCUMFERENCE) * ENCODER_TICKS_PER_ROTATION;
//
//        while (fr.getCurrentPosition() <= fr.getTargetPosition()) {
//            fl.setPower(turnSpeed);
//            fr.setPower(turnSpeed);
//            bl.setPower(turnSpeed);
//            br.setPower(turnSpeed);
//        }
//    }

    public void turn(double power){
        if(abs(power) < 0.05){
            stop();
        } else {
            fl.setPower(power);
            fr.setPower(power);
            bl.setPower(power);
            br.setPower(power);
        }

    }
}

