package org.firstinspires.ftc.teamcode.subsystems.Intake;

public class IntakeConstants {
    public static final String INTAKE_NAME = "intake";

    public enum intakeState {
        FORWARD(1.0),
        REVERSE(-1.0),
        STOP(0.0);

        private final double power;

        intakeState(double power) {
            this.power = power;
        }

        public double getPower() {
            return power;
        }
    }
}