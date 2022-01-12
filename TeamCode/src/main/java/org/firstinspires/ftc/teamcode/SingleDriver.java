package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.tool.DriveTrain;
import org.firstinspires.ftc.teamcode.tool.Toggle;
import org.firstinspires.ftc.teamcode.tool.Grabber;


//You are on the sam_code branch
//Hi Sam

@TeleOp(name ="Single Driver TeleOp")
public class SingleDriver extends OpMode {
    //Local DcMotor variables:
    DriveTrain driveTrain;
    DcMotor arm; //arm to lift objects
    Grabber grabber;
    DcMotor linActuator; //linear actuator that raises objects
    Toggle toggleGrabber;
    CRServo spinServo;

    
    //Initiation process:
    @Override
    public void init(){
        //Hardwaremap ALL motors:
        driveTrain = DriveTrain.initDriveTrain(hardwareMap);

        linActuator = hardwareMap.dcMotor.get("linActuator");

        grabber = Grabber.initGrabber(hardwareMap);

        arm= hardwareMap.dcMotor.get("arm");

        spinServo = (CRServo) hardwareMap.dcMotor.get("spinServo");



        //make spin servo stationary
        spinServo.setPower(0.5);




    }

    //Loop process:
    @Override
    public void loop(){

        driveTrain.manualDrive(gamepad1);
        driveTrain.checkToggleSpeed(gamepad1);
        DriveTrain.logTelemetry(telemetry, driveTrain);





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

        //activate grabber thing
        grabber.ManualToggleGrabber(gamepad2);
        telemetry.addData("Left Servo Position", grabber.leftServoPosition());
        telemetry.addData("Right Servo Position", grabber.rightServoPosition());




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


        // spins spin thing
        if (gamepad1.x) {
            spinServo.setPower(1);

        }
        else if (gamepad1.y) {
            spinServo.setPower(0.5);
        }
    }
}