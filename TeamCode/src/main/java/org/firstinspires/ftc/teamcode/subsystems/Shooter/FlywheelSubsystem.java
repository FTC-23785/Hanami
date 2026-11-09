
package org.firstinspires.ftc.teamcode.subsystems.Shooter;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class FlywheelSubsystem {

    private DcMotorEx flywheelMotor;

    private final Gamepad gamepad1;

    private final HardwareMap hardwareMap;

    private static FlywheelSubsystem instance;


    private double targetVelocityTPS = 0.0;

    private FlywheelSubsystem(HardwareMap hardwareMap, Gamepad gamepad1){
        this.hardwareMap = hardwareMap;
        this.gamepad1 = gamepad1;

    }

    public void init() {
        flywheelMotor = hardwareMap.get(
                DcMotorEx.class,
                FlywheelConstants.MOTOR_NAME
        );

        flywheelMotor.setPower(0.0);

        flywheelMotor.setDirection(
                FlywheelConstants.MOTOR_DIRECTION);

        flywheelMotor.setZeroPowerBehavior(
                FlywheelConstants.ZERO_POWER_BEHAVIOR);

        flywheelMotor.setMode(
                FlywheelConstants.RUN_MODE);
    }



//    public FlywheelSubsystem(HardwareMap hardwareMap) {
//
//        flywheelMotor = hardwareMap.get(
//                DcMotorEx.class,
//                FlywheelConstants.MOTOR_NAME
//        );
//
//        flywheelMotor.setPower(0.0);
//
//        flywheelMotor.setDirection(
//                FlywheelConstants.MOTOR_DIRECTION);
//
//        flywheelMotor.setZeroPowerBehavior(
//                FlywheelConstants.ZERO_POWER_BEHAVIOR);
//
//        flywheelMotor.setMode(
//                FlywheelConstants.RUN_MODE);
//    }


    public static FlywheelSubsystem getInstance(HardwareMap hardwareMap, Gamepad gamepad1){
        if(instance == null){
            instance = new FlywheelSubsystem(hardwareMap, gamepad1);
        }
        return instance;
    }
    // Set a specific flywheel velocity
    public void setVelocity(double ticksPerSecond) {

        if (!Double.isFinite(ticksPerSecond)) {
            throw new IllegalArgumentException(
                    "Flywheel velocity must be finite.");
        }

        if (ticksPerSecond == 0.0) {
            stop();
            return;
        }

        targetVelocityTPS = ticksPerSecond;

        flywheelMotor.setVelocity(targetVelocityTPS);
    }

    // Stop flywheel
    public void stop() {

        targetVelocityTPS = 0.0;

        flywheelMotor.setPower(0.0);
    }

    // Actual motor velocity
    public double getVelocity() {

        return flywheelMotor.getVelocity();
    }

    public double getTargetVelocity() {

        return targetVelocityTPS;
    }

    public boolean isAtSpeed() {

        return Math.abs(targetVelocityTPS) > 0.0
                && Math.abs(
                getVelocity() - targetVelocityTPS)
                <= FlywheelConstants.VELOCITY_TOLERANCE_TPS;
    }

    public void idle(){
        setVelocity(FlywheelConstants.SHOOT_VELOCITY_TPS);
    }
}
