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
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

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

public class SampleArmEncoderTeleOp1 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    //RunTime for Telemetry(Not Important, Just for testing data)

    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor rightBack = null;
    private DcMotor leftBack = null;
    //Movement Motors


    private CRServo ContinuousIntakeSide2 = null;
    private CRServo ContinuousIntakeSide1 = null;
    private Servo SpecimenContinuousIntakeSide2;
    //Servos (2 for Intake 1 for ContinuousIntakeSide2)

    //Attachment Motors (Linear Slide and Pivot)
    private DcMotor linear1 = null;
    private DcMotor pivot1 = null;

    // pivot Pos is the same as currentPos, but is called a tick earlier. This helps for holdStop (PIVOT POSITION)
    int pivotPos;
    int currentpivotPos;

    //Power for movemetn, helps change speeds
    double movementPower=0.5;

    //Target Position for encodors
    int Targetpos;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        //Telemetry data for status (Dont worry about it)


        leftFront  = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightBack");
        leftBack  = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightFront");

        // Calling all the motors on the driver station

        ContinuousIntakeSide2 = hardwareMap.get(CRServo.class, "ContinuousIntakeSide21");
        ContinuousIntakeSide1 = hardwareMap.get(CRServo.class, "ContinuousIntakeSide1");
        SpecimenContinuousIntakeSide2 = hardwareMap.get(Servo.class, "SpecimenContinuousIntakeSide2");

        // Calling all  the ContinuousIntakeSide2s on driver station

        linear1 = hardwareMap.get(DcMotor.class, "viper1");
        pivot1 = hardwareMap.get(DcMotor.class, "pivot1");

        //Calling attachments
        pivot1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pivot1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Presetting the encoders for the pivot






        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.FORWARD);
        //Setting movement motors directions to match the robot's build/orientation



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
            //Power variables for cleaner code

            double OMNIPower0 = 0;
            double OMNIPower1 = 1;
            double OMNIPower2 = -1;

            //OMNI variables so it can be used on a button


            final double INTAKE_COLLECT    = -1.0;
            final double INTAKE_OFF        =  0.0;
            final double INTAKE_DEPOSIT    =  0.5;
            //Intake variables for cleaner code and continual movement
//




            //Movement
            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            double OMNI = Range.clip(gamepad1.left_stick_x, -1, 1);
            pivotPower = Range.clip(gamepad2.right_stick_y, -0.5 , 0.5);
            leftPower    = Range.clip(drive + turn, -movementPower, movementPower) ;
            rightPower   = Range.clip(drive - turn, -movementPower, movementPower) ;
            viperPower = Range.clip(gamepad2.left_stick_y, -1.0, 1.0);

            //Speed Changing using Buttons
            if (gamepad1.y){
                movementPower=0.5;
            }else if(gamepad1.b){
                movementPower=0.75;
            }


// Re updating position variables
            pivotPos = pivot1.getCurrentPosition();
            currentpivotPos = pivot1.getCurrentPosition();



            // Send calculated power to wheels
            leftFront.setPower(leftPower);
            rightBack.setPower(rightPower);
            leftBack.setPower(leftPower);
            rightFront.setPower(rightPower);
            linear1.setPower(-viperPower);



            //Continuous motion intake
            if(gamepad2.left_bumper){
                ContinuousIntakeSide2.setPower(INTAKE_COLLECT);
                ContinuousIntakeSide1.setPower(-INTAKE_COLLECT);
            }else if (gamepad2.right_bumper){
                ContinuousIntakeSide2.setPower(INTAKE_DEPOSIT);
                ContinuousIntakeSide1.setPower(-INTAKE_DEPOSIT);
            } else{
                ContinuousIntakeSide2.setPower(INTAKE_OFF);
                ContinuousIntakeSide1.setPower(INTAKE_OFF);
            }





// Omni movement code for buttons
            if (gamepad1.left_bumper){
                leftFront.setPower(OMNIPower2);
                rightFront.setPower(OMNIPower2);
                leftBack.setPower(OMNIPower1);
                rightBack.setPower(OMNIPower1);

            } else if (gamepad1.right_bumper) {
                leftFront.setPower(OMNIPower1);
                rightFront.setPower(OMNIPower1);
                leftBack.setPower(OMNIPower2);
                rightBack.setPower(OMNIPower2);
            }

            //ContinuousIntakeSide2 positioning matches ContinuousIntakeSide2)
            if (gamepad2.dpad_up){
                SpecimenContinuousIntakeSide2.setPosition(0.65);
            } else if (gamepad2.dpad_right){
                SpecimenContinuousIntakeSide2.setPosition(0.5);
            }else if (gamepad2.dpad_down){
                SpecimenContinuousIntakeSide2.setPosition(0.4);
            }



            //PIVOT HOLD STOP FUNCTION (ASK ANI)
            if(pivotPower > 0){
                Targetpos += 10;
                pivot1.setTargetPosition(Targetpos);
                pivot1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                pivot1.setPower(pivotPower);
                pivotPos = pivot1.getCurrentPosition();
                currentpivotPos = pivot1.getCurrentPosition();
            } else if (pivotPower <0 ) {
                Targetpos -= 10;
                pivotPos = pivot1.getCurrentPosition();
                currentpivotPos = pivot1.getCurrentPosition();
                pivot1.setTargetPosition(Targetpos);
                pivot1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                pivot1.setPower(pivotPower);
            } else if(pivotPower == 0 && pivotPos != currentpivotPos) {
                pivotPos = pivot1.getCurrentPosition();
                currentpivotPos = pivot1.getCurrentPosition();
                Targetpos = pivotPos;
                pivot1.setTargetPosition(Targetpos);
                pivot1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                pivot1.setPower(3);
            }else{
                pivotPos = pivot1.getCurrentPosition();
                currentpivotPos = pivot1.getCurrentPosition();
            }


            //Final Lift
            if (gamepad2.x){
                pivot1.setPower(1);
                sleep(500000);
            }

            //Telemetry
            pivotPos = pivot1.getCurrentPosition();
            // Show the elapsed game time and wheel power.
            telemetry.addData("Pivot", pivotPos);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}
