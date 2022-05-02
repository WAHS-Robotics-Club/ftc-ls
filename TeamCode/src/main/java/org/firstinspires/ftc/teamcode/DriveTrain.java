package org.firstinspires.ftc.teamcode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveTrain {
    public DcMotor frontLeft, frontRight, backLeft, backRight;

    public DriveTrain(DcMotor fl, DcMotor fr, DcMotor br, DcMotor bl) {
        frontLeft = fl;
        frontRight = fr;
        backLeft = bl;
        backRight = br;
    }


    public void driving(double inches2, double power, Telemetry telemetry) throws InterruptedException {
        double rotations;
        double inches1 = inches2;
        int targetPosition;
        boolean isBusy;
        int i = 0;
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rotations = (inches1/(4.0*Math.PI));
        targetPosition = (int)(rotations*1120);
        frontLeft.setTargetPosition(-targetPosition);
        backLeft.setTargetPosition(-targetPosition);
        frontRight.setTargetPosition(targetPosition);
        backRight.setTargetPosition(targetPosition);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(power);
        backLeft.setPower(power);
        frontRight.setPower(power);
        backRight.setPower(power);
        Thread.sleep(1);


        if (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
            isBusy = true;
        } else {
            isBusy = false;
        }

        while((frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) && i < 500) {

            telemetry.update();
            i++;
            Thread.sleep(1);
        }
    }

    public void turning(int degrees, Telemetry telemetry, BananaFruit gyro) throws InterruptedException {


        int targetHeading = degrees;
        boolean isCorrectHeading = false;
        int currentHeading;
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        while (!isCorrectHeading) {
            telemetry.update();
            currentHeading = gyro.getHeading();

            if (targetHeading < gyro.getHeading() + 1.25 && targetHeading > gyro.getHeading() - 1.25) {
                isCorrectHeading = true;
            } else {
                isCorrectHeading = false;
            }

            if (currentHeading > 145 || currentHeading < -145) {
                if (currentHeading < 0) {
                    currentHeading += 360;
                }
            }

            double modifier, basePower;
            modifier = ((Math.sqrt(Math.abs(targetHeading - currentHeading)))/2);
            basePower = 0.1;

            if (targetHeading < currentHeading - 1.25) {
                frontLeft.setPower(basePower * modifier);
                frontRight.setPower(basePower * modifier);
                backLeft.setPower(basePower * modifier);
                backRight.setPower(basePower * modifier);
            } else if (targetHeading > currentHeading + 1.25) {
                frontLeft.setPower(-basePower * modifier);
                frontRight.setPower(-basePower * modifier);
                backLeft.setPower(-basePower * modifier);
                backRight.setPower(-basePower * modifier);
            }
            else {
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }
            Thread.sleep(1);
        }
    }
}
