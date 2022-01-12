package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.PwmControl;

import org.firstinspires.ftc.teamcode.tool.BananaFruit;
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


    @Override
    public void runOpMode() throws InterruptedException {


        //Hardwaremap ALL motors:
        driveTrain = DriveTrain.initDriveTrain(hardwareMap);
        linActuator = hardwareMap.dcMotor.get("linActuator");
        grabber = Grabber.initGrabber(hardwareMap);
        arm= hardwareMap.dcMotor.get("arm");
        spinServo = (CRServo) hardwareMap.dcMotor.get("spinServo");

        telemetry.addData("IsBusy", driveTrain.isBusy());
        driveTrain.logTelemetry(telemetry, driveTrain);
        telemetry.update();
        driveTrain.resetEncoders();
        BananaFruit gyro = new BananaFruit();
        gyro.runBananaFruit(hardwareMap, telemetry);
        telemetry.update();




        //make spin servo stationary
        spinServo.setPower(0.5);



        waitForStart();



        sleep(300);
        driveTrain.moveForwardsBy(telemetry, -20);
        driveTrain.turnToHeading(gyro, telemetry,90);


        sleep(300);
        driveTrain.moveForwardsBy(telemetry, -20);
        driveTrain.turnToHeading(gyro, telemetry,175);


        sleep(300);
        driveTrain.moveForwardsBy(telemetry, -20);
        driveTrain.turnToHeading(gyro, telemetry,-90);


        sleep(300);
        driveTrain.moveForwardsBy(telemetry, -20);
        driveTrain.turnToHeading(gyro, telemetry, 5);
    }


    }

