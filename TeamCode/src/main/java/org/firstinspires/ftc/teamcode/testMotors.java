package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.tool.DriveTrain;
import org.firstinspires.ftc.teamcode.tool.Grabber;
import org.firstinspires.ftc.teamcode.tool.Toggle;


//You are on the sam_code branch
//Hi Sam

@TeleOp(name ="test TELEOP")
public class testMotors extends OpMode {
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

        spinServo = hardwareMap.crservo.get("spinServo");

        driveTrain.setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //make spin servo stationary
        spinServo.setPower(0);




    }

    //Loop process:
    @Override
    public void loop(){

        DriveTrain.logTelemetry(telemetry, driveTrain);

        if(gamepad1.a){
            driveTrain.flMotor.setPower(1);
        }else{
            driveTrain.flMotor.setPower(0);
        }

        if(gamepad1.b){
            driveTrain.blMotor.setPower(1);
        }else{
            driveTrain.blMotor.setPower(0);
        }

        if(gamepad1.x){
            driveTrain.frMotor.setPower(1);
        }else{
            driveTrain.frMotor.setPower(0);
        }

        if(gamepad1.y){
            driveTrain.brMotor.setPower(1);
        }else{
            driveTrain.brMotor.setPower(0);
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

        //activate grabber thing
        grabber.ManualToggleGrabber(gamepad2);
        telemetry.addData("Left Servo Position", grabber.leftServoPosition());
        telemetry.addData("Right Servo Position", grabber.rightServoPosition());




        //holds the lift still

        if (gamepad2.a) {
            arm.setPower(.5);
        }
        else if (gamepad2.x) {
            arm.setPower(.4);
        }
        else  {
            arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }



        // spins spin thing
        if (gamepad1.b) {
            spinServo.setPower(1);

        }
        else if (gamepad1.y) {
            spinServo.setPower(0);
        }
    }
}