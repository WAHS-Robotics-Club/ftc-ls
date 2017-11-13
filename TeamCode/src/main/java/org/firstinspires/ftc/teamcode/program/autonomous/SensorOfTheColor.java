package org.firstinspires.ftc.teamcode.program.autonomous;
import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

public class SensorOfTheColor extends OpMode {

    ColorSensor color_sensor;
    @Override

    public void init() {
        color_sensor = hardwareMap.colorSensor.get("color");

    }

    @Override
    public void loop() {
        double red = color_sensor.red();   // Red channel value
        double green = color_sensor.green(); // Green channel value
        double blue = color_sensor.blue();  // Blue channel value

        color_sensor.alpha(); // Total luminosity
        color_sensor.argb();  // Combined color value

        telemetry.addData("R", red);
        telemetry.addData("G", green);
        telemetry.addData("B", blue);
        telemetry.update();
    }
}
