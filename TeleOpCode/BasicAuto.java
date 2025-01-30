package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;





@Autonomous(name="BasicAuto", group="Linear OpMode")

public class MeetAuto extends LinearOpMode{
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightBack = null;
    private DcMotor viper1 = null;
    private Servo  SpecimenClaw = null;
    /*public void MoveForwardFullSpeed*/

    @Override
    public void runOpMode() throws InterruptedException {
        leftFront  = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        viper1 = hardwareMap.get(DcMotor.class, "viper1");
        SpecimenClaw = hardwareMap.get(Servo.class, "claw3");
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        viper1.setDirection(DcMotorSimple.Direction.FORWARD);
        waitForStart();
        while (opModeIsActive()){
            //open and close claw fully is 3.5
            SpecimenClaw.setPosition(0.03);
            viper1.setPower(11.5);

            Thread.sleep(200);




            /*Have to break after each movement while in while loop*/
            rightFront.setPower(-0.3);
            leftFront.setPower(-0.3);
            rightBack.setPower(-0.3);
            leftBack.setPower(-0.3);

            Thread.sleep(1675);

            rightFront.setPower(0);
            leftFront.setPower(0);
            rightBack.setPower(0);
            leftBack.setPower(0);

            Thread.sleep(100);


            viper1.setPower(-3);

            Thread.sleep(500);

            SpecimenClaw.setPosition(0);

            rightFront.setPower(0.55);
            leftFront.setPower(0.55);
            rightBack.setPower(0.55);
            leftBack.setPower(0.55);

            Thread.sleep(450);

            rightFront.setPower(0);
            leftFront.setPower(0);
            rightBack.setPower(0);
            leftBack.setPower(0);

            Thread.sleep(100);

            rightFront.setPower(-0.55);
            leftFront.setPower(0.55);
            rightBack.setPower(0.55);
            leftBack.setPower(-0.55);

            Thread.sleep(1075);

            break;





        }


    }
}




