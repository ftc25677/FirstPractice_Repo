package org.firstinspires.ftc.teamcode.Tutorial;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.hardware.Servo;

@TeleOp(name = "SampleTeleOp", group = "Tutorial")
public class SampleTeleOp extends OpMode {

DcMotor leftFront, rightFront;
DcMotor leftRear, rightRear;
    @Override
    //Servo claw;
    public void init(){
leftFront = hardwareMap.get(DcMotor.class, deviceName: "motor1");
        rightFront = hardwareMap.get(DcMotor.class, deviceName: "motor2");
        leftRear = hardwareMap.get(DcMotor.class, deviceName: "motor0");
        rightRear = hardwareMap.get(DcMotor.class, deviceName: "motor3");
        //claw = hardwareMap.get(Servo.class deviceName: "claw")
    }
    @Override
    public void loop(){
        leftFront.setPower(Range.clip(gamepad1.left_stick_y, min: -1 max: 1));
        rightFront.setPower(Range.clip(gamepad1.right_stick_y, min: -1 max: 1));
        leftRear.setPower(Range.clip(gamepad1.left_stick_y, min: -1 max: 1));
        rightRear.setPower(Range.clip(gamepad1.right_stick_y, min: -1 max: 1));

      //  if(gamepad2.a){
        //    claw.setPosition(0.5);
      //  }else{
        //    claw.setPosition(1);
        }
    }
}