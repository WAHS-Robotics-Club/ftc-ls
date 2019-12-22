package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.LSBotBoi;

public class Autonomous extends LinearOpMode {

    LSBotBoi lsBot;

    @Override
    public void runOpMode() throws InterruptedException {

        lsBot.initialize(hardwareMap);

        waitForStart();



    }
}
