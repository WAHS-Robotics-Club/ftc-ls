package org.firstinspires.ftc.teamcode.program.autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class TestAutonomous extends LinearOpMode {
    DcMotor fl, fr, bl, br;
    Servo ls, rs, css;
    ColorSensor cs;

    @Override
    public void runOpMode() throws InterruptedException {
        fl = hardwareMap.dcMotor.get("fl");
        fr = hardwareMap.dcMotor.get("fr");
        bl = hardwareMap.dcMotor.get("bl");
        br = hardwareMap.dcMotor.get("br");
        cs = hardwareMap.colorSensor.get("color");
        ls = hardwareMap.servo.get("ls");
        rs = hardwareMap.servo.get("rs");
        css = hardwareMap.servo.get("css");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            telemetry.update();
        }

        move(-0.2, 0.2, -0.2, 0.2, 1250);
        css.setPosition(60);

        move(-0.2, -0.2, 0.2, 0.2, 500);


        if (cs.red() > 2) {
            move(-0.2, 0.2, -0.2, 0.2, 1000);
        } else {
            move(0.2, -0.2, 0.2, -0.2, 1000);
        }
    }

     public void move(double flpower, double frpower, double blpower, double brpower, int msec)
            throws InterruptedException {
        if (opModeIsActive()) {
            fl.setPower(flpower);
            fr.setPower(frpower);
            bl.setPower(blpower);
            br.setPower(brpower);
            sleep(msec);
            fl.setPower(0);
            fr.setPower(0);
            bl.setPower(0);
            br.setPower(0);

        }
    }

}
