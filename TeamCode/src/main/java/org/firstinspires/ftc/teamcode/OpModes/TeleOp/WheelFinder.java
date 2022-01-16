package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Objects.DriveTrain;
import org.firstinspires.ftc.teamcode.Objects.Grabber;
import org.firstinspires.ftc.teamcode.Objects.Misc;

@TeleOp(name ="WheelFinder - LS", group = "TeleOp")
public class WheelFinder extends OpMode {
    //Initializing the main objects:
    DriveTrain driveTrain;

    @Override
    public void init(){
        //Hardware mapping the servos:
        driveTrain = DriveTrain.initDriveTrain(hardwareMap, DcMotor.ZeroPowerBehavior.FLOAT);

        driveTrain.resetEncoders();
        driveTrain.setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override public void loop(){
        //Drive Train manual control system
        DriveTrain.logTelemetry(telemetry, driveTrain);
        if(gamepad1.a){
            driveTrain.flMotor.setPower(0.25);
        }else{
            driveTrain.flMotor.setPower(0);
        }
        if(gamepad1.b){
            driveTrain.frMotor.setPower(0.25);
        }else{
            driveTrain.frMotor.setPower(0);
        }
        if(gamepad1.x){
            driveTrain.blMotor.setPower(0.25);
        }else{
            driveTrain.blMotor.setPower(0);
        }
        if(gamepad1.y){
            driveTrain.brMotor.setPower(0.25);
        }else{
            driveTrain.brMotor.setPower(0);
        }

    }



}