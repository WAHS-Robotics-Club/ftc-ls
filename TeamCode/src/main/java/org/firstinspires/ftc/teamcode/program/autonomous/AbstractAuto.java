package org.firstinspires.ftc.teamcode.program.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.navigation.CameraSide;
import org.firstinspires.ftc.teamcode.navigation.CryptoColumn;
import org.firstinspires.ftc.teamcode.navigation.Navigator;
import org.firstinspires.ftc.teamcode.navigation.PhoneOrientation;

/**
 * Created by Wintermute on 12/2/17.
 */

public class AbstractAuto {
    private boolean isRed;
    private LinearOpMode opMode;
    private TestAutonomous function;
    private Navigator henry = new Navigator(CameraSide.BACK, PhoneOrientation.VOLUME_SIDE_DOWN, 1, false);

    public AbstractAuto(boolean isRed, LinearOpMode opMode){
        this.isRed = isRed;
        this.opMode = opMode;
        function = new TestAutonomous(isRed);
    }

    public void run() throws InterruptedException {
        final int INIT_MOVE = 6;

        function.setUp(opMode.hardwareMap);

        int col = 0;

        henry.init();

        opMode.waitForStart();

        function.clamp();

        Thread.sleep(500);

        function.move(.35, isRed ? 180 : 0, INIT_MOVE, opMode.telemetry);

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

        function.move(.35, isRed ? 180 : 0, (32.9 - INIT_MOVE) + col * FieldMeasurements.columnWidth, opMode.telemetry);

        Thread.sleep(1000);

        function.move(.35, 90, FieldMeasurements.distanceToWallWithCube, opMode.telemetry);
    }

    final double CORRECTION = 28d / 24d * (Math.E / Math.E);

    private void alignWithImage() {
        while(opMode.opModeIsActive()){
            double yangle = henry.getRelativeTargetRotation().y;
            if(henry.canSeeTarget()){
                if(yangle > 3){
                    function.turn(0.15);
                } else if (yangle < -3){
                    function.turn(-0.15);
                } else {
                    function.stop();
                }
            } else {
                function.stop();
            }
        }
    }
}
