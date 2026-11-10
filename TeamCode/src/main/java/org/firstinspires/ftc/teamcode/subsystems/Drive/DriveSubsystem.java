package org.firstinspires.ftc.teamcode.subsystems.Drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveSubsystem {
    private final HardwareMap hardwareMap;

    private static DriveSubsystem instance;
    private final Gamepad gamepad1;
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    private DriveSubsystem(HardwareMap hardwareMap, Gamepad gamepad1) {
        this.hardwareMap = hardwareMap;
        this.gamepad1 = gamepad1;
    }

    // Always build a fresh instance so a stale hardwareMap/gamepad from a
    // previous OpMode run (or an old config) is never reused.
    public static DriveSubsystem getInstance(HardwareMap hardwareMap, Gamepad gamepad1) {
        instance = new DriveSubsystem(hardwareMap, gamepad1);
        return instance;
    }

    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, DriveConstants.FRONT_LEFT_NAME);
        frontRight = hardwareMap.get(DcMotor.class, DriveConstants.FRONT_RIGHT_NAME);
        backLeft = hardwareMap.get(DcMotor.class, DriveConstants.BACK_LEFT_NAME);
        backRight = hardwareMap.get(DcMotor.class, DriveConstants.BACK_RIGHT_NAME);

        // Directions live in DriveConstants so each wheel can be fixed in one place.
        frontLeft.setDirection(DriveConstants.FRONT_LEFT_DIRECTION);
        backLeft.setDirection(DriveConstants.BACK_LEFT_DIRECTION);
        frontRight.setDirection(DriveConstants.FRONT_RIGHT_DIRECTION);
        backRight.setDirection(DriveConstants.BACK_RIGHT_DIRECTION);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /** Call every loop from MainTeleOp. Reads gamepad1 sticks. */
    public void drive() {
        double forward = deadband(gamepad1.left_stick_y);
        double strafe = -deadband(gamepad1.left_stick_x);
        double turn = -deadband(gamepad1.right_stick_x);

        drive(forward, strafe, turn);
    }

    /** Robot-centric mecanum drive. Inputs range from -1 to 1. */
    public void drive(double forward, double strafe, double turn) {
        double fl = forward + strafe + turn;
        double bl = forward - strafe + turn;
        double fr = forward - strafe - turn;
        double br = forward + strafe - turn;

        // Scale down only if needed so the wheel ratios stay correct.
        double max = Math.max(1.0,
                Math.max(Math.max(Math.abs(fl), Math.abs(bl)),
                        Math.max(Math.abs(fr), Math.abs(br))));

        frontLeft.setPower(fl / max);
        backLeft.setPower(bl / max);
        frontRight.setPower(fr / max);
        backRight.setPower(br / max);
    }

    public void stop() {
        drive(0, 0, 0);
    }

    private double deadband(double value) {
        return Math.abs(value) < DriveConstants.DEADBAND ? 0 : value;
    }
}