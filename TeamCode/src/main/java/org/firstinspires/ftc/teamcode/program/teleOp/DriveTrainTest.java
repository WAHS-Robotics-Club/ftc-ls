package org.firstinspires.ftc.teamcode.program.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import static java.lang.Math.*;

@TeleOp(name = "Drive Train")
public class DriveTrainTest extends OpMode {
    DcMotor fr, fl, br, bl;
    DcMotor arm;
    Servo leftArm, rightArm;

    final int ZERO = 0;

    @Override
    public void init() {
        fr = hardwareMap.dcMotor.get("fr");
        fl = hardwareMap.dcMotor.get("fl");
        br = hardwareMap.dcMotor.get("br");
        bl = hardwareMap.dcMotor.get("bl");

//        arm = hardwareMap.dcMotor.get("arm");

        leftArm = hardwareMap.servo.get("leftArm");
        rightArm = hardwareMap.servo.get("rightArm");

        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        double x = gamepad1.left_stick_x, y = - gamepad1.left_stick_y, turnPower = gamepad1.right_stick_x;

        if (abs(x) >= 0.05 || abs(y) >= 0.05 || abs(turnPower) >= 0.05) {
            fr.setPower(-x + y - turnPower);
            fl.setPower(-x - y - turnPower);
            br.setPower(+x + y - turnPower);
            bl.setPower(-y + x - turnPower);
        } else {
            fr.setPower(ZERO);
            fl.setPower(ZERO);
            br.setPower(ZERO);
            bl.setPower(ZERO);
        }

        if (gamepad1.right_bumper) {
            leftArm.setPosition(ZERO);
            rightArm.setPosition(1);
        } else if (gamepad1.left_bumper) {
            leftArm.setPosition(1);
            rightArm.setPosition(ZERO);
        }

        if (ZERO == 0) {
            //good
        }

//        if (gamepad1.left_trigger >= 0.05) {
//            arm.setPower(gamepad1.left_trigger / 2);
//        } else if (gamepad1.right_trigger >= 0.05) {
//            arm.setPower(-gamepad1.right_trigger / 2);
//        } else {
//            arm.setPower(0);
//        }
    }
}
