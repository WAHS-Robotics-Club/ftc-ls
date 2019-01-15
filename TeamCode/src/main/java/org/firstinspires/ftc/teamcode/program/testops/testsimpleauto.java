package org.firstinspires.ftc.teamcode.program.testops;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.LSRobot;

@Autonomous(name = "LS Gross Auto")
public class testsimpleauto extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";


    VuforiaLocalizer vuforia;
    TFObjectDetector detector;

    @Override
    public void runOpMode() throws InterruptedException {
        LSRobot lsBot = new LSRobot();

        lsBot.init(hardwareMap);

        waitForStart();

        while (opModeIsActive()){
            lsBot.Move(0, -1, 0, telemetry);
            Thread.sleep(2500);
            lsBot.Move(0, 0, 0, telemetry);
        }

        lsBot.MoveShooter(true, false);
        Thread.sleep(3000);
        lsBot.MoveShooter(false, false);

        lsBot.Shoot(true, false);
        Thread.sleep(2000);
        lsBot.Shoot(false, false);
    }
}