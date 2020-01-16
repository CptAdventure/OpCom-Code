package org.firstinspires.ftc.teamcode.AutonomousCommands;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.RobotComponents.Drive;

public class MoveToWall implements ICommand {
    private Drive drive;
    private DistanceSensor color;
    private boolean direction;
    private final int ACTIVATED = 7;

    public MoveToWall(Drive drive, HardwareMap hardwareMap, boolean direction){
        this.drive = drive;
        this.color = hardwareMap.get(DistanceSensor.class,"brickColor");
        this.direction = direction;
    }

    @Override
    public boolean Run() {
        if(Math.round(color.getDistance(DistanceUnit.INCH))>=ACTIVATED) {
            drive.drive(0,0,0);
            return true;
        }
        drive.drive(0,(direction?1:-1)*0.25,0);
        return false;
    }
}
