package org.firstinspires.ftc.teamcode.subsystems.Vision;


import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class VisionSubsystem {
    Limelight3A limelight;
    HardwareMap hardwareMap;

    static VisionSubsystem instance;
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100); // This sets how often we ask Limelight for data (100 times per second)
        limelight.start(); // This tells Limelight to start looking!
    }

    public VisionSubsystem(HardwareMap hardwareMap){
        this.hardwareMap = hardwareMap;
    }

    public static VisionSubsystem getInstance(HardwareMap hardwareMap){
        if(instance == null) {
            instance = new VisionSubsystem(hardwareMap);
        }
        return instance;
    }
    public void loop(){

    }

}
