package org.firstinspires.ftc.teamcode.program.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Toggle;
import org.firstinspires.ftc.teamcode.program.HardwareMapConstants;
import org.firstinspires.ftc.teamcode.program.autonomous.TestAutonomous;

import static java.lang.Math.*;

@TeleOp(name = "LS TeleOp")
public class lsTeleOp extends OpMode {
    private DcMotor arm;
    private Servo leftClaw, rightClaw;

    final double FULLSPEED = 0.75;

    MecanumDrive alexander = new MecanumDrive();

    Servo jewelWhacker;

    ColorSensor cs;

    @Override
    public void init() {
        alexander.init(hardwareMap);

        arm = hardwareMap.dcMotor.get(HardwareMapConstants.MOTOR_ARM);

        jewelWhacker = hardwareMap.servo.get(HardwareMapConstants.COLOR_SERVO);

        cs = hardwareMap.colorSensor.get(HardwareMapConstants.COLOR_SENSOR);

        leftClaw = hardwareMap.servo.get(HardwareMapConstants.LEFT_CLAW);
        rightClaw = hardwareMap.servo.get(HardwareMapConstants.RIGHT_CLAW);
    }

    private Toggle slowToggle = new Toggle();
    private Toggle fourDirectionToggle = new Toggle();
    private Toggle clawToggle = new Toggle();

    @Override
    public void loop() {
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double turnPower = gamepad1.right_stick_x;

        if(gamepad1.y) {
            slowToggle.toggle();
        }

        if(gamepad1.x) {
            fourDirectionToggle.toggle();
        }

        if(slowToggle.isToggled()) {
            x /= 2;
            y /= 2;
            turnPower /= 2;
        }

        if(fourDirectionToggle.isToggled()) {
            if(abs(x) < abs(y)) {
                x = 0;
            } else {
                y = 0;
            }
        }

        alexander.moveAndTurn(x, y, turnPower);

        if (gamepad1.left_trigger >= 0.05) {
            arm.setPower(gamepad1.left_trigger / 2);
        } else if (gamepad1.right_trigger >= 0.05) {
            arm.setPower(-gamepad1.right_trigger / 2);
        } else {
            arm.setPower(0);
        }

        telemetry.addData("Foof", arm.getPower());
        telemetry.update();

        if(gamepad1.right_bumper) {
            clawToggle.toggle();
        }

        if (clawToggle.isToggled()) { //open
            leftClaw.setPosition(0.80);
            rightClaw.setPosition(0.15);
        } else { //closed
            leftClaw.setPosition(0.425);
            rightClaw.setPosition(0.475);
        }
    }
}
