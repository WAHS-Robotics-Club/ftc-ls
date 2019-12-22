package org.firstinspires.ftc.teamcode.hardwareconstants;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HardwareMapConstants {
    /**  This class maps the represented names of motors, servos, and sensors in
        the program to the hardware on the robot. The hardware maps can be found in @LSBotBoi.
     */

    //Wheel motors.
    public static final String frontLeftWheel = "FrontLeft";
    public static final String frontRightWheel = "FrontRight";
    public static final String backLeftWheel = "BackLeft";
    public static final String backRightWheel = "BackRight";

    //Crane arm motors.
    public static final String craneLeft = "CraneLeft";
    public static final String craneRight = "CraneRight";

    //Claw orientation servos
    public static final String craneServoLeft = "CraneServoLeft";
    public static final String craneServoRight = "CraneServoRight";

    //Claw servos
    public static final String grabLeft = "GrabLeft";
    public static final String grabRight = "GrabRight";

    //Foundation grabber
    public static final String foundationHook = "FoundationHook";

}
