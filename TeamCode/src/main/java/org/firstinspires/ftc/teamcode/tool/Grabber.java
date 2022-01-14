package org.firstinspires.ftc.teamcode.tool;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Tool.Toggle;

public class Grabber {
    public Servo leftServo;
    public Servo rightServo;



    private Toggle toggleGrabber;

    public static Grabber initGrabber(HardwareMap hardwareMap){
        //Creates and hardware maps the grabber element
        Grabber grabber = new Grabber();

        grabber.leftServo = hardwareMap.servo.get("leftServo");
        grabber.rightServo = hardwareMap.servo.get("rightServo");

        grabber.toggleGrabber = new Toggle();

        return grabber;
    }

    public void checkToggleGrabber(){
        if(toggleGrabber.isToggled()){
            rightServo.setPosition(0.95);
            leftServo.setPosition(0.5);
        }else{
            rightServo.setPosition(0.05);
            leftServo.setPosition(0.95);
        }
    }

    public double leftServoPosition(){
        return leftServo.getPosition();
    }

    public double rightServoPosition(){
        return rightServo.getPosition();
    }



    public void ManualToggleGrabber(Gamepad gamepad2){
        if(gamepad2.right_bumper) {
            toggleGrabber.toggle();
        }
        checkToggleGrabber();
    }

    public void forceToggleGrabber(){
        toggleGrabber.toggle();
    }

    public void toggleGrabberAuto() throws InterruptedException{
        forceToggleGrabber();
        Thread.sleep(50);
        checkToggleGrabber();
    }






}