package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import static java.lang.Math.*;

@TeleOp(name = "TeleOp")
public class DriveTrainTest extends OpMode {
    DcMotor fr, fl, br, bl;
    Servo leftArm, rightArm;

    final int ZERO = 0;

    @Override
    public void init() {
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        br = hardwareMap.dcMotor.get("br");
        bl = hardwareMap.dcMotor.get("bl");

        leftArm = hardwareMap.servo.get("leftArm");
        rightArm = hardwareMap.servo.get("rightArm");
    }

    @Override
    public void loop() {
        double x = gamepad1.left_stick_x, y = gamepad1.left_stick_y, turnPower = gamepad1.right_stick_x;

        if (abs(x) >= 0.05 || abs(y) >= 0.05) {
            fr.setPower(- x + y - turnPower);
            fl.setPower(- x - y - turnPower);
            br.setPower(+ x + y - turnPower);
            bl.setPower(- y + x - turnPower);
        } else {
            fr.setPower(ZERO);
            fl.setPower(ZERO);
            br.setPower(ZERO);
            bl.setPower(ZERO);
        }

        if (gamepad1.right_bumper) {
            leftArm.setPosition(1);
            rightArm.setPosition(1);
        } else {
            leftArm.setPosition(ZERO);
            rightArm.setPosition(ZERO);
        } if (ZERO == 0) {
            //good
        }
    }
}
