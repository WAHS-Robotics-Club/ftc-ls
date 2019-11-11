package org.firstinspires.ftc.teamcode.hardwareconstants;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HardwareMapConstants {
    /*  This class maps the represented names of motors, servos, and sensors in
        the program to the hardware on the robot. The robot phone reads the map and
        assigns the hardware to the objects in the TeleOp.
     */
    static DcMotor frontLeft, frontRight, backLeft, backRight, craneLeft, craneRight;
    static HardwareMap hardwareMap;

    //Maps the wheels' motors.
    public void driveTrainWheels() {
        frontLeft = hardwareMap.dcMotor.get("FrontLeft");
        frontRight = hardwareMap.dcMotor.get("FrontRight");
        backLeft = hardwareMap.dcMotor.get("BackLeft");
        backRight = hardwareMap.dcMotor.get("BackRight");
    }

    //Maps the cranes' motors.
    public void craneMotors() {
        craneLeft = hardwareMap.dcMotor.get("CraneLeft");
        craneRight = hardwareMap.dcMotor.get("CraneRight");
    }
}
