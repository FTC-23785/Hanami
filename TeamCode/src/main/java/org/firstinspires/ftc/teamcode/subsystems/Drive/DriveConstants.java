package org.firstinspires.ftc.teamcode.subsystems.Drive;

import com.qualcomm.robotcore.hardware.DcMotor;

public final class DriveConstants {

    public static final String FRONT_LEFT_NAME = "frontLeft";
    public static final String FRONT_RIGHT_NAME = "frontRight";
    public static final String BACK_LEFT_NAME = "backLeft";
    public static final String BACK_RIGHT_NAME = "backRight";

    // Goal: a positive power makes every wheel spin so the robot moves FORWARD.
    // Your test showed the right-side wheels were running opposite to the left,
    // so the right side is flipped relative to the left here.
    public static final DcMotor.Direction FRONT_LEFT_DIRECTION = DcMotor.Direction.FORWARD;
    public static final DcMotor.Direction BACK_LEFT_DIRECTION = DcMotor.Direction.FORWARD;
    public static final DcMotor.Direction FRONT_RIGHT_DIRECTION = DcMotor.Direction.REVERSE;
    public static final DcMotor.Direction BACK_RIGHT_DIRECTION = DcMotor.Direction.REVERSE;
}
