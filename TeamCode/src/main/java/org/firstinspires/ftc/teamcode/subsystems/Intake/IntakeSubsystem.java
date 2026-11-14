package org.firstinspires.ftc.teamcode.subsystems.Intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.Intake.IntakeConstants.intakeState;

public class IntakeSubsystem {
    // fraction of full power used while the right trigger is held (tune this)
    private static final double SLOW_MULTIPLIER = 0.4;

    private DcMotor intakeMotor;
    private HardwareMap hardwareMap;
    private Gamepad gamepad1;
    private static IntakeSubsystem instance;
    private intakeState state = intakeState.STOP;
    private boolean slowed = false;

    private boolean previousLeftBumper = false;
    private boolean previousRightBumper = false;

    private IntakeSubsystem(HardwareMap hardwareMap, Gamepad gamepad1) {
        this.hardwareMap = hardwareMap;
        this.gamepad1 = gamepad1;
    }

    public static IntakeSubsystem getInstance(HardwareMap hardwareMap, Gamepad gamepad1) {
        if (instance == null) {
            instance = new IntakeSubsystem(hardwareMap, gamepad1);
        } else {
            // refresh references in case a new OpMode started
            instance.hardwareMap = hardwareMap;
            instance.gamepad1 = gamepad1;
        }
        return instance;
    }

    public void setState(intakeState state) {
        this.state = state;
        applyPower();
    }

    private void applyPower() {
        double power = state.getPower();
        intakeMotor.setPower(slowed ? power * SLOW_MULTIPLIER : power);
    }

    public void init() {
        intakeMotor = hardwareMap.get(DcMotor.class, IntakeConstants.INTAKE_NAME);
        slowed = false;
        setState(intakeState.STOP);
        previousLeftBumper = false;
        previousRightBumper = false;
    }

    public void loop() {
        boolean leftBumper = gamepad1.left_bumper;
        boolean rightBumper = gamepad1.right_bumper;
        boolean triggerHeld = gamepad1.right_trigger_pressed;

        boolean leftPressed = leftBumper && !previousLeftBumper;
        boolean rightPressed = rightBumper && !previousRightBumper;
        boolean slowChanged = triggerHeld != slowed;
        slowed = triggerHeld;

        // toggle behavior (unchanged)
        if (leftBumper && rightBumper) {
            setState(intakeState.STOP);
        } else if (rightPressed) {
            setState(state == intakeState.FORWARD ? intakeState.STOP : intakeState.FORWARD);
        } else if (leftPressed) {
            setState(state == intakeState.REVERSE ? intakeState.STOP : intakeState.REVERSE);
        } else if (slowChanged) {
            // trigger pressed or released with no bumper input: update the speed
            applyPower();
        }

        previousLeftBumper = leftBumper;
        previousRightBumper = rightBumper;
    }
}