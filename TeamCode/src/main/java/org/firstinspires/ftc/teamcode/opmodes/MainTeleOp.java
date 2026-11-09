package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Drive.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Indexer.IndexSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.Shooter.FlywheelSubsystem;

@TeleOp(name = "Testing")
public class MainTeleOp extends OpMode {

    DriveSubsystem drive;
    IndexSubsystem index;
    IntakeSubsystem intake;
    FlywheelSubsystem flywheel;

    @Override
    public void init() {
        index = IndexSubsystem.getInstance(hardwareMap, gamepad1);
        drive = DriveSubsystem.getInstance(hardwareMap, gamepad1);
        intake = IntakeSubsystem.getInstance(hardwareMap, gamepad1);
        flywheel = FlywheelSubsystem.getInstance(hardwareMap, gamepad1);

        drive.init();
        intake.init();
        index.init();
        flywheel.init();
    }

    @Override
    public void loop() {
        // Each subsystem reads the gamepad itself
        drive.loop();
        intake.loop();   // bumpers: right = forward toggle, left = reverse toggle
        index.loop();
        flywheel.idle();
    }
}