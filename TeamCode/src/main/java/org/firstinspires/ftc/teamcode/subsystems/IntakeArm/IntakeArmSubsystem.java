package org.firstinspires.ftc.teamcode.subsystems.IntakeArm;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeArmSubsystem {
    private HardwareMap hardwareMap;
    private Gamepad gamepad1;
    private static IntakeArmSubsystem instance;
    private Servo intakeServo;
    private boolean flipflop;

    public IntakeArmSubsystem(HardwareMap hardwareMap, Gamepad gamepad1){
        this.hardwareMap = hardwareMap;
        this.gamepad1 = gamepad1;
    }

    public static IntakeArmSubsystem getInstance(HardwareMap hardwareMap, Gamepad gamepad1){
        if(instance == null){
            instance = new IntakeArmSubsystem(hardwareMap, gamepad1);
        }
        return instance;
    }
    public void init(){
        intakeServo = hardwareMap.get(Servo.class, "flowarm");

    }
    public void loop(){
        if(gamepad1.b) {
            intakeServo.setPosition(200);
        }else {
            intakeServo.setPosition(110);
        }
    }

}
