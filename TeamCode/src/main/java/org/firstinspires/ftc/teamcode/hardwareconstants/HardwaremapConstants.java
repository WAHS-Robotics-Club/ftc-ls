package org.firstinspires.ftc.teamcode.hardwareconstants;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HardwaremapConstants{
    DcMotor frontLeft, frontRight, backLeft, backRight;

    public void wheels(HardwareMap hardwareMap){
        frontLeft = hardwareMap.dcMotor.get("FrontLeft");
        frontRight = hardwareMap.dcMotor.get("FrontRight");
        backLeft = hardwareMap.dcMotor.get("BackLeft");
        backRight = hardwareMap.dcMotor.get("BackRight");
    }

}
