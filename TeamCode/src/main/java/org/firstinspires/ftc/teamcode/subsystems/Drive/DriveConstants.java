package org.firstinspires.ftc.teamcode.subsystems.Drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public final class DriveConstants {

    public static final String FRONT_LEFT_NAME = "frontLeft";
    public static final String FRONT_RIGHT_NAME = "frontRight";
    public static final String BACK_LEFT_NAME = "backLeft";
    public static final String BACK_RIGHT_NAME = "backRight";

    // Restore the directions from your attached constants instead of reversing all four.
    public static final DcMotorSimple.Direction FRONT_LEFT_DIRECTION =
            DcMotorSimple.Direction.REVERSE;
    public static final DcMotorSimple.Direction FRONT_RIGHT_DIRECTION =
            DcMotorSimple.Direction.FORWARD;
    public static final DcMotorSimple.Direction BACK_LEFT_DIRECTION =
            DcMotorSimple.Direction.REVERSE;
    public static final DcMotorSimple.Direction BACK_RIGHT_DIRECTION =
            DcMotorSimple.Direction.FORWARD;

    public static final DcMotor.RunMode RUN_MODE = DcMotor.RunMode.RUN_WITHOUT_ENCODER;
    public static final DcMotor.ZeroPowerBehavior ZERO_POWER_BEHAVIOR =
            DcMotor.ZeroPowerBehavior.BRAKE;

    // Joystick component multipliers, preserved from your attached constants.
    public static final double DRIVE_SPEED = 0.5;
    public static final double TURN_SPEED = 0.5;
    public static final double JOYSTICK_DEADBAND = 0.05;

    // Invert sideways commands because you reported left/right strafe reversed.
    // Recheck this after testing the corrected motor directions; use +1.0 if needed.
    public static final double STRAFE_DIRECTION = -1.0;

    private DriveConstants() {}
}
