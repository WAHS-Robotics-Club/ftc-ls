package org.firstinspires.ftc.teamcode.HardwareConstants;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.sql.Time;

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
