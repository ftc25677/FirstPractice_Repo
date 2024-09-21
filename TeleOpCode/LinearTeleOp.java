package org.firstinspires.ftc.teamcode;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
@TeleOp(name="LinearOpMode", group="TeleOp")
public class LinearTeleOp extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor leftFront, rightFront;
    DcMotor leftRear, rightRear;
    DcMotor Pivot;
    @Override
    public void init() {
        leftFront = hardwareMap.get(DcMotor.class,"motor1");
        rightFront = hardwareMap.get(DcMotor.class, "motor2");
        leftRear = hardwareMap.get(DcMotor.class, "motor0");
        rightRear = hardwareMap.get(DcMotor.class, "motor3");
        pivotMotor = hardwareMap.get(DcMotor.class, "Pivot1");
// These codes properly add hardware blah blah
    }

    @Override
    public void loop() {
        double leftPower;
        double rightPower;
        double pivotPower;
        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;

        //Setting the Pivot motor to gamepad 2's left stick y
        double pivotTurn = gamepad2.left_stick_y;
        pivotPower = Range.clip(pivotTurn, -1.0. 1.0);

        // This sets the joysticks to their corresponding values
        leftPower = Range.clip(drive + turn, -1.0, 1.0) ;
        rightPower = Range.clip(drive - turn, -1.0, 1.0) ;
        //Math for calculating each wheel's power using the amount of drive requested and turn requested
        leftFront.setPower(leftPower);
        rightFront.setPower(rightPower);
        leftRear.setPower(leftPower);
        rightRear.setPower(rightPower);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
        telemetry.update();
//If this shows up, this commit worked Anirudh Anand 9/21

    }
    //End of LinearOp Mode code
}
