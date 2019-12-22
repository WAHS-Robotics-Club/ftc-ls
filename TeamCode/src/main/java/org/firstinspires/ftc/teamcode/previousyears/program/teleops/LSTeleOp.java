package org.firstinspires.ftc.teamcode.previousyears.program.teleops;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.previousyears.LSRobot;

@TeleOp(name = "LSTeleOp")
@Disabled
public class LSTeleOp extends OpMode {
    LSRobot lsBot = new LSRobot();

    @Override
    public void init() {
        lsBot.init(hardwareMap);
        lsBot.SetRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop() {

        double x = (gamepad1.left_stick_x)/1.5;
        double y = (gamepad1.left_stick_y)/1.5;
        double turnPower = (gamepad1.right_stick_x)/1.5;

        double leftTrigger = gamepad1.left_trigger;
        double rightTrigger = gamepad1.right_trigger;
        boolean leftBumper = gamepad1.left_bumper;
        boolean rightBumper = gamepad1.right_bumper;

        boolean dpadleft = gamepad1.dpad_left;
        boolean dpadright = gamepad1.dpad_right;
        boolean dpadup = gamepad1.dpad_up;
        boolean dpaddown = gamepad1.dpad_down;

        boolean xbutton = gamepad1.x;
        boolean ybutton = gamepad1.y;
        boolean b = gamepad1.b;
        boolean a = gamepad1.a;

        lsBot.Move(x, y, turnPower, telemetry);

//        lsBot.MoveCollector(dpadleft, dpadright, leftTrigger, rightTrigger, telemetry);

        lsBot.Collect(ybutton, b);

        lsBot.MoveShooter(leftBumper, rightBumper);

        lsBot.Shoot(xbutton, a);

        lsBot.LiftRobot(dpadup, dpaddown, telemetry);
    }
}