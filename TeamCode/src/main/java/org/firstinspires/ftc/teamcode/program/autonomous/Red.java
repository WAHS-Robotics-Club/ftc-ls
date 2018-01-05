package org.firstinspires.ftc.teamcode.program.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.navigation.CameraSide;
import org.firstinspires.ftc.teamcode.navigation.Navigator;
import org.firstinspires.ftc.teamcode.navigation.PhoneOrientation;

@Autonomous (name = "LS Autonomous Red")
public class Red extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        AbstractAuto thing = new AbstractAuto(true, this);
        thing.run();
    }
}