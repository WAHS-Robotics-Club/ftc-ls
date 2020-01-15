package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.LSBotBoi;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous (name = "autonomous red")
public class AutonomousRed extends LinearOpMode {

    LSBotBoi lsBot = new LSBotBoi();

    @Override
    public void runOpMode() throws InterruptedException {

        lsBot.initialize(hardwareMap);

        waitForStart();

        lsBot.autoMove(.5, 0, 0, false, telemetry);
        lsBot.stop(telemetry);

        wait(200);

        lsBot.autoMove(0, 90, 0, false, telemetry);
        lsBot.stop(telemetry);

        wait(200);

        lsBot.autoMove(1.25, 0, 0, false, telemetry);
        lsBot.stop(telemetry);

        wait(200);

        lsBot.autoMove(0, -90, 0, false, telemetry);
        lsBot.stop(telemetry);

        wait(200);

        lsBot.autoMove(1.25, 0, 0, false, telemetry);
        lsBot.stop(telemetry);

        wait(200);


        lsBot.setFoundationHook(true, telemetry);

        wait(100);

        lsBot.setFoundationHook(false, telemetry);

        wait(600);


        lsBot.autoMove(-1, 0, 0, true, telemetry);
        lsBot.stop(telemetry);

        wait(600);

        lsBot.setFoundationHook(true, telemetry);

        wait(100);

        lsBot.setFoundationHook(false, telemetry);

        wait(500);

        lsBot.autoMove(-.2, 0, 0, false, telemetry);
        lsBot.stop(telemetry);

        wait(200);

        lsBot.autoMove(0, -90, 0, false, telemetry);
        lsBot.stop(telemetry);

        wait(200);

        lsBot.autoMove(2.2, 0, 0, false, telemetry);
        lsBot.stop(telemetry);

        wait(300);


        lsBot.setFoundationHook(true, telemetry);

        wait(100);

        lsBot.setFoundationHook(false, telemetry);

    }
}
