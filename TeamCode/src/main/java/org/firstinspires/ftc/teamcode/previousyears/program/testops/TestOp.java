package org.firstinspires.ftc.teamcode.previousyears.program.testops;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.previousyears.util.HardwareMapConstants2018;

@TeleOp(name = "TestOp")
@Disabled
public class TestOp extends OpMode {
    DcMotor fl, fr, bl, br;

    @Override
    public void init() {
        fl = hardwareMap.dcMotor.get(HardwareMapConstants2018.MOTOR_FRONT_LEFT);
        bl = hardwareMap.dcMotor.get(HardwareMapConstants2018.MOTOR_BACK_LEFT);
        fr = hardwareMap.dcMotor.get(HardwareMapConstants2018.MOTOR_FRONT_RIGHT);
        br = hardwareMap.dcMotor.get(HardwareMapConstants2018.MOTOR_BACK_RIGHT);

        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        double y = (-gamepad1.left_stick_y)/2;
        double x = (gamepad1.left_stick_x)/2;

        double r = (gamepad1.right_stick_x)/2;

        fl.setPower(y + x - r);
        bl.setPower(y - x - r);
        fr.setPower(-y + x - r);
        br.setPower(-y - x - r);
    }
}
