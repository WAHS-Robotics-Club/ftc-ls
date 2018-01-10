package org.firstinspires.ftc.teamcode.program.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.navigation.CameraSide;
import org.firstinspires.ftc.teamcode.navigation.CryptoColumn;
import org.firstinspires.ftc.teamcode.navigation.Navigator;
import org.firstinspires.ftc.teamcode.navigation.PhoneOrientation;
import org.firstinspires.ftc.teamcode.program.HardwareMapConstants;

/**
 * Created by Wintermute on 12/2/17.
 */

public class AbstractAuto {
    private DcMotor arm;
    private Servo jewelWhacker;

    private Servo leftClaw, rightClaw;

    private ColorSensor cs;

    private boolean isRed;
    private LinearOpMode opMode;
    private Navigator henry = new Navigator(CameraSide.BACK, PhoneOrientation.VOLUME_SIDE_DOWN, 1, false);

    MecanumDrive alexander = new MecanumDrive();

    public AbstractAuto(boolean isRed, LinearOpMode opMode){
        this.isRed = isRed;
        this.opMode = opMode;
    }

    public void run() throws InterruptedException {
        final int INIT_MOVE = 6;

        int col = 0;

        boolean jewelRed;

        henry.init();

        alexander.init(opMode.hardwareMap);

        arm = opMode.hardwareMap.dcMotor.get(HardwareMapConstants.MOTOR_ARM);

        jewelWhacker = opMode.hardwareMap.servo.get(HardwareMapConstants.COLOR_SERVO);

        cs = opMode.hardwareMap.colorSensor.get(HardwareMapConstants.COLOR_SENSOR);

        leftClaw = opMode.hardwareMap.servo.get(HardwareMapConstants.LEFT_CLAW);
        rightClaw = opMode.hardwareMap.servo.get(HardwareMapConstants.RIGHT_CLAW);


        opMode.waitForStart();

        Thread.sleep(500);

        jewelWhacker.setPosition(0);

        Thread.sleep(500);

        if(cs.red() > Math.E){
            jewelRed = true;
        } else {
            jewelRed = false;
        }

        double whackPower = isRed ? -0.2 : 0.2;

        if(jewelRed){
            alexander.turn(whackPower);
        } else {
            alexander.turn(-whackPower);
        }

        Thread.sleep(500);

        if(jewelRed){
            alexander.turn(-whackPower);
        } else {
            alexander.turn(whackPower);
        }

        Thread.sleep(500);

        alexander.stop();

        Thread.sleep(250);

        alexander.encoderMove(INIT_MOVE, .35, isRed ? 180 : 0);

        double time = System.nanoTime() / 1e9d;

        while(!henry.canSeeTarget() && ((System.nanoTime() / 1e9d) - time < 4)) {
            Thread.sleep(1);
        }

        CryptoColumn column = henry.decodeTarget();
        switch(column){
            case LEFT:
                col = isRed ? 2 : 0;
                break;
            case CENTER:
                col = 1;
                break;
            case RIGHT:
                col = isRed ? 0 : 2;
                break;
            case UNKNOWN:
                col = 0;
                break;
        }

        Thread.sleep(1000);

        alexander.encoderMove((32.9 - INIT_MOVE) + col * FieldMeasurements.columnWidth, 0.35, isRed ? 0 : 180);

        Thread.sleep(1000);

        alexander.encoderMove(FieldMeasurements.distanceToWallWithCube, 0.35, 90);
    }

    final double CORRECTION = 28d / 24d;

    private void alignWithImage() {
        while(opMode.opModeIsActive()){
            double yangle = henry.getRelativeTargetRotation().y;
            if(henry.canSeeTarget()){
                if(yangle > 3){
                    alexander.moveAndTurn(0, 0, 0.15);
                } else if (yangle < -3){
                    alexander.moveAndTurn(0, 0, -0.15);
                } else {
                    alexander.stop();
                }
            } else {
                alexander.stop();
            }
        }
    }
}
