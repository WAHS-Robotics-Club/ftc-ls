package org.firstinspires.ftc.teamcode.previousyears.program.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.previousyears.LSRobot;

@Autonomous(name = "LS AutonomousRed")
@Disabled
public class LSAuto extends LinearOpMode {
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

//        lsBot.Lower();

        lsBot.AutoMove(0, 180, 0, telemetry);

        lsBot.Stop(telemetry);
    }
}