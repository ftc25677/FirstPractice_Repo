package AutoCodes;

public class MeetAutoPark {

    package AutoCodes;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


    @Autonomous(name="MeetAutoPark", group="Linear OpMode")
    public class MeetAutoPark extends LinearOpMode {
        private DcMotor leftFront = null;
        private DcMotor rightFront = null;
        private DcMotor leftBack = null;
        private DcMotor rightBack = null;
        private DcMotor viper1;
        private Servo SpecimenClaw;

        @Override
        public void runOpMode() throws InterruptedException {
            leftFront = hardwareMap.get(DcMotor.class, "leftFront");
            rightFront = hardwareMap.get(DcMotor.class, "rightFront");
            leftBack = hardwareMap.get(DcMotor.class, "leftBack");
            rightBack = hardwareMap.get(DcMotor.class, "rightBack");
            SpecimenClaw= hardwareMap.get(Servo.class, "claw3");
            viper1= hardwareMap.get (DcMotor.class, "bucketViper");
            rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
            leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
            leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
            rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
            waitForStart();

            while (opModeIsActive()) {
                SpecimenClaw.setPosition(0.6);//close

                rightFront.setPower(.5);
                leftFront.setPower(-.55);
                leftBack.setPower(.5);
                rightBack.setPower(-.55);
                Thread.sleep(1000);
                //does specimen twice


                break;
            }



        }

        public void robotMove(double power) {
            leftBack.setPower(power);
            leftFront.setPower(power);
            rightBack.setPower(power);
            rightFront.setPower(power);

        }

    }




}
