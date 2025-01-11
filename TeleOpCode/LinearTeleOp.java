/* Copyright (c) 2021 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
/*

 *
 * 1) Axial:    Driving forward and backward               Left-joystick Forward/Backward
 * 2) Lateral:  Strafing right and left                     Left-joystick Right and Left
 * 3) Yaw:      Rotating Clockwise and counter clockwise    Right-joystick Right and Left


 */


@TeleOp(name="Basic: Meet3TeleOp", group="Linear OpMode")

public class Meet3TeleOp extends LinearOpMode {

    // Declare OpMode members for each of the 4 motors.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor bucketViper;
    private Servo SpecimenClaw;
    private Servo bucketServo;
    private CRServo extensionArm;
    private Servo extensionClaw;
    private Servo PivotClaw;
    double exnumber=0;
    boolean stopservo = false;
    double bucketPower;
    double bucketPower1;
    @Override
    public void runOpMode(){
        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "leftFront");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "leftBack");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFront");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightBack");
        extensionArm = hardwareMap.get(CRServo.class, "extensionArm");
        bucketViper = hardwareMap.get(DcMotor.class, "bucketViper");
        bucketServo =hardwareMap.get(Servo.class, "bucketServo");
        extensionClaw = hardwareMap.get(Servo.class, "extensionClaw");
        SpecimenClaw = hardwareMap.get(Servo.class, "claw3");
        PivotClaw = hardwareMap.get(Servo.class, "PivotClaw");
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);

        double ActionNumber =1;
        double movementPower = 0.5;
        double rightPower;
        double leftPower;

        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            if (gamepad1.y){
                movementPower=0.5;
            }else if(gamepad1.b){
                movementPower=0.75;
            }



            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            double OMNI = Range.clip(gamepad1.left_stick_x, -1, 1);

            leftPower    = Range.clip(drive - 2*turn, -movementPower,movementPower) ;
            rightPower   = Range.clip(drive + 2*turn, -movementPower,movementPower) ;

            double extenderPower;

            extenderPower = Range.clip(gamepad2.right_stick_y, -1.0, 1.0);
            bucketPower = Range.clip(gamepad2.right_trigger, -1.0, 1.0);
            bucketPower1 = Range.clip(gamepad2.left_trigger, -1.0, 1.0);
            // Normalize the values so no wheel power exceeds 1000%
            // This ensures that the robot maintains the desired motion.

            //extensionArm.setPower(extenderPower);
            if (gamepad2.dpad_up) {
                extensionClaw.setPosition(1);

            } else if (gamepad2.dpad_down) {
                extensionClaw.setPosition(0.8);
            }
            if (gamepad2.right_bumper) {
                SpecimenClaw.setPosition(0.6);

            }
            else if (gamepad2.left_bumper) {
                SpecimenClaw.setPosition(0);
            }
            // Send calculated power to wheels
            leftFrontDrive.setPower(rightPower);
            rightFrontDrive.setPower(leftPower);
            leftBackDrive.setPower(rightPower);
            rightBackDrive.setPower(leftPower);
            bucketViper.setPower(bucketPower);
            bucketViper.setPower(-bucketPower1);
            extensionArm.setPower(-extenderPower);
          /*  //The next Few Lines are Pseudo Code
         if (button is pressed){
         extension arm set power -1
         sleep for 1000 miliseconds
         turn Pivot Claw Back
         open extension claw
         lift viper
         drop bucket
       }

*/
            if(gamepad2.b){
                bucketServo.setPosition(0.30);
            }
            if(gamepad2.a){
                bucketServo.setPosition(0.33);
            }
            if(gamepad2.y){
                bucketServo.setPosition(0.18);
                sleep(1000);
                bucketServo.setPosition(0.33);
            }
            //ALL VALUES HAVE TO BE CHANGED = CURRENT CODE IS *yes* CALIBRATED
            if(gamepad2.x){
                extensionArm.setPower(-0.2);
                sleep(400);
                PivotClaw.setPosition(-1);
                sleep(1000);
                extensionArm.setPower(0);
                extensionClaw.setPosition(0.8);
                sleep(100);
                PivotClaw.setPosition(0.3);
                sleep(100);
                extensionClaw.setPosition(1);
                sleep(500);
                bucketServo.setPosition(0.31);
                sleep(100);
                bucketServo.setPosition(0.33);
                sleep(150);
                extensionArm.setPower(0.2);
                sleep(1000);

            }
            if(gamepad2.dpad_right){
                PivotClaw.setPosition(-1);

            }else if(gamepad2.dpad_left){
                PivotClaw.setPosition(0.8);
            }
            while (gamepad1.right_bumper){
                rightFrontDrive.setPower(0.6);
                leftFrontDrive.setPower(0.6);
                rightBackDrive.setPower(-0.6);
                leftBackDrive.setPower(-0.6);
            }

            while (gamepad1.left_bumper){
                rightFrontDrive.setPower(-0.6);
                leftFrontDrive.setPower(-0.6);
                rightBackDrive.setPower(0.6);
                leftBackDrive.setPower(0.6);
            }
            telemetry.addData("servo", bucketServo.getPosition());
            telemetry.update();
        }


    }

}