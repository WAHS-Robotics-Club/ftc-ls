//package org.firstinspires.ftc.teamcode.program.teleOp;
//
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.Servo;
//
//import org.firstinspires.ftc.teamcode.program.HardwareMapConstants;
//
//@TeleOp (name = "Jan the Man")
//public class Jan extends OpMode{
//
//    DcMotor fl,fr,bl,br,arm;
//    Servo leftClaw,rightClaw;
//    final int ZERO = 0;
//
//    @Override
//    public void init() {
//        fl = hardwareMap.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_LEFT);
//        fr = hardwareMap.dcMotor.get(HardwareMapConstants.MOTOR_FRONT_RIGHT);
//        bl = hardwareMap.dcMotor.get(HardwareMapConstants.MOTOR_BACK_LEFT);
//        br = hardwareMap.dcMotor.get(HardwareMapConstants.MOTOR_BACK_RIGHT);
//
//        arm = hardwareMap.dcMotor.get(HardwareMapConstants.MOTOR_ARM);
//
//        leftClaw = hardwareMap.servo.get(HardwareMapConstants.LEFT_CLAW);
//        rightClaw = hardwareMap.servo.get(HardwareMapConstants.RIGHT_CLAW);
//
//        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
////        leftClaw.setPosition(1);
////        rightClaw.setPosition(0.5);
//
//        telemetry.addData ("Left claw is at position ", leftClaw.getPosition());
//        telemetry.addData ("Right claw is at position ", rightClaw.getPosition());
//        telemetry.update();
//    }
//
//    @Override
//    public void loop() {
//        fr.setPower(0.4);
//        fl.setPower(0.4);
//        br.setPower(0.4);
//        bl.setPower(0.4);
//
//        telemetry.addData ("fr", fr.getCurrentPosition());
//        telemetry.addData ("fl", fl.getCurrentPosition());
////        if(gamepad1.x){
////            //hah the remenants of a previous version's testing
////        }
////        if(gamepad1.a){
////            rightClaw.setPosition(0.38);
////            leftClaw.setPosition(0.50);
////        }
////        if(gamepad1.b){
////            rightClaw.setPosition(0.725);
////            leftClaw.setPosition(0.225);
////        }
////
////
////        telemetry.addData ("Left claw is at position ", leftClaw.getPosition());
////        telemetry.addData ("Right claw is at position ", rightClaw.getPosition());
//        telemetry.update();
//    }
//}
