package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.LSBotBoi;

@TeleOp (name = "Main TeleOp")
public class TeleOpMain extends OpMode{
    LSBotBoi lsBot = new LSBotBoi();

    @Override
    public void init() {
        lsBot.initialize(hardwareMap);
        lsBot.setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop() {

        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double rotation = gamepad1.right_stick_x;

        double rightTrigger = gamepad1.right_trigger;
        double leftTrigger = gamepad1.left_trigger;

        boolean leftBumper = gamepad1.left_bumper;
        boolean rightBumper = gamepad1.right_bumper;

        telemetry.addData("Crane Encoder Postition:", lsBot.craneMotorLeft.getCurrentPosition());

        lsBot.move(x, y, rotation, rightBumper, telemetry);

        lsBot.setFoundationHook(leftBumper, telemetry);

    }
}
