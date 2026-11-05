package org.firstinspires.ftc.teamcode.subsystems.Shooter;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public final class FlywheelConstants {

    public static final String MOTOR_NAME = "flywheel";

    // Changed from FORWARD to REVERSE. Keep the shooting velocity positive.
    public static final DcMotorSimple.Direction MOTOR_DIRECTION =
            DcMotorSimple.Direction.REVERSE;

    public static final DcMotor.RunMode RUN_MODE = DcMotor.RunMode.RUN_USING_ENCODER;
    public static final DcMotor.ZeroPowerBehavior ZERO_POWER_BEHAVIOR =
            DcMotor.ZeroPowerBehavior.FLOAT;

    public static final double SHOOT_VELOCITY_TPS = 1160.0;
    public static final double VELOCITY_TOLERANCE_TPS = 20.0;

    private FlywheelConstants() {}
}
