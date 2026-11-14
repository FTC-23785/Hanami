package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.Drive.DriveConstants;

@Autonomous(name = "ForwardAuto")
public class ForwardAuto extends LinearOpMode {
    private static final double POWER = 0.4;
    private static final double DRIVE_TIME_SECONDS = 1.0;

    @Override
    public void runOpMode() {
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, DriveConstants.FRONT_LEFT_NAME);
        DcMotor frontRight = hardwareMap.get(DcMotor.class, DriveConstants.FRONT_RIGHT_NAME);
        DcMotor backLeft = hardwareMap.get(DcMotor.class, DriveConstants.BACK_LEFT_NAME);
        DcMotor backRight = hardwareMap.get(DcMotor.class, DriveConstants.BACK_RIGHT_NAME);

        frontLeft.setDirection(DriveConstants.FRONT_LEFT_DIRECTION);
        frontRight.setDirection(DriveConstants.FRONT_RIGHT_DIRECTION);
        backLeft.setDirection(DriveConstants.BACK_LEFT_DIRECTION);
        backRight.setDirection(DriveConstants.BACK_RIGHT_DIRECTION);

        waitForStart();

        frontLeft.setPower(POWER);
        frontRight.setPower(POWER);
        backLeft.setPower(POWER);
        backRight.setPower(POWER);

        sleep((long) (DRIVE_TIME_SECONDS * 1000));

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}