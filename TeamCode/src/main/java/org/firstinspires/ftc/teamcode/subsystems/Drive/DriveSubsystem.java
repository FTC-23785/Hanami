package org.firstinspires.ftc.teamcode.subsystems.Drive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "MecanumTeleOp")
public class DriveSubsystem extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(
                DcMotor.class, DriveConstants.FRONT_LEFT_NAME);
        frontRight = hardwareMap.get(
                DcMotor.class, DriveConstants.FRONT_RIGHT_NAME);
        backLeft = hardwareMap.get(
                DcMotor.class, DriveConstants.BACK_LEFT_NAME);
        backRight = hardwareMap.get(
                DcMotor.class, DriveConstants.BACK_RIGHT_NAME);

        frontLeft.setDirection(DriveConstants.FRONT_LEFT_DIRECTION);
        frontRight.setDirection(DriveConstants.FRONT_RIGHT_DIRECTION);
        backLeft.setDirection(DriveConstants.BACK_LEFT_DIRECTION);
        backRight.setDirection(DriveConstants.BACK_RIGHT_DIRECTION);

        DcMotor[] motors = {
                frontLeft, frontRight, backLeft, backRight
        };

        for (DcMotor motor : motors) {
            motor.setPower(0);
            motor.setMode(DriveConstants.RUN_MODE);
            motor.setZeroPowerBehavior(
                    DriveConstants.ZERO_POWER_BEHAVIOR);
        }

        telemetry.addLine("Drive initialized");
        telemetry.update();

        waitForStart();

        try {
            while (opModeIsActive()) {
                double y = applyDeadband(-gamepad1.left_stick_y);
                double x = applyDeadband(gamepad1.left_stick_x);
                double turn = applyDeadband(gamepad1.right_stick_x)
                        * DriveConstants.TURN_SPEED;

                double frontLeftPower = y + x + turn;
                double backLeftPower = y - x + turn;
                double frontRightPower = y - x - turn;
                double backRightPower = y + x - turn;

                double max = Math.max(
                        1.0,
                        Math.max(
                                Math.max(
                                        Math.abs(frontLeftPower),
                                        Math.abs(backLeftPower)
                                ),
                                Math.max(
                                        Math.abs(frontRightPower),
                                        Math.abs(backRightPower)
                                )
                        )
                );

                frontLeft.setPower(
                        frontLeftPower / max * DriveConstants.DRIVE_SPEED);
                backLeft.setPower(
                        backLeftPower / max * DriveConstants.DRIVE_SPEED);
                frontRight.setPower(
                        frontRightPower / max * DriveConstants.DRIVE_SPEED);
                backRight.setPower(
                        backRightPower / max * DriveConstants.DRIVE_SPEED);
            }
        } finally {
            for (DcMotor motor : motors) {
                motor.setPower(0);
            }
        }
    }
//del deadband?--> see comment in DriveConstants
    private double applyDeadband(double value) {
        if (Math.abs(value) <= DriveConstants.JOYSTICK_DEADBAND) {
            return 0.0;
        }

        return value;
    }
}