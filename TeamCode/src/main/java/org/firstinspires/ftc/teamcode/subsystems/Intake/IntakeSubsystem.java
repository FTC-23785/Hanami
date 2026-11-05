
package org.firstinspires.ftc.teamcode.subsystems.Intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeSubsystem {

    private enum State {
        STOPPED,
        FORWARD,
        REVERSE
    }

    private final DcMotor intakeMotor;

    private State state = State.STOPPED;

    public IntakeSubsystem(HardwareMap hardwareMap) {

        intakeMotor = hardwareMap.get(
                DcMotor.class,
                IntakeConstants.MOTOR_NAME
        );

        intakeMotor.setPower(0.0);

        intakeMotor.setDirection(
                IntakeConstants.MOTOR_DIRECTION);

        intakeMotor.setZeroPowerBehavior(
                IntakeConstants.ZERO_POWER_BEHAVIOR);

        intakeMotor.setMode(
                IntakeConstants.RUN_MODE);
    }

    // Run intake forward
    public void runForward() {

        intakeMotor.setPower(
                IntakeConstants.FORWARD_POWER);

        state = State.FORWARD;
    }

    // Run intake backward
    public void runReverse() {

        intakeMotor.setPower(
                IntakeConstants.REVERSE_POWER);

        state = State.REVERSE;
    }

    // Stop intake
    public void stop() {

        intakeMotor.setPower(0.0);

        state = State.STOPPED;
    }

    // Press LB once to run, again to stop
    public void toggleForward() {

        if (state == State.FORWARD) {
            stop();
        } else {
            runForward();
        }
    }

    // Press RB once to reverse, again to stop
    public void toggleReverse() {

        if (state == State.REVERSE) {
            stop();
        } else {
            runReverse();
        }
    }

    public String getStateName() {
        return state.name();
    }

    public double getCommandedPower() {
        return intakeMotor.getPower();
    }
}
