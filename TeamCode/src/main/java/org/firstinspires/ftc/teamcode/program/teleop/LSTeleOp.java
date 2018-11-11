package org.firstinspires.ftc.teamcode.program.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import org.firstinspires.ftc.teamcode.LSRobot;

@TeleOp(name = "LSTeleOp")

public class LSTeleOp extends OpMode {
    LSRobot Prospero = new LSRobot();

    @Override
    public void init() {
        Prospero.init(hardwareMap);
    }

    @Override
    public void loop() {
        double x = (gamepad1.left_stick_x);
        double y = (gamepad1.left_stick_y);
        double turnPower = gamepad1.right_stick_x;

        double lefttrig = gamepad1.left_trigger;
        double righttrig = gamepad1.right_trigger;

        boolean a = gamepad1.a;
        boolean b = gamepad1.b;
        boolean xbutt = gamepad1.x;

        boolean dpadup = gamepad1.dpad_up;
        boolean dpaddown = gamepad1.dpad_down;

        Prospero.MoveAround(x, y, turnPower);

        Prospero.Micheller(lefttrig, righttrig, a, b, telemetry);

        Prospero.Jordaner(dpadup, dpaddown, xbutt, telemetry);
    }
}
