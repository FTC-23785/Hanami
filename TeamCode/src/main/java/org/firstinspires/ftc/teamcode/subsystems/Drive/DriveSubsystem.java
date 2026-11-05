package org.firstinspires.ftc.teamcode.subsystems.Drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveSubsystem {

    // Set to -1.0 only if the robot strafes opposite to the left stick.
    private static final double STRAFE_DIRECTION = 1.0;

    private final DcMotor frontLeft;
    private final DcMotor frontRight;
    private final DcMotor backLeft;
    private final DcMotor backRight;

    public DriveSubsystem(HardwareMap hardwareMap) {
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

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void drive(Gamepad gamepad) {
        drive(
                -gamepad.left_stick_y,
                STRAFE_DIRECTION * gamepad.left_stick_x,
                gamepad.right_stick_x
        );
    }

    // Positive inputs mean forward, strafe right, and turn clockwise.
    public void drive(double forward, double strafe, double turn) {
        double denominator = Math.max(
                Math.abs(forward) + Math.abs(strafe) + Math.abs(turn),
                1.0
        );

        frontLeft.setPower((forward + strafe + turn) / denominator);
        frontRight.setPower((forward - strafe - turn) / denominator);
        backLeft.setPower((forward - strafe + turn) / denominator);
        backRight.setPower((forward + strafe - turn) / denominator);
    }

    public void stop() {
        frontLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backLeft.setPower(0.0);
        backRight.setPower(0.0);
    }
}
