package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

    @Autonomous(name ="Warehouse RED")
    public class TestAutonomous extends LinearOpMode {

        //DriveTrain DcMotors:
        DcMotor fl;
        DcMotor bl;
        DcMotor fr;
        DcMotor br;

        //Appendage DcMotors:
        DcMotor spool;
        DcMotor grab;
        DcMotor carousel;

        @Override
        public void runOpMode() throws InterruptedException {
            //INIT PHASE BUTTON PRESSED
            //HardwareMap DcMotors:
            fl = hardwareMap.dcMotor.get("frontLeftMotor");
            bl = hardwareMap.dcMotor.get("backLeftMotor");
            fr = hardwareMap.dcMotor.get("frontRightMotor");
            br = hardwareMap.dcMotor.get("backRightMotor");

            grab = hardwareMap.dcMotor.get("grab");
            spool = hardwareMap.dcMotor.get("spoolMotor");
            carousel = hardwareMap.dcMotor.get("carouselSpinner");

            //PLAY PHASE BUTTON PRESSED
            //Wait for the button and subsequently wait 1/4 secs to start the program:
            waitForStart();
            sleep(250);

            fl.setPower(1);
            bl.setPower(1);
            fr.setPower(1);
            br.setPower(1);
            sleep(250);
            fl.setPower(1);
            bl.setPower(1);
            fr.setPower(-1);
            br.setPower(-1);
            sleep(500);
            fl.setPower(1);
            bl.setPower(1);
            fr.setPower(1);
            br.setPower(1);
            sleep(250);
            grab.setPower(0.2);
            wait(100);
            grab.setPower(-0.2);
            wait(100);
            spool.setPower(0.5);
            wait(100);
            grab.setPower(0.2);


        }
    }


