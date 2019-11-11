package org.firstinspires.ftc.teamcode.previousyears.program.testops;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.previousyears.LSRobot;

@Autonomous(name = "hanging test")

public class HangingTest extends OpMode {
    CRServo ml, mr;

    LSRobot lsBot = new LSRobot();

    @Override
    public void init() {
        ml = hardwareMap.crservo.get("michelle-left");
        mr = hardwareMap.crservo.get("michelle-right");
    }

    @Override
    public void loop() {
        if(Math.abs(gamepad1.left_stick_x) > 0.05){
            ml.setPower(gamepad1.left_stick_x);
            mr.setPower(-gamepad1.left_stick_x);
        } else {
            ml.setPower(0);
            mr.setPower(0);
        }
    }
}
