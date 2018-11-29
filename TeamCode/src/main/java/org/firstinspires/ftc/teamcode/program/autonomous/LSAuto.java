package org.firstinspires.ftc.teamcode.program.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.LSRobot;

@Autonomous(name = "LS Autonomous")
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

        lsBot.AutoMove(45, 2, 0.3);
    }
}