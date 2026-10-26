package org.firstinspires.ftc.teamcode.subsystems.Drive;

import com.qualcomm.robotcore.hardware.DcMotor;

public final class DriveConstants {

    public static final String FRONT_LEFT_NAME = "frontLeft";
    public static final String FRONT_RIGHT_NAME = "frontRight";
    public static final String BACK_LEFT_NAME = "backLeft";
    public static final String BACK_RIGHT_NAME = "backRight";

    public static final DcMotor.Direction FRONT_LEFT_DIRECTION =
            DcMotor.Direction.REVERSE;
    public static final DcMotor.Direction FRONT_RIGHT_DIRECTION =
            DcMotor.Direction.FORWARD;
    public static final DcMotor.Direction BACK_LEFT_DIRECTION =
            DcMotor.Direction.REVERSE;
    public static final DcMotor.Direction BACK_RIGHT_DIRECTION =
            DcMotor.Direction.FORWARD;

    public static final DcMotor.RunMode RUN_MODE =
            DcMotor.RunMode.RUN_WITHOUT_ENCODER;

    public static final DcMotor.ZeroPowerBehavior ZERO_POWER_BEHAVIOR =
            DcMotor.ZeroPowerBehavior.BRAKE;

    public static final double DRIVE_SPEED = 1.0;
    public static final double TURN_SPEED = 1.0;
    //gpt says we need deadband? del later if it doesnt matter
    public static final double JOYSTICK_DEADBAND = 0.05;

    private DriveConstants() {
    }
}