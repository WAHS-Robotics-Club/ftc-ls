package org.firstinspires.ftc.teamcode.program.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.LSRobot;

@TeleOp(name = "LS Autonomous")
public class LSAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        LSRobot Miranda = new LSRobot();

        Miranda.AutoBlindMove(1, 0, 0, 2);
        Miranda.AutoBlindMove(0, 0, 1, 2);
    }
}