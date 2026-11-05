package org.firstinspires.ftc.teamcode.subsystems.Drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveSubsystem {

    private final DcMotor frontLeft;
    private final DcMotor frontRight;
    private final DcMotor backLeft;
    private final DcMotor backRight;

    public DriveSubsystem(HardwareMap hardwareMap) {
        frontLeft = hardwareMap.get(DcMotor.class, DriveConstants.FRONT_LEFT_NAME);
        frontRight = hardwareMap.get(DcMotor.class, DriveConstants.FRONT_RIGHT_NAME);
        backLeft = hardwareMap.get(DcMotor.class, DriveConstants.BACK_LEFT_NAME);
        backRight = hardwareMap.get(DcMotor.class, DriveConstants.BACK_RIGHT_NAME);

        configureMotor(frontLeft, DriveConstants.FRONT_LEFT_DIRECTION);
        configureMotor(frontRight, DriveConstants.FRONT_RIGHT_DIRECTION);
        configureMotor(backLeft, DriveConstants.BACK_LEFT_DIRECTION);
        configureMotor(backRight, DriveConstants.BACK_RIGHT_DIRECTION);
    }

    private void configureMotor(DcMotor motor, DcMotorSimple.Direction direction) {
        motor.setPower(0.0);
        motor.setDirection(direction);
        motor.setZeroPowerBehavior(DriveConstants.ZERO_POWER_BEHAVIOR);
        motor.setMode(DriveConstants.RUN_MODE);
    }

    public void drive(Gamepad gamepad) {
        drive(
                -gamepad.left_stick_y,
                gamepad.left_stick_x,
                gamepad.right_stick_x
        );
    }

    // Requested robot motion: forward, right strafe, clockwise turn are positive.
    // STRAFE_DIRECTION adapts the strafe request to your reported wheel response.
    public void drive(double forward, double strafe, double turn) {
        forward = applyDeadband(forward) * DriveConstants.DRIVE_SPEED;
        strafe = applyDeadband(strafe)
                * DriveConstants.DRIVE_SPEED * DriveConstants.STRAFE_DIRECTION;
        turn = applyDeadband(turn) * DriveConstants.TURN_SPEED;

        double frontLeftPower = forward + strafe + turn;
        double frontRightPower = forward - strafe - turn;
        double backLeftPower = forward - strafe + turn;
        double backRightPower = forward + strafe - turn;

        // Preserve the wheel-power ratios when combining driving, strafing, and turning.
        double denominator = Math.max(1.0,
                Math.max(Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower)),
                        Math.max(Math.abs(backLeftPower), Math.abs(backRightPower))));

        frontLeft.setPower(frontLeftPower / denominator);
        frontRight.setPower(frontRightPower / denominator);
        backLeft.setPower(backLeftPower / denominator);
        backRight.setPower(backRightPower / denominator);
    }

    private double applyDeadband(double value) {
        return Math.abs(value) <= DriveConstants.JOYSTICK_DEADBAND ? 0.0 : value;
    }

    public void stop() {
        frontLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backLeft.setPower(0.0);
        backRight.setPower(0.0);
    }
}
