package org.firstinspires.ftc.teamcode.AutonomousCommands;

import org.firstinspires.ftc.teamcode.RobotComponents.Drive;
import org.firstinspires.ftc.teamcode.RobotComponents.Vision;


// this command was created to simply turn rotate the robot until it can see one of the pictures
public class RotateToFindPicture implements ICommand {

    // to do this it needs the drive and the vision classes
    private Drive drive;
    private Vision vision;
    private double r;

    private boolean hasStarted = false;


    // this is called javaDoc https://www.tutorialspoint.com/java/java_documentation.htm
    /**
     *
      * @param drive
     * @param vision
     * @param r the speed/direction you want it to rotate to find the picture
     */
    public RotateToFindPicture(Drive drive, Vision vision, double r){
        this.drive = drive;
        this.vision = vision;
        this.r = r;
    }


    @Override
    public boolean Run() {
        // how this works is if it hasn't started it will start the vision tracking
        if (!hasStarted){
            vision.startTracking();
            hasStarted = true;
        }

        // if the vision can see the picture
        if (vision.getPosition() != null) {
            // stop turning
            drive.drive(0,0,0);
            // return true because the command is finished
            return true;
        }

        // otherwise tell the drive to rotate at the speed specified when setting up the command
        drive.drive(0, 0, r);
        return false;
    }
}
