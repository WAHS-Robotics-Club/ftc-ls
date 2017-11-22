package org.firstinspires.ftc.teamcode.program.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous (name = "Maui Just Messin' Around")
public class MauiJustMessinAround extends LinearOpMode {
    TestAutonomous function = new TestAutonomous();

    @Override
    public void runOpMode() throws InterruptedException {
        function.setUp(hardwareMap);

        waitForStart();

        function.move(0.25, 0, 24);

        function.turn(.5, Math.PI * 2);
    }
}
