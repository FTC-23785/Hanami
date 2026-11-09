package org.firstinspires.ftc.teamcode.subsystems.Intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.Intake.IntakeConstants.intakeState;

public class IntakeSubsystem {
    private DcMotor intakeMotor;
    private HardwareMap hardwareMap;
    private Gamepad gamepad1;
    private static IntakeSubsystem instance;
    private intakeState state = intakeState.STOP;

    private boolean previousLeftBumper;
    private boolean previousRightBumper;

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
        intakeMotor.setPower(state.getPower());
    }

    public void init() {
        intakeMotor = hardwareMap.get(DcMotor.class, IntakeConstants.INTAKE_NAME);
        setState(intakeState.STOP);
        previousLeftBumper = false;
        previousRightBumper = false;
    }

    public void loop() {
        boolean leftBumper = gamepad1.left_bumper;
        boolean rightBumper = gamepad1.right_bumper;
        boolean leftPressed = leftBumper && !previousLeftBumper;
        boolean rightPressed = rightBumper && !previousRightBumper;

        if (leftBumper && rightBumper) {
            setState(intakeState.STOP);
        } else if (rightPressed) {
            // right bumper = forward toggle
            setState(state == intakeState.FORWARD ? intakeState.STOP : intakeState.FORWARD);
        } else if (leftPressed) {
            // left bumper = reverse toggle
            setState(state == intakeState.REVERSE ? intakeState.STOP : intakeState.REVERSE);
        }

        previousLeftBumper = leftBumper;
        previousRightBumper = rightBumper;
    }
}
