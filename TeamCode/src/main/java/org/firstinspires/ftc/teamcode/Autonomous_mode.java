package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Autonomous")
public class Autonomous_mode extends LinearOpMode{


    //Local DcMotor variables:
    DcMotor frontLeftMotor;
    DcMotor backLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backRightMotor;
    DcMotor arm; //arm to lift objects
    Servo leftServoGrabber; //servo grabber on the right
    Servo rightServoGrabber; //servo grabber on the left
    DcMotor linActuator; //linear actuator that raises objects
    SingleDriver.Toggle toggleGrabber;
    CRServo spinServo;

    //prepares servo motors
    public final static double GRABBER_START = 0.45; //starting position of servo
    public final static double GRABBER_MAX = 0.75; //max position of servo


    @Override
    public void runOpMode() throws InterruptedException {


        //Hardwaremap ALL motors:
        //prepares servo motors

        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        linActuator = hardwareMap.dcMotor.get("linActuator");

        leftServoGrabber = hardwareMap.servo.get("leftServoGrabber");
        rightServoGrabber = hardwareMap.servo.get("rightServoGrabber");

        arm= hardwareMap.dcMotor.get("arm");

        spinServo = (CRServo) hardwareMap.dcMotor.get("spinServo");

        //send servos to initial position
        leftServoGrabber.setPosition(GRABBER_START);
        rightServoGrabber.setPosition(GRABBER_START);


        //make spin servo stationary
        spinServo.setPower(0.5);



        waitForStart();

        frontRightMotor.setPower(.5);
        sleep(10000);
        frontRightMotor.setPower(0);
    }


    }

