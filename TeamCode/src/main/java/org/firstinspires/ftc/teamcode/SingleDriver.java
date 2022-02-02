package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

//You are on the darius_code branch

@TeleOp(name ="Single Driver TeleOp")
public class SingleDriver extends OpMode {
    //Local DcMotor variables:
    DcMotor fl;
    DcMotor fr;
    DcMotor bl;
    DcMotor br;
    DcMotor spool;
    DcMotor carousel;
    DcMotor door;

    //Initiation process:
    @Override
    public void init(){
        //Hardwaremap ALL motors:
        fl = hardwareMap.dcMotor.get("frontLeftMotor");
        fr = hardwareMap.dcMotor.get("frontRightMotor");
        bl = hardwareMap.dcMotor.get("backLeftMotor");
        br = hardwareMap.dcMotor.get("backRightMotor");
        spool = hardwareMap.dcMotor.get("spool");
        carousel = hardwareMap.dcMotor.get("carousel");
        door = hardwareMap.dcMotor.get("door");
    }

    //Loop process:
    @Override
    public void loop(){

    }
}