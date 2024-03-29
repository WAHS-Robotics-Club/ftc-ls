package org.firstinspires.ftc.teamcode.Objects;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Tool.Toggle;

public class Grabber {
    public Servo leftServo;
    public Servo rightServo;

    public DcMotor linActuator;
    public DcMotor armMotor;

    private Toggle toggleGrabber;

    public static Grabber initGrabber(HardwareMap hardwareMap){
        //Creates and hardware maps the grabber element
        Grabber grabber = new Grabber();

        grabber.leftServo = hardwareMap.servo.get("leftServo");
        grabber.rightServo = hardwareMap.servo.get("rightServo");

        grabber.linActuator = hardwareMap.dcMotor.get("linActuator");
        grabber.linActuator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        grabber.linActuator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        grabber.armMotor = hardwareMap.dcMotor.get("armMotor");
        grabber.armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        grabber.linActuator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        grabber.toggleGrabber = new Toggle();


        return grabber;

    }

public void grab(Gamepad gamepad1) {
        if(gamepad1.a) {
            rightServo.setPosition(0.95);
            leftServo.setPosition(0.05);
        }
        else if (gamepad1.b) {
                rightServo.setPosition(0.05);
                leftServo.setPosition(0.95);

            }
        }


    /*public void checkToggleGrabber(){
        if(toggleGrabber.isToggled()){
            rightServo.setPosition(0.95);
            leftServo.setPosition(0.05);
        }else{
            rightServo.setPosition(0.05);
            leftServo.setPosition(0.95);
        }
    }*/

    public void setHeightTo(Telemetry telemetry, int targetPosition) throws InterruptedException{
        Thread.sleep(1);

        linActuator.setPower(1);
        linActuator.setTargetPosition(targetPosition);
        linActuator.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        int i = 0;
        while(linActuator.isBusy() && i < 500){
            telemetry.update();
            i++;
            Thread.sleep(1);
        }
    }


    /*public void ManualToggleGrabber(Gamepad gamepad1){
        if(gamepad1.a) {
            toggleGrabber.toggle();
        }
        //checkToggleGrabber();
    }

    public void forceToggleGrabber(){
        toggleGrabber.toggle();
    }

    public void toggleGrabberAuto() throws InterruptedException{
        forceToggleGrabber();
        Thread.sleep(50);
        //checkToggleGrabber();
    }

    public void ManualLinActuator(Gamepad gamepad) {
        //Moves the arm up and down
            if (gamepad.right_trigger >= 0.1 && gamepad.left_trigger >= 0.1) {
                LinActuatorControl(0, gamepad);
            } else if (gamepad.right_trigger >= 0.1) {
                LinActuatorControl(gamepad.right_trigger, gamepad);
            } else if (gamepad.left_trigger >= 0.1) {
                LinActuatorControl(-gamepad.left_trigger, gamepad);
            } else {
                LinActuatorControl(0, gamepad);
            }
    }

    private void LinActuatorControl(float Power, Gamepad gamepad){
        if(linActuator.getCurrentPosition() < 18000 && linActuator.getCurrentPosition() > -10 || gamepad.dpad_right) {
            if (Math.abs(Power) >= 0.1) {
                linActuator.setPower(Power);
            } else {
                linActuator.setPower(0);
            }
        }else if(linActuator.getCurrentPosition() > -10){
            linActuator.setPower(-.3);
        }else{
            linActuator.setPower(.3);
        }

        if(gamepad.dpad_up){
            linActuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            for(int i = 0; i < 100; i++){/*slow down*/
            /*linActuator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void ManualArmMotor(Gamepad gamepad){
        if (gamepad.right_trigger >= 0.1 && gamepad.left_trigger >= 0.1) {
            armMotor.setPower(0);
        } else if (gamepad.right_trigger >= 0.1) {
            armMotor.setPower(gamepad.right_trigger);
        } else if (gamepad.left_trigger >= 0.1) {
            armMotor.setPower(-gamepad.right_trigger);
        } else {
            //LinActuatorControl(0, gamepad);
        }
    }*/

    public void ManualLinActuator(Gamepad gamepad) {
        //Moves the arm up and down
        if (gamepad.a) {
            linActuator.setPower(.7);
        }
        else if (gamepad.b) {
            linActuator.setPower(-0.7);
        }
        else {
            linActuator.setPower(0);
        }

    }
    public void ManualArmMotor(Gamepad gamepad){
        if (gamepad.right_trigger >= 0.1 && gamepad.left_trigger >= 0.1) {
            armMotor.setPower(0);
        } else if (gamepad.right_trigger >= 0.1) {
            armMotor.setPower(gamepad.right_trigger);
        } else if (gamepad.left_trigger >= 0.1) {
            armMotor.setPower((-gamepad.left_trigger)*.8);
        } //else {
            //LinActuatorControl(0, gamepad);
        //}
    }

}
