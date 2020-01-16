package org.firstinspires.ftc.teamcode.AutonomousCommands;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.RobotComponents.Drive;

public class CrossToBrickEnd implements ICommand {
    private Drive drive;
    private DistanceSensor color;
    private boolean direction;
    private final int ACTIVATED = 12;

    public CrossToBrickEnd(Drive drive, HardwareMap hardwareMap, boolean direction){
        this.drive = drive;
        this.color = hardwareMap.get(DistanceSensor.class,"brickColor");
        this.direction = direction;
    }

    @Override
    public boolean Run() {
        if(color.getDistance(DistanceUnit.INCH)<ACTIVATED) {
            drive.drive(0,0,0);
            return true;
        }
        drive.drive((direction?1:-1)*0.25,0,0);
        return false;
    }
}
