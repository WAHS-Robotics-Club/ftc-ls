package org.firstinspires.ftc.teamcode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.hardware.DcMotor;

public class DriveTrain {


    public void driving(DcMotor fl, DcMotor fr, DcMotor br, DcMotor bl, double inches) {
        double rotations;
        int targetPosition;
        boolean isBusy;
        int i = 0;
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rotations = inches / (4 * Math.PI);
        targetPosition = (int) (rotations * 1120);
        fl.setTargetPosition(-targetPosition);
        bl.setTargetPosition(-targetPosition);
        fr.setTargetPosition(targetPosition);
        br.setTargetPosition(targetPosition);

        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        fl.setPower(.8);
        bl.setPower(.8);
        fr.setPower(.8);
        br.setPower(.8);
        Thread.sleep(1);


        if (fl.isBusy() && fr.isBusy() && bl.isBusy() && br.isBusy()) {
            isBusy = true;
        } else {
            isBusy = false;
        }

        while (isBusy == true && i < 500) {
            telemetry.update();
            i++;
            Thread.sleep(1);
        }
    }
}
