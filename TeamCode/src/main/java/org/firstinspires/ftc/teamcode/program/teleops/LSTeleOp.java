package org.firstinspires.ftc.teamcode.program.teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.LSRobot;

@TeleOp(name = "LSTeleOp")

public class LSTeleOp extends OpMode {
    LSRobot lsBot = new LSRobot();

    @Override
    public void init() {
        lsBot.init(hardwareMap);
    }

    @Override
    public void loop() {
        double x = (gamepad1.left_stick_x)/3;
        double y = (gamepad1.left_stick_y)/3;
        double turnPower = (gamepad1.right_stick_x)/3;

        double leftTrigger = gamepad1.left_trigger;
        double rightTrigger = gamepad1.right_trigger;
        boolean leftBumper = gamepad1.left_bumper;
        boolean rightBumper = gamepad1.right_bumper;

        boolean dpadleft = gamepad1.dpad_left;
        boolean dpadright = gamepad1.dpad_right;

        boolean xButton = gamepad1.x;
        boolean yButton = gamepad1.y;

        lsBot.Move(x, y, turnPower);

        lsBot.MoveCollector(leftBumper, rightBumper, dpadleft, dpadright);

        lsBot.Collect(yButton);

        lsBot.MoveShooter(leftTrigger, rightTrigger);

        lsBot.Shoot(xButton);
    }
}
