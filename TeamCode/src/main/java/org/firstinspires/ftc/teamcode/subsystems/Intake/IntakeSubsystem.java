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
        intakeMotor = hardwareMap.get(DcMotor.class, IntakeConstants.MOTOR_NAME);
        intakeMotor.setPower(0.0);
        intakeMotor.setDirection(IntakeConstants.MOTOR_DIRECTION);
        intakeMotor.setZeroPowerBehavior(IntakeConstants.ZERO_POWER_BEHAVIOR);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void runForward() {
        intakeMotor.setPower(IntakeConstants.FORWARD_POWER);
        state = State.FORWARD;
    }

    public void runReverse() {
        intakeMotor.setPower(IntakeConstants.REVERSE_POWER);
        state = State.REVERSE;
    }

    public void stop() {
        intakeMotor.setPower(0.0);
        state = State.STOPPED;
    }

    public void toggleForward() {
        if (state == State.FORWARD) {
            stop();
        } else {
            runForward();
        }
    }

    public void toggleReverse() {
        if (state == State.REVERSE) {
            stop();
        } else {
            runReverse();
        }
    }
}
