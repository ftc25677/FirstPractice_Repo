/* Copyright (c) 2017 FIRST. All rights reserved.
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


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

//hiiiii
/*
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The n
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * ames of OpModes appear on the menu
 * of the FTC Driver Station. When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@TeleOp(name = "LinearPov")

public class SampleTeleOp1 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor rightBack = null;
    private DcMotor leftBack = null;

    private Servo Claw = null;
    private Servo pivotClaw = null;
    private DcMotor linear1 = null;
    private DcMotor viper2 = null;
    double speed50=0.5;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftFront  = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightBack");
        leftBack  = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightFront");

        Claw = hardwareMap.get(Servo.class, "claw1");
        linear1 = hardwareMap.get(DcMotor.class, "viper1");
        viper2 = hardwareMap.get(DcMotor.class, "pivot1");


        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.FORWARD);
        viper2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        // Wait for the game to start (driver presse s START)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            double pivotPower;
            double viperPower;
            double holdStop = 0.1;
            double holdStop1 = -0.1;
            double OMNIPower0 = 0;
            double OMNIPower1 = 1;
            double OMNIPower2 = 1;




            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            //Movement
            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            pivotPower = Range.clip(gamepad2.right_stick_y, -1.0 , 1.0);
            leftPower    = Range.clip(drive + turn, -speed50, speed50) ;
            rightPower   = Range.clip(drive - turn, -speed50, speed50) ;
            if (gamepad1.y){
                speed50=0.5;
            }else if(gamepad1.b){
                speed50=0.75;
            }

            viperPower = Range.clip(gamepad2.left_stick_y, -1.0, 1.0);
            double OMNI = Range.clip(gamepad1.left_stick_x, -1, 1);




            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;

            // Send calculated power to wheels
            leftFront.setPower(leftPower);
            rightBack.setPower(rightPower);
            leftBack.setPower(leftPower);
            rightFront.setPower(rightPower);
            linear1.setPower(-viperPower);
            viper2.setPower(pivotPower);

            if (gamepad2.left_bumper) {
                Claw.setPosition(1);
            } else if (gamepad2.right_bumper) {
                Claw.setPosition(0.5);
            }



            if (gamepad2.dpad_up) {
                Claw.setPosition(1);
            } else if (gamepad2.dpad_down) {
                Claw.setPosition(0.5);
            }


            if (gamepad2.right_trigger > 0){
                viper2.setPower(holdStop);
            }
            if (gamepad2.left_trigger > 0){
                viper2.setPower(holdStop1);
            }

            if (gamepad1.right_bumper){
                leftFront.setPower(OMNIPower1);
                rightFront.setPower(OMNIPower1);
                leftBack.setPower(-OMNIPower1);
                rightBack.setPower(-OMNIPower1);
            }else if(gamepad1.dpad_up){
                leftFront.setPower(OMNIPower0);
                rightFront.setPower(OMNIPower0);
                leftBack.setPower(OMNIPower0);
                rightBack.setPower(OMNIPower0);
                leftFront.setPower(OMNIPower0);
            } else if (gamepad1.left_bumper) {
                leftFront.setPower(-OMNIPower1);
                rightFront.setPower(-OMNIPower1);
                leftBack.setPower(OMNIPower1);
                rightBack.setPower(OMNIPower1);
            }
            // Show the elapsed game time and wheel power.
            ;
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}
