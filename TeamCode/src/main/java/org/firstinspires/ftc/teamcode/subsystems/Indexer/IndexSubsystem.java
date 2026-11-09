package org.firstinspires.ftc.teamcode.subsystems.Indexer;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.Indexer.IndexConstants.IndexState;

public class IndexSubsystem {
    private Gamepad gamepad1;
    private HardwareMap hardwareMap;

    private IndexState state = IndexState.STOP;

    private CRServo indexerServo;
    private boolean previousX;
    private boolean previousY;

    private static IndexSubsystem instance;

    private IndexSubsystem(HardwareMap hardwareMap, Gamepad gamepad1) {
        this.hardwareMap = hardwareMap;
        this.gamepad1 = gamepad1;
    }

    public static IndexSubsystem getInstance(HardwareMap hardwareMap, Gamepad gamepad1) {
        if (instance == null) {
            instance = new IndexSubsystem(hardwareMap, gamepad1);
        } else {
            // refresh references in case a new OpMode started
            instance.hardwareMap = hardwareMap;
            instance.gamepad1 = gamepad1;
        }
        return instance;
    }

    public void setState(IndexState state) {
        this.state = state;
        indexerServo.setPower(state.getPower());
    }

    public void init() {
        indexerServo = hardwareMap.get(CRServo.class, IndexConstants.INDEXER_NAME);
        setState(IndexState.STOP);
        previousX = false;
        previousY = false;
    }

    public void loop() {
        boolean x = gamepad1.x;
        boolean y = gamepad1.y;
        boolean xPressed = x && !previousX;
        boolean yPressed = y && !previousY;

        if (x && y) {
            setState(IndexState.STOP);
        } else if (xPressed) {
            setState(IndexState.INTAKE);
        } else if (yPressed) {
            setState(IndexState.OUTTAKE);
        }

        previousX = x;
        previousY = y;
    }
}