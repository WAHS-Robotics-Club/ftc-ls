package org.firstinspires.ftc.teamcode.lastyear.util;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.LABEL_GOLD_MINERAL;
import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.LABEL_SILVER_MINERAL;
import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.TFOD_MODEL_ASSET;


public class Navigator {

    private static final String VUFORIA_KEY = "AfLxv2j/////AAABmYGPUEQbkUSboaGDXA3ZrG8gbZ8ovALERXI9LZm5oTH5KoH2USA2+zMEy3TQ8m8flx9YFAbuoqLkSkuWwOvbPXuWwnRe9Z/8kOum9F8P7haxIVSS366oxGFaocRAx7kgpPHk6/LWmhJsbZ9Erai9FEBYZnbfyoVxQSmLgi0QxP+sihyA1VjdOTANVcS+B6e2GMVEZppbro1GHoA/+SN4tVNQOAItQotgsmDmW0lpqxKLhTZ/+EeanbC5PjiPh+LWyETIO+/S4eRCkxSyw6OcvzUU9D8R7yWIdmPCMhltXcDHrjJdYRDb28Kth/7hSdjj3zSfogBQiHhjyRWDUkCeTGnGq6nuELLUTMJhRc/jRhyI";

    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    public void init (HardwareMap map) {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;

        parameters.cameraName = map.get(WebcamName.class, "Webcam");

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }

    public void Tfod(HardwareMap map, Telemetry telemetry){

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod(map);
        }
        tfod.activate();

        int goldmineralX = 1;

        telemetry.addData("gold mineral x", goldmineralX);
        telemetry.update();
        }

    private void initTfod(HardwareMap map) {
        int tfodMonitorViewId = map.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", map.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

    public void Tfod(HardwareMap map) {
    }
}
