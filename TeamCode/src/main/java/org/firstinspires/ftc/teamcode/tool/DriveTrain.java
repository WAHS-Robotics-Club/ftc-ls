package org.firstinspires.ftc.teamcode.tool;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Tool.Toggle;

public class DriveTrain{


    DcMotor FrontLeftMotor;
    DcMotor FrontRightMotor;
    DcMotor BackLeftMotor;
    DcMotor BackRightMotor;
    Toggle toggleSpeed;
    int targetHeading;
    //Sets the acceptable margin of error for the heading (in degrees)
    final double HEADING_ACCURACY = 2;

    public static DriveTrain initDriveTrain(HardwareMap hardwareMap) {
        //Hardware mapping the motors:
        DriveTrain driveTrain = new DriveTrain();

        driveTrain.FrontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        driveTrain.BackLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        driveTrain.FrontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        driveTrain.BackRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        driveTrain.toggleSpeed = new Toggle();

        return driveTrain;
    }

    public void manualDrive(Gamepad gamepad1){
        if(!toggleSpeed.isToggled()) {
            FrontLeftMotor.setPower(gamepad1.left_stick_x + -gamepad1.left_stick_y + gamepad1.right_stick_x);
            BackLeftMotor.setPower(-gamepad1.left_stick_x + -gamepad1.left_stick_y + gamepad1.right_stick_x);
            FrontRightMotor.setPower(gamepad1.left_stick_x + gamepad1.left_stick_y + gamepad1.right_stick_x);
            BackRightMotor.setPower(-gamepad1.left_stick_x + gamepad1.left_stick_y + gamepad1.right_stick_x);
        }else{
            FrontLeftMotor.setPower((gamepad1.left_stick_x + -gamepad1.left_stick_y + gamepad1.right_stick_x)/4);
            BackLeftMotor.setPower((-gamepad1.left_stick_x + -gamepad1.left_stick_y + gamepad1.right_stick_x)/4);
            FrontRightMotor.setPower((gamepad1.left_stick_x + gamepad1.left_stick_y + gamepad1.right_stick_x)/4);
            BackRightMotor.setPower((-gamepad1.left_stick_x + gamepad1.left_stick_y + gamepad1.right_stick_x)/4);
        }
    }

    public void checkToggleSpeed(Gamepad gamepad1){
        if(gamepad1.left_bumper){
            toggleSpeed.toggle();
        }
    }

    public static void logTelemetry(Telemetry telemetry, DriveTrain driveTrain) {
        //telemetry.addData("Heading", driveTrain.getHeading() + " degrees");
        //1120 ticks in a rotation
        telemetry.addData("FL Power", driveTrain.FrontLeftMotor.getPower());
        telemetry.addData("BL Power", driveTrain.BackLeftMotor.getPower());
        telemetry.addData("FR Power", driveTrain.FrontRightMotor.getPower());
        telemetry.addData("BR Power", driveTrain.BackRightMotor.getPower());
    }

    private void goForwardsTo(double inches) throws InterruptedException{
        resetEncoders();
        Thread.sleep(1);
        int targetPosition;
        double rotations;

        rotations = inches / (4*Math.PI);
        targetPosition = (int)(rotations * 1120);

        FrontLeftMotor.setTargetPosition(targetPosition);
        BackLeftMotor.setTargetPosition(targetPosition);
        FrontRightMotor.setTargetPosition(-targetPosition);
        BackRightMotor.setTargetPosition(-targetPosition);

        setRunMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void setRunMode(DcMotor.RunMode runMode){
        FrontLeftMotor.setMode(runMode);
        BackLeftMotor.setMode(runMode);
        FrontRightMotor.setMode(runMode);
        BackRightMotor.setMode(runMode);
    }

    public void setBasePower(double power){
        FrontLeftMotor.setPower(power);
        BackLeftMotor.setPower(power);
        FrontRightMotor.setPower(power);
        BackRightMotor.setPower(power);
    }

    public void resetEncoders(){
        FrontLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackLeftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackRightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public boolean isBusy(){
        if(FrontLeftMotor.isBusy() && BackLeftMotor.isBusy() && FrontRightMotor.isBusy() && BackRightMotor.isBusy()){
            return true;
        }else{
            return false;
        }
    }

    public boolean isCorrectHeading(int currentHeading){
        if(targetHeading < currentHeading + HEADING_ACCURACY && targetHeading > currentHeading - HEADING_ACCURACY){
            return true;
        }else{
            return false;
        }
    }

    private void turnRobotToHeading(int currentHeading){
        if(currentHeading > 145 || currentHeading < -145){
            if(currentHeading < 0){
                currentHeading += 360;
            }
        }

        double modifier, startingPower;
        modifier = ((Math.sqrt(Math.abs(targetHeading - currentHeading)))/2);
        startingPower = 0.1;

        if(targetHeading < currentHeading - HEADING_ACCURACY){
            FrontLeftMotor.setPower(startingPower * modifier);
            BackLeftMotor.setPower(startingPower * modifier);
            FrontRightMotor.setPower(startingPower * modifier);
            BackRightMotor.setPower(startingPower * modifier);
        }else if(targetHeading > currentHeading + HEADING_ACCURACY){
            FrontLeftMotor.setPower(-startingPower * modifier);
            BackLeftMotor.setPower(-startingPower * modifier);
            FrontRightMotor.setPower(-startingPower * modifier);
            BackRightMotor.setPower(-startingPower * modifier);
        }else{
            FrontLeftMotor.setPower(0);
            BackLeftMotor.setPower(0);
            FrontRightMotor.setPower(0);
            BackRightMotor.setPower(0);
        }

    }

    public void moveForwardsBy(Telemetry telemetry, double inches) throws InterruptedException{
        //Going Forwards
        int i = 0;
        setBasePower(0.8);
        goForwardsTo(inches);
        Thread.sleep(10);
        while(isBusy() && i < 100){
            telemetry.update();
            i++;
            Thread.sleep(1);
        }
    }

    public void turnToHeading(BananaFruit gyro, Telemetry telemetry, int inputTargetHeading) throws InterruptedException{
        //Turning
        targetHeading = inputTargetHeading;
        setRunMode(DcMotor.RunMode.RUN_USING_ENCODER);
        while(!isCorrectHeading(gyro.getHeading())){
            telemetry.update();
            turnRobotToHeading(gyro.getHeading());
            Thread.sleep(1);
        }
    }

}