package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

//You are on the sam_code branch
//Hi Sam

@TeleOp(name ="Single Driver TeleOp")
public class SingleDriver extends OpMode {
    //Local DcMotor variables:
    DcMotor frontLeftMotor;
    DcMotor backLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backRightMotor;
    DcMotor arm; //arm to lift objects
    Servo leftServoGrabber; //servo grabber on the right
    Servo rightServoGrabber; //servo grabber on the left
    DcMotor linActuator; //linear actuator that raises objects

    //prepares servo motors
    public final static double GRABBER_START = 0.45; //starting position of servo
    public final static double GRABBER_MAX = 0.75; //max position of servo
    public final static double GRABBER_MIN = 0.45; //min position of servo
    public final static double GRABBER_SPEED = 0.1; //speed at which grabber grabs that which is to be grabbed

    //Initiation process:
    @Override
    public void init(){
        //Hardwaremap ALL motors:
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        linActuator = hardwareMap.dcMotor.get("linActuator");
        leftServoGrabber = hardwareMap.servo.get("leftServoGrabber");
        rightServoGrabber = hardwareMap.servo.get("rightServoGrabber");
        arm= hardwareMap.dcMotor.get("arm");

        //send servos to initial position
        leftServoGrabber.setPosition(GRABBER_START);
        rightServoGrabber.setPosition(GRABBER_START);





    }

    //Loop process:
    @Override
    public void loop(){
        frontLeftMotor.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
        backLeftMotor.setPower(-gamepad1.left_stick_y + -gamepad1.left_stick_x + gamepad1.right_stick_x);
        frontRightMotor.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
        backRightMotor.setPower(gamepad1.left_stick_y + -gamepad1.left_stick_x + gamepad1.right_stick_x);



        // lin actuator forward if x is pressed, back if y is pressed, off else
        if (gamepad1.x) {
            linActuator.setPower(1);
        }
        else if (gamepad1.y) {
            linActuator.setPower(-1);
        }
        else {
            linActuator.setPower(0);

        }

        //turn on servo grabber NOT DONE yet
        if (gamepad1.b) {
            leftServoGrabber.setPosition(GRABBER_MAX);
            rightServoGrabber.setPosition(GRABBER_MAX);
        }
        else if (gamepad1.a) {
            leftServoGrabber.setPosition(0.45);
            rightServoGrabber.setPosition(0.45);
        }

        //raises the lift to pick up objects if triggers pushed. Still otherwise
        if ((gamepad1.left_trigger==0 && gamepad1.right_trigger==0))
                || (gamepad2.left_trigger!=0 && gamepad2.right_trigger!=0)) {
            arm.setPower(0);
        }
        else if (gamepad1.left_trigger!=0) {
            arm.setPower(gamepad2.left_trigger);
        }
        else if (gamepad1.right_trigger!=0) {
            arm.setPower(-gamepad2.right_trigger);

        }



    }
}