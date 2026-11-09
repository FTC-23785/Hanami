package org.firstinspires.ftc.teamcode.subsystems.Indexer;

public class IndexConstants {
    public static final String INDEXER_NAME = "indexerServo";

    public enum IndexState {
        INTAKE(1.0),
        OUTTAKE(-1.0),
        STOP(0.0);

        private final double power;

        IndexState(double power) {
            this.power = power;
        }

        public double getPower() {
            return power;
        }
    }
}