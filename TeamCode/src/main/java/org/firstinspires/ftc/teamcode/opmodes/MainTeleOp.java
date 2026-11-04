package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.subsystems.Intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Shooter.FlywheelSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Intake.IntakeSubsystem;

@TeleOp(name = "MainTeleOp", group = "TeleOp")
public class MainTeleOp extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    @Override
    public void runOpMode() {
        initializeDrive();
        FlywheelSubsystem flywheel = new FlywheelSubsystem(hardwareMap); //basically line 12 in FlywheelSub
        IntakeSubsystem intake = new IntakeSubsystem(hardwareMap);

        boolean previousLeftBumper = false;
        boolean previousRightBumper = false;

        telemetry.addLine("Ready");
        telemetry.update();

        try {
            waitForStart();

            if (isStopRequested()) {
                return;
            }

            while (opModeIsActive()) {
                boolean leftBumper = gamepad1.left_bumper;
                boolean rightBumper = gamepad1.right_bumper;
                boolean leftBumperPressed = leftBumper && !previousLeftBumper;
                boolean rightBumperPressed = rightBumper && !previousRightBumper;




                drive();
                idle();

                if (leftBumper && rightBumper) {
                    intake.stop();
                } else if (leftBumperPressed) {
                    intake.toggleReverse();
                } else if (rightBumperPressed) {
                    intake.toggleForward();
                }

                previousLeftBumper = leftBumper;
                previousRightBumper = rightBumper;

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

        for (DcMotor motor : new DcMotor[] {
                frontLeft, frontRight, backLeft, backRight
        }) {
            motor.setPower(0.0);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        // Direction settings inferred from the movement you described.
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    private void drive() {
        double forward = -gamepad1.left_stick_y;
        double strafe = -gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;

        double denominator = Math.max(
                Math.abs(forward) + Math.abs(strafe) + Math.abs(turn),
                1.0
        );

        double frontLeftPower = (forward + strafe + turn) / denominator;
        double frontRightPower = (forward - strafe - turn) / denominator;
        double backLeftPower = (forward - strafe + turn) / denominator;
        double backRightPower = (forward + strafe - turn) / denominator;

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }

    private void stopDrive() {
        frontLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backLeft.setPower(0.0);
        backRight.setPower(0.0);
    }
}