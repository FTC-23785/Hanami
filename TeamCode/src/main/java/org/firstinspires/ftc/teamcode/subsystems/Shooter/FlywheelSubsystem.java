package org.firstinspires.ftc.teamcode.subsystems.Shooter;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class FlywheelSubsystem {

    private final DcMotorEx flywheelMotor;
    private double targetVelocityTPS = 0.0;

    public FlywheelSubsystem(HardwareMap hardwareMap) {
        flywheelMotor = hardwareMap.get(DcMotorEx.class, FlywheelConstants.MOTOR_NAME);

        flywheelMotor.setPower(0.0);
        flywheelMotor.setDirection(FlywheelConstants.MOTOR_DIRECTION);
        flywheelMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        flywheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void runForward() {
        setVelocity(FlywheelConstants.SHOOT_VELOCITY_TPS);
    }

    public void setVelocity(double ticksPerSecond) {
        if (Double.isNaN(ticksPerSecond) || Double.isInfinite(ticksPerSecond)) {
            throw new IllegalArgumentException("Flywheel velocity must be finite.");
        }

        if (ticksPerSecond == 0.0) {
            stop();
            return;
        }

        targetVelocityTPS = ticksPerSecond;
        flywheelMotor.setVelocity(targetVelocityTPS);
    }

    public void stop() {
        targetVelocityTPS = 0.0;
        flywheelMotor.setPower(0.0);
    }

    public double getVelocity() {
        return flywheelMotor.getVelocity();
    }

    public double getTargetVelocity() {
        return targetVelocityTPS;
    }

    public boolean isAtSpeed() {
        return targetVelocityTPS > 0.0
                && Math.abs(getVelocity() - targetVelocityTPS)
                <= FlywheelConstants.VELOCITY_TOLERANCE_TPS;
    }
}
