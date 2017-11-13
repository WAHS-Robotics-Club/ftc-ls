package org.firstinspires.ftc.teamcode.program.autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class TestAutonomous extends LinearOpMode {
    DcMotor fl, fr, bl, br;

    @Override
    public void runOpMode() throws InterruptedException {
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");

        telemetry.update();

        waitForStart();



    }

}

