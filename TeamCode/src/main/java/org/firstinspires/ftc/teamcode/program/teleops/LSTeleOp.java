package org.firstinspires.ftc.teamcode.program.teleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.LSRobot;

@TeleOp(name = "LSTeleOp")

public class LSTeleOp extends OpMode {
    private LSRobot lsBot = new LSRobot();

    @Override
    public void init() {
        lsBot.init(hardwareMap);
    }

    @Override
    public void loop() {
        double x = (gamepad1.left_stick_x);
        double y = (gamepad1.left_stick_y);
        double turnPower = gamepad1.right_stick_x;

        double leftTrigger = gamepad1.left_trigger;
        double rightTrigger = gamepad1.right_trigger;

        boolean leftBumper = gamepad1.left_bumper;
        boolean rightBumper = gamepad1.right_bumper;
        boolean aButton = gamepad1.a;
        boolean bButton = gamepad1.b;
        boolean xButton = gamepad1.x;
        boolean yButton = gamepad1.y;

        boolean dpadup = gamepad1.dpad_up;
        boolean dPadDown = gamepad1.dpad_down;

        lsBot.Move(x, y, turnPower);

        lsBot.MoveCollector(rightBumper, leftBumper);

        lsBot.Collect(yButton);

        lsBot.MoveShooter(rightTrigger, leftTrigger);

        lsBot.Shoot(xButton);
    }
}
