package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drive.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Shooter.FlywheelSubsystem;

@TeleOp(name = "MainTeleOp", group = "TeleOp")
public class MainTeleOp extends LinearOpMode {

    private static final double FLYWHEEL_TRIGGER_THRESHOLD = 0.25;

    @Override
    public void runOpMode() {
        DriveSubsystem drive = new DriveSubsystem(hardwareMap);
        IntakeSubsystem intake = new IntakeSubsystem(hardwareMap);
        FlywheelSubsystem flywheel = new FlywheelSubsystem(hardwareMap);

        boolean previousLeftBumper = false;
        boolean previousRightBumper = false;

        telemetry.addLine("Ready - using gamepad 1");
        telemetry.addLine("Left stick: drive/strafe | Right stick X: turn");
        telemetry.addLine("LB: toggle intake forward | RB: toggle intake reverse");
        telemetry.addLine("Both bumpers: stop intake | Hold RT: flywheel");
        telemetry.update();

        try {
            waitForStart();

            if (isStopRequested()) {
                return;
            }

            while (opModeIsActive()) {
                drive.drive(gamepad1);

                // A held bumper counts as one press until it is released.
                boolean leftBumper = gamepad1.left_bumper;
                boolean rightBumper = gamepad1.right_bumper;
                boolean leftBumperPressed = leftBumper && !previousLeftBumper;
                boolean rightBumperPressed = rightBumper && !previousRightBumper;

                if (leftBumper && rightBumper) {
                    intake.stop();
                } else if (leftBumperPressed) {
                    intake.toggleForward();
                } else if (rightBumperPressed) {
                    intake.toggleReverse();
                }

                previousLeftBumper = leftBumper;
                previousRightBumper = rightBumper;

                // Both bumpers belong to the intake; hold RT to run the flywheel.
                if (gamepad1.right_trigger > FLYWHEEL_TRIGGER_THRESHOLD) {
                    flywheel.runForward();
                } else {
                    flywheel.stop();
                }

                idle();
            }
        } finally {
            drive.stop();
            intake.stop();
            flywheel.stop();
        }
    }
}
