package org.firstinspires.ftc.teamcode.subsystems.Shooter;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public final class FlywheelConstants {

    public static final String MOTOR_NAME = "flywheel";

    public static final DcMotorSimple.Direction MOTOR_DIRECTION =
            DcMotorSimple.Direction.FORWARD;

    public static final double SHOOT_VELOCITY_TPS = 1160.0;

    public static final double VELOCITY_TOLERANCE_TPS = 20.0;

    private FlywheelConstants() {}
}