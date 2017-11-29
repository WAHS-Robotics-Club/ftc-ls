package org.firstinspires.ftc.teamcode.program.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "colorscensor")
public class SensorOfTheColor extends OpMode {

    ColorSensor cs;
    DistanceSensor ds;
    @Override

    public void init() {
        cs = hardwareMap.colorSensor.get("color");
        ds = hardwareMap.get(DistanceSensor.class, "color");
    }

    @Override
    public void loop() {

        double red = cs.red();   // Red channel value
        double green = cs.green(); // Green channel value
        double blue = cs.blue();  // Blue channel value

        cs.alpha(); // Total luminosity
        cs.argb();  // Combined color value

        telemetry.addData("R", red);
        telemetry.addData("G", green);
        telemetry.addData("B", blue);
        telemetry.addData("Distance", ds.getDistance(DistanceUnit.INCH));
        telemetry.update();
    }
}
