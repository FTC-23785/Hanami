package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drive.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Shooter.FlywheelSubsystem;

@TeleOp(name = "MainTeleOp", group = "TeleOp")
public class MainTeleOp extends LinearOpMode {

    private static final double FLYWHEEL_TRIGGER_THRESHOLD = 0.1;

    @Override
    public void runOpMode() {
        DriveSubsystem drive = new DriveSubsystem(hardwareMap);
        IntakeSubsystem intake = new IntakeSubsystem(hardwareMap);
        FlywheelSubsystem flywheel = new FlywheelSubsystem(hardwareMap);

        boolean previousLeftBumper = false;
        boolean previousRightBumper = false;

        telemetry.addLine("Ready");
        telemetry.addLine("Left bumper: toggle intake forward");
        telemetry.addLine("Right bumper: toggle intake reverse");
        telemetry.addLine("Right trigger: hold to run flywheel");
        showIntakeStatus(intake);

        try {
            waitForStart();

            if (isStopRequested()) {
                return;
            }

            while (opModeIsActive()) {
                // Sample once so holding a bumper counts as a single press.
                boolean leftBumper = gamepad1.left_bumper;
                boolean rightBumper = gamepad1.right_bumper;
                boolean leftBumperPressed = leftBumper && !previousLeftBumper;
                boolean rightBumperPressed = rightBumper && !previousRightBumper;

                drive.drive(gamepad1);

                if (leftBumper && rightBumper) {
                    intake.stop();
                } else if (leftBumperPressed) {
                    intake.toggleForward();
                } else if (rightBumperPressed) {
                    intake.toggleReverse();
                }

                previousLeftBumper = leftBumper;
                previousRightBumper = rightBumper;

                if (gamepad1.right_trigger > FLYWHEEL_TRIGGER_THRESHOLD) {
                    flywheel.runForward();
                } else {
                    flywheel.stop();
                }

                // Update only on a bumper press, rather than every loop.
                if (leftBumperPressed || rightBumperPressed) {
                    showIntakeStatus(intake);
                }

                idle();
            }
        } finally {
            drive.stop();
            intake.stop();
            flywheel.stop();
        }
    }

    private void showIntakeStatus(IntakeSubsystem intake) {
        telemetry.addData("Intake mode", intake.getStateName());
        telemetry.addData("Intake commanded power", intake.getCommandedPower());
        telemetry.update();
    }
}
