package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subsystems.Drive.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Shooter.FlywheelSubsystem;

@TeleOp(name = "MainTeleOp", group = "TeleOp")
public class MainTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        DriveSubsystem drive = new DriveSubsystem(hardwareMap);
        FlywheelSubsystem flywheel = new FlywheelSubsystem(hardwareMap);
        IntakeSubsystem intake = new IntakeSubsystem(hardwareMap);

        boolean previousLeftBumper = false;
        boolean previousRightBumper = false;

        telemetry.addLine("Ready");
        telemetry.update();

        try {
            waitForStart();

            if (isStopRequested()) {
                return;
            }

            while (opModeIsActive()) {
                drive.drive(
                        -gamepad1.left_stick_y,
                        gamepad1.left_stick_x,
                        gamepad1.right_stick_x);

                boolean leftBumper = gamepad1.left_bumper;
                boolean rightBumper = gamepad1.right_bumper;

                boolean leftBumperPressed = leftBumper && !previousLeftBumper;
                boolean rightBumperPressed = rightBumper && !previousRightBumper;

                if (leftBumper && rightBumper) {
                    intake.stop();
                } else if (leftBumperPressed) {
                    intake.toggleReverse();
                } else if (rightBumperPressed) {
                    intake.toggleForward();
                }

                previousLeftBumper = leftBumper;
                previousRightBumper = rightBumper;

                if (gamepad1.right_trigger > 0) {
                    flywheel.runForward();
                } else {
                    flywheel.stop();
                }

                idle();
            }
        } finally {
            drive.stopDrive();
            flywheel.stop();
            intake.stop();
        }
    }
}
