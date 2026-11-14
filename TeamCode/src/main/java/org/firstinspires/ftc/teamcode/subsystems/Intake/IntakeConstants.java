package org.firstinspires.ftc.teamcode.subsystems.Intake;

public class IntakeConstants {
    public static final String INTAKE_NAME = "intake";

    public enum intakeState {
        FORWARD(0.5),
        REVERSE(-1),
        STOP(0.0),

        SLOWED_FORWARD(-0.5);       private final double power;

        intakeState(double power) {
            this.power = power;
        }

        public double getPower() {
            return power;
        }
    }
}