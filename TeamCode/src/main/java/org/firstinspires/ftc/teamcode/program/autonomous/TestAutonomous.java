package org.firstinspires.ftc.teamcode.program.autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.program.HardwareMapConstants;
import static java.lang.Math.*;

public class TestAutonomous {

    protected final int ENCODER_TICKS_PER_ROTATION = 1120;
    protected final double WHEEL_CIRCUMFERENCE = 4 * Math.PI;

    private DcMotor fr, fl, br, bl;
    private DcMotor arm;
    private Servo leftClaw, rightClaw;

    public void setUp (HardwareMap monkey) {
        fl = monkey.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_LEFT);
        fr = monkey.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_RIGHT);
        bl = monkey.dcMotor.get(HardwareMapConstants.MOTOR_BACK_LEFT);
        br = monkey.dcMotor.get(HardwareMapConstants.MOTOR_BACK_RIGHT);

        arm = monkey.dcMotor.get(HardwareMapConstants.MOTOR_ARM);

        leftClaw = monkey.servo.get(HardwareMapConstants.LEFT_CLAW);
        rightClaw = monkey.servo.get(HardwareMapConstants.RIGHT_CLAW);

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

    void stop(){
        fr.setPower(0);
        fl.setPower(0);
        br.setPower(0);
        bl.setPower(0);

        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    void getTelemetry(Telemetry telemetry){
        telemetry.addData("fl target", fl.getCurrentPosition());
        telemetry.addData("fr target", fr.getCurrentPosition());
        telemetry.addData("bl target", bl.getCurrentPosition());
        telemetry.addData("br target", br.getCurrentPosition());

        telemetry.addData("fl power", fl.getPower());
        telemetry.addData("fr power", fr.getPower());
        telemetry.addData("bl power", bl.getPower());
        telemetry.addData("br power", br.getPower());

//        telemetry.addData("how warm it", Temperature);
    }

    public void move(double power, double angle, double distance) throws InterruptedException {

        double distanceRequested = (distance / WHEEL_CIRCUMFERENCE) * ENCODER_TICKS_PER_ROTATION;

        double x = power * cos(toRadians(angle));//takes the angle in degrees
        double y = power * sin(toRadians(angle));

        setRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        fl.setPower(-x - y);
        fr.setPower(+y - x);
//        bl.setPower(-y + x);
//        br.setPower(y + x);

        fl.setTargetPosition((int) (abs(distanceRequested * sin(toRadians(angle + 45))) * signum(fl.getPower())));
//        br.setTargetPosition((int) (y * signum(br.getPower())));
        fr.setTargetPosition((int) (abs(distanceRequested * cos(toRadians(angle + 45))) * signum(fr.getPower())));
//        bl.setTargetPosition((int) (y * signum(bl.getPower())));

        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fl.setPower(-x - y);
        fr.setPower(+y - x);
        bl.setPower(-y + x);
        br.setPower(+y + x);

        while (fr.isBusy() || fl.isBusy()){
            Thread.sleep(1);
        }

        stop();
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

    /***
     * This is a spiffy little function, spry and saucy; redolent of
     * elderberries and getting spruced, this is the prize pinot noir of
     * the 2017 wine-in-a-box season.
     *
     * @param power what everyone wants
     */
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

