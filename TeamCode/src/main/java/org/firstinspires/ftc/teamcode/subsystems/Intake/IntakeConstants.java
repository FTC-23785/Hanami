package org.firstinspires.ftc.teamcode.subsystems.Intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public final class IntakeConstants {

    public static final String MOTOR_NAME = "intake";

    public static final double FORWARD_POWER = 1.0;
    public static final double REVERSE_POWER = -1.0;

    public static final DcMotorSimple.Direction MOTOR_DIRECTION =
            DcMotorSimple.Direction.FORWARD;

    public static final DcMotor.RunMode RUN_MODE = DcMotor.RunMode.RUN_WITHOUT_ENCODER;
    public static final DcMotor.ZeroPowerBehavior ZERO_POWER_BEHAVIOR =
            DcMotor.ZeroPowerBehavior.BRAKE;

    private IntakeConstants() {}
}
