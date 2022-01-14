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

    private Toggle toggleGrabber;

    public static Grabber initGrabber(HardwareMap hardwareMap){
        //Creates and hardware maps the grabber element
        Grabber grabber = new Grabber();

        grabber.leftServo = hardwareMap.servo.get("leftServo");
        grabber.rightServo = hardwareMap.servo.get("rightServo");

        grabber.linActuator = hardwareMap.dcMotor.get("arm");
        grabber.toggleGrabber = new Toggle();
        grabber.linActuator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        grabber.linActuator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        return grabber;
    }

    public void checkToggleGrabber(){
        if(toggleGrabber.isToggled()){
            rightServo.setPosition(0.95);
            leftServo.setPosition(0.05);
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

    public void ManualToggleGrabber(Gamepad gamepad1){
        if(gamepad1.right_bumper) {
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

    public void ManuallinActuator(Gamepad gamepad) {
        //Moves the arm up and down
            if (gamepad.right_trigger >= 0.1 && gamepad.left_trigger >= 0.1) {
                SpoolMotorControl(0, gamepad);
            } else if (gamepad.right_trigger >= 0.1) {
                SpoolMotorControl(gamepad.right_trigger, gamepad);
            } else if (gamepad.left_trigger >= 0.1) {
                SpoolMotorControl(-gamepad.left_trigger, gamepad);
            } else {
                SpoolMotorControl(0, gamepad);
            }
    }

    private void SpoolMotorControl(float Power, Gamepad gamepad){
        if (linActuator.getCurrentPosition() < 18000 && linActuator.getCurrentPosition() > -10 || gamepad.dpad_right) {
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
            for(int i = 0; i < 100; i++){/*slow down*/}
            linActuator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }


}