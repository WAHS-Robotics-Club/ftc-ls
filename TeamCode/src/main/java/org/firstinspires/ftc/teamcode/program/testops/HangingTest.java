package org.firstinspires.ftc.teamcode.program.testops;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.LSRobot;

@Autonomous(name = "hanging test")

public class HangingTest extends OpMode {
    DcMotor shooterArm;

    LSRobot lsBot = new LSRobot();

    @Override
    public void init() {
        lsBot.init(hardwareMap);

    }

    @Override
    public void loop() {
        while(shooterArm.isBusy()){
            shooterArm.setPower(1);
        }

        telemetry.addData("shooter arm is at", shooterArm.getCurrentPosition());
        telemetry.update();
    }
}
