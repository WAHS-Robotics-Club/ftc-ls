package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.program.HardwareMapConstants;

@TeleOp(name = "ServoTest")
public class ServoTest extends OpMode {
    Servo claw;

    @Override
    public void init() {
        claw = hardwareMap.servo.get("claw");
        claw.setPosition(.5);
    }

    @Override
    public void loop() {
        if(gamepad1.b){
            claw.setPosition(1.0);
        } else {
            claw.setPosition(0.5);
        }
    }
}
