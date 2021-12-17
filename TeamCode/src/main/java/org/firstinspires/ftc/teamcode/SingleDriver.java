package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
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
    Toggle toggleGrabber;

    //prepares servo motors
    public final static double GRABBER_START = 0.45; //starting position of servo
    public final static double GRABBER_MAX = 0.75; //max position of servo
    public final static double GRABBER_MIN = 0.45; //min position of servo
    public final static double GRABBER_SPEED = 0.1; //speed at which grabber grabs that which is to be grabbed

    public void CheckToggleGrabber(){
        if(toggleGrabber.isToggled()){
            rightServoGrabber.setPosition(0.05);
            leftServoGrabber.setPosition(0.95);
        }else{
            rightServoGrabber.setPosition(1);
            leftServoGrabber.setPosition(0);
        }
    }



    public class Toggle {
        private double cooldownTime;
        private boolean toggled;
        private double time;

        public Toggle(double cooldownTime) {
            this.cooldownTime = cooldownTime;
            toggled = false;
            time = System.nanoTime();
        }

        public Toggle() {
            this(0.25);
        }

        public void toggle() {
            if((System.nanoTime() - time) / 1e9 >= cooldownTime) {
                toggled = !toggled;
                time = System.nanoTime();
            }
        }

        public boolean isToggled() {
            return toggled;
        }
    }


    
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
        frontLeftMotor.setPower(.6*(-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x));
        backLeftMotor.setPower(.6*(-gamepad1.left_stick_y + -gamepad1.left_stick_x + gamepad1.right_stick_x));
        frontRightMotor.setPower(.6*(gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x));
        backRightMotor.setPower(.6*(gamepad1.left_stick_y + -gamepad1.left_stick_x + gamepad1.right_stick_x));



        ///toggles grabber

            if(gamepad2.b) {
                toggleGrabber.toggle();
            }

        // lin actuator forward if x is pressed, back if y is pressed, off else
        if (gamepad2.x) {
            linActuator.setPower(1);
        }
        else if (gamepad2.y) {
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


        //holds the lift still

        if (gamepad2.a) {
            arm.setPower(0.3);
        }
        else if ((gamepad2.left_trigger==0 && gamepad2.right_trigger==0) || (gamepad2.left_trigger!=0 && gamepad2.right_trigger!=0)) {
            arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        else if (gamepad2.left_trigger!=0) {
            arm.setPower(0.6*(gamepad1.left_trigger));
        }
        else if (gamepad2.right_trigger!=0) {
            arm.setPower(0.2*(-gamepad1.right_trigger));

        }



    }
}