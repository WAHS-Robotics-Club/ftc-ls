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
    DcMotor spoolMotor;
    Servo chopstick;

    int x = 0;

    public void ifElse(boolean value1, boolean value2, DcMotor motor, double power) {
        if (value1) {
            motor.setPower(power);
        } else if (value2) {
            motor.setPower(-(power));


        } else {
            motor.setPower(0);
        }

    }

    public void ifElseAnalog(float input1, float input2, DcMotor motor2) {
        if (input1 > 0.001 || input1 < -0.001) {
            motor2.setPower(input1);
        } else if (input2 > 0.001 || input2 < -0.001) {
            motor2.setPower(-input2);
        } else {
            motor2.setPower(0);

        }

    }

    public void ifElseServo(boolean button1, boolean button2, Servo controller, double position1, double position2) {
        if (controller.getPosition() == 0 || controller.getPosition() == 180){
            controller.setPosition(controller.getPosition());
        }
        else if (button1) {
            controller.setPosition(position1);
        } else if (button2) {
            controller.setPosition(position2);


        }


    }
    public void ifElseAnalogServo(float button1, float button2, Servo controller) {
        if (controller.getPosition() == 0 || controller.getPosition() == 180){
            controller.setPosition(controller.getPosition());
        }
        else if (button1 > 0.001 || button1 < -0.001) {
            controller.setPosition(controller.getPosition()+button1);
        } else if (button2 > 0.001 || button2 < -0.001) {
            controller.setPosition(controller.getPosition()+button2);
        }

        }



        //Initiation process:
        @Override
        public void init () {
            //HardwareMap ALL motors:
            frontL = hardwareMap.dcMotor.get("frontLeftMotor");
            frontR = hardwareMap.dcMotor.get("frontRightMotor");
            backL = hardwareMap.dcMotor.get("backLeftMotor");
            backR = hardwareMap.dcMotor.get("backRightMotor");
            spoolMotor = hardwareMap.dcMotor.get("spool");
            chopstick = hardwareMap.servo.get("servo");

        }

        //Loop process:
        @Override
        public void loop () {
        chopstick.setPosition(0.0);
        if (gamepad1.left_bumper){
            frontL.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x)/4);
            backL.setPower((-gamepad1.left_stick_y + -gamepad1.left_stick_x + gamepad1.right_stick_x)/4);
            frontR.setPower((gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x)/4);
            backR.setPower((gamepad1.left_stick_y + -gamepad1.left_stick_x + gamepad1.right_stick_x)/4);
        }
        else{
            frontL.setPower((-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x));
            backL.setPower((-gamepad1.left_stick_y + -gamepad1.left_stick_x + gamepad1.right_stick_x));
            frontR.setPower((gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x));
            backR.setPower((gamepad1.left_stick_y + -gamepad1.left_stick_x + gamepad1.right_stick_x));
        }

        ifElseAnalog(gamepad1.right_trigger, gamepad1.left_trigger, spoolMotor);
        ifElseServo(gamepad1.a, gamepad1.b, chopstick,30,0);



            }
        }
