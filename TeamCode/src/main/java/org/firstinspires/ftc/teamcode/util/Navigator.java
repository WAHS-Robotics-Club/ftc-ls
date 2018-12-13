package org.firstinspires.ftc.teamcode.util;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.util.HardwareMapConstants;


public class Navigator {

    private static final String VUFORIA_KEY = "AfLxv2j/////AAABmYGPUEQbkUSboaGDXA3ZrG8gbZ8ovALERXI9LZm5oTH5KoH2USA2+zMEy3TQ8m8flx9YFAbuoqLkSkuWwOvbPXuWwnRe9Z/8kOum9F8P7haxIVSS366oxGFaocRAx7kgpPHk6/LWmhJsbZ9Erai9FEBYZnbfyoVxQSmLgi0QxP+sihyA1VjdOTANVcS+B6e2GMVEZppbro1GHoA/+SN4tVNQOAItQotgsmDmW0lpqxKLhTZ/+EeanbC5PjiPh+LWyETIO+/S4eRCkxSyw6OcvzUU9D8R7yWIdmPCMhltXcDHrjJdYRDb28Kth/7hSdjj3zSfogBQiHhjyRWDUkCeTGnGq6nuELLUTMJhRc/jRhyI";

    VuforiaLocalizer vuforia;

    public void init (HardwareMap map) {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;

        parameters.cameraName = map.get(WebcamName.class, "Webcam");

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

    }


}
