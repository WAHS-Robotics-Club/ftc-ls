package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.PwmControl;

import org.firstinspires.ftc.teamcode.tool.DriveTrain;
import org.firstinspires.ftc.teamcode.tool.Toggle;
import org.firstinspires.ftc.teamcode.tool.Grabber;


@Autonomous(name="Autonomous")
public class Autonomous_mode extends LinearOpMode{


    //Local DcMotor variables:
    DriveTrain driveTrain;
    DcMotor arm; //arm to lift objects
    Grabber grabber;
    DcMotor linActuator; //linear actuator that raises objects
    Toggle toggleGrabber;
    CRServo spinServo;



    //prepares servo motors
    public final static double GRABBER_START = 0.45; //starting position of servo
    public final static double GRABBER_MAX = 0.75; //max position of servo


    @Override
    public void runOpMode() throws InterruptedException {


        //Hardwaremap ALL motors:
        driveTrain = DriveTrain.initDriveTrain(hardwareMap);
        linActuator = hardwareMap.dcMotor.get("linActuator");
        grabber = Grabber.initGrabber(hardwareMap);
        arm= hardwareMap.dcMotor.get("arm");
        spinServo = (CRServo) hardwareMap.dcMotor.get("spinServo");




        //make spin servo stationary
        spinServo.setPower(0.5);



        waitForStart();


    }


    }

