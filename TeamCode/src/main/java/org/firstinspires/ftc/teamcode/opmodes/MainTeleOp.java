package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.subsystems.Shooter.FlywheelSubsystem;

@TeleOp(name = "MainTeleOpTest", group = "TeleOp")
public class MainTeleOp extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    @Override
    public void runOpMode() {
        initializeDrive();
        FlywheelSubsystem flywheel = new FlywheelSubsystem(hardwareMap); //basically line 12 in FlywheelSub

        telemetry.addLine("Ready");
        telemetry.update();

        try {
            waitForStart();

            if (isStopRequested()) {
                return;
            }

            while (opModeIsActive()) {
                drive();
                idle();

                if (gamepad1.right_trigger > 0) {
                    flywheel.runForward();
                } else {
                    flywheel.stop();
                }
            }
        } finally {
            stopDrive();
            flywheel.stop();
        }
    }

    private void initializeDrive() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        for (DcMotor motor : new DcMotor[] {frontLeft, frontRight, backLeft, backRight}) {
            motor.setPower(0.0);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    private void drive() {
        double forward = (gamepad1.left_stick_y);
        double strafe = (gamepad1.left_stick_x);
        double turn = (gamepad1.right_stick_x);
        double denominator = Math.max(
                Math.abs(forward) + Math.abs(strafe) + Math.abs(turn), 1.0);

        frontLeft.setPower((forward + strafe + turn) / denominator);
        frontRight.setPower((forward - strafe - turn) / denominator);
        backLeft.setPower((forward - strafe + turn) / denominator);
        backRight.setPower((forward + strafe - turn) / denominator);
    }

    private void stopDrive() {
        frontLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backLeft.setPower(0.0);
        backRight.setPower(0.0);
    }
}