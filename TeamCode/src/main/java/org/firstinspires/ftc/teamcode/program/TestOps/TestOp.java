package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.program.HardwareMapConstants;

@TeleOp(name = "Circle Test")

public class TestOp extends OpMode {
    DcMotor fl, fr, bl, br;

    @Override
    public void init() {
        fl = hardwareMap.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_LEFT);
        bl = hardwareMap.dcMotor.get(HardwareMapConstants.MOTOR_BACK_LEFT);
        fr = hardwareMap.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_RIGHT);
        br = hardwareMap.dcMotor.get(HardwareMapConstants.MOTOR_BACK_RIGHT);
    }

    @Override
    public void loop() {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;

        double r = gamepad1.right_stick_x;

        fl.setPower(y + x - r);
        bl.setPower(y - x - r);
        fr.setPower(-y - x - r);
        br.setPower(-y + x - r);
    }
}
