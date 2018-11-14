package org.firstinspires.ftc.teamcode.program.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.LSRobot;

@Autonomous(name = "LS Autonomous")
public class LSAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        LSRobot lsBot = new LSRobot();

        lsBot.Move(1, 0, 0, 2);
        lsBot.Move(0, 0, 1, 2);
    }
}