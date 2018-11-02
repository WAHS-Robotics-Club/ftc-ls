package org.firstinspires.ftc.teamcode.program.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.HolonomicDrive;
import org.firstinspires.ftc.teamcode.program.HardwareMapConstants;

@TeleOp(name = "HolonomicTeleOp")

public class HolonomicTeleOp extends OpMode {
    HolonomicDrive Prospero = new HolonomicDrive();

    @Override
    public void init() {
        Prospero.init(hardwareMap);
    }

    public void whereMichelle(){
        telemetry.addLine("bean");
    }

    @Override
    public void loop() {
        double x = (gamepad1.left_stick_x);
        double y = (gamepad1.left_stick_y);
        double turnPower = gamepad1.right_stick_x;

        double lefttrig = gamepad1.left_trigger;
        double righttrig = gamepad1.right_trigger;

        boolean a = gamepad1.a;

        Prospero.MoveAround(x, y , turnPower);

        Prospero.Michelle(lefttrig, righttrig, a);
    }
}
