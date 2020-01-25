package org.firstinspires.ftc.teamcode.AutonomousCommands;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.RobotComponents.Drive;

import static java.lang.Double.isNaN;

public class MoveToWall implements ICommand {
    private Drive drive;
    private DistanceSensor color;
    private boolean direction;
    private int ACTIVATED = 100;

    public MoveToWall(Drive drive, HardwareMap hardwareMap, boolean direction, int dist){
        this.drive = drive;
        this.color = hardwareMap.get(DistanceSensor.class,"brickColor");
        this.direction = direction;
        this.ACTIVATED = dist;
    }

    @Override
    public boolean Run() {
        if(Math.round(color.getDistance(DistanceUnit.INCH))<ACTIVATED&&!isNaN(color.getDistance(DistanceUnit.INCH))) {
            drive.drive(0,0,0);
            return true;
        }
        drive.drive(0,(direction?0.5:-0.5),0);
        return false;
    }
}
