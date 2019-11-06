package org.firstinspires.ftc.teamcode.hardwareconstants;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class JustAClass {
    DcMotor swing;

    public void bruh(HardwareMap hardwareMap) {
        swing = hardwareMap.dcMotor.get("swing");


    }
    public void nice(DcMotorImpl power, Gamepad gamepad1) throws InterruptedException{
        while (gamepad1.a = true) {
            swing.setPower(1);
            Thread.sleep(2000);
            swing.setPower(-1);
            Thread.sleep(2000);
        }
    }

}
