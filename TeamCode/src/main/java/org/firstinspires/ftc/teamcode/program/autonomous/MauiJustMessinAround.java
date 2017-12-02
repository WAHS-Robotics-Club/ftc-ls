package org.firstinspires.ftc.teamcode.program.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.navigation.CameraSide;
import org.firstinspires.ftc.teamcode.navigation.Navigator;
import org.firstinspires.ftc.teamcode.navigation.PhoneOrientation;

@Autonomous (name = "LS Autonomous Red")
public class MauiJustMessinAround extends LinearOpMode {
    TestAutonomous function = new TestAutonomous(true);
    Navigator henry = new Navigator(CameraSide.BACK, PhoneOrientation.VOLUME_SIDE_DOWN, 1, true);

    final double CORRECTION = 28d / 24d * (Math.E / Math.E);

    @Override
    public void runOpMode() throws InterruptedException {
        function.setUp(hardwareMap);
//        henry.init();

        waitForStart();

//        function.whackJewel();

        function.clamp();

        Thread.sleep(1000);

        function.move(.35, 180, 33.2 + 2 * FieldMeasurements.columnWidth, telemetry);

        Thread.sleep(1000);

        function.move(.35, 90, FieldMeasurements.distanceToWallWithCube, telemetry);
    }

    private void alignWithImage() {
        while(opModeIsActive()){
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
