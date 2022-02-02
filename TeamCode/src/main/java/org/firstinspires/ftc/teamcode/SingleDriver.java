package org.firstinspires.ftc.teamcode;

import com.google.gson.internal.bind.util.ISO8601Utils;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

//You are on the ryan_code branch


@TeleOp(name ="Single Driver TeleOp")
public class SingleDriver extends OpMode {
    //Local DcMotor variables:
    DcMotor frontL;
    DcMotor frontR;
    DcMotor backL;
    DcMotor backR;
    DcMotor spool;
    DcMotor spinner;
    DcMotor door;




    //Initiation process:
    @Override
    public void init(){
        //Hardwaremap ALL motors:
    frontL = hardwareMap.dcMotor.get("frontLeftMotor");
    frontR = hardwareMap.dcMotor.get("frontRightMotor");
    backL = hardwareMap.dcMotor.get("backLeftMotor");
    backR = hardwareMap.dcMotor.get("backRightMotor");
    spool = hardwareMap.dcMotor.get("spool");
    spinner = hardwareMap.dcMotor.get("spinner");
    door = hardwareMap.dcMotor.get("door");

    }

    //Loop process:
    @Override
    public void loop(){

    }
}