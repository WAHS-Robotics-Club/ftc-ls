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


        //Initiation process:
        @Override
        public void init () {
            //HardwareMap ALL motors:
            frontL = hardwareMap.dcMotor.get("frontLeftMotor");
            frontR = hardwareMap.dcMotor.get("frontRightMotor");
            backL = hardwareMap.dcMotor.get("backLeftMotor");
            backR = hardwareMap.dcMotor.get("backRightMotor");
            spool = hardwareMap.dcMotor.get("spoolMotor");
            spinner = hardwareMap.dcMotor.get("carouselSpinner");
            door = hardwareMap.dcMotor.get("grab");
        }

        //Loop process:
        @Override
        public void loop () {

        if(gamepad1.right_bumper){
            x++;
        }
        if (x%2 != 0) {
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
            ifElseAnalog(gamepad2.right_trigger, gamepad2.left_trigger, spool);
            ifElse(gamepad2.right_bumper, gamepad2.left_bumper, door, 0.2);
            ifElse(gamepad1.a, gamepad1.b, spinner, 0.6);
        }
    }
