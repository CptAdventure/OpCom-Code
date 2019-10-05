package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.AutonomousCommands.DeployRover;
import org.firstinspires.ftc.teamcode.AutonomousCommands.EndCommand;
import org.firstinspires.ftc.teamcode.AutonomousCommands.ICommand;
import org.firstinspires.ftc.teamcode.AutonomousCommands.RotateToFindPicture;
import org.firstinspires.ftc.teamcode.AutonomousCommands.TimedDriveCommand;
import org.firstinspires.ftc.teamcode.RobotComponents.Drive;
import org.firstinspires.ftc.teamcode.RobotComponents.RobotLifter;
import org.firstinspires.ftc.teamcode.RobotComponents.Vision;

import java.util.ArrayList;
@Autonomous(name = "Auto Test Op")
public class AutoTest extends OpMode {

    /*
    This shows how the command pattern has been set up to run the commands
     */


    // these two variables are used to store the commands to be run
    private ArrayList<ICommand> listOfCommands = new ArrayList();
    private ICommand commandToRun;


    // here you will need all the classes created to control the robot
    private Drive drive;
    private Vision vision;
    private RobotLifter robotLifter;



    @Override
    public void init() {

        // create all the class objects
        vision = new Vision(hardwareMap);
        drive = new Drive(hardwareMap);
        robotLifter = new RobotLifter(hardwareMap);



        // this is how you set up the list of commands to run
        // make a list of commands, the list is ordered
        listOfCommands.add(new DeployRover(robotLifter)); //first command
        listOfCommands.add(new TimedDriveCommand(drive, 1, 0, 0,1000)); // second command
        listOfCommands.add(new RotateToFindPicture(drive, vision, 0.2));
        listOfCommands.add(new TimedDriveCommand(drive, 0, 0, 0.5, 500));

        listOfCommands.add(new TimedDriveCommand(drive, 0, 1, 0, 500));

        // this command is simply a do nothing forever command
        // always have this at the end,
        listOfCommands.add(new EndCommand());



        // pop the first command
        commandToRun = listOfCommands.remove(0);
    }





    @Override
    public void loop() {

        // this just calls run on the current command until the command returns true
        // ie that the command is finished
        if (commandToRun.Run()){
            // when the current command is finished it gets the next command
            commandToRun = listOfCommands.remove(0);
            // this is why the endCommand needs to be the last one in the list
            // it will always return false, without it when the last command finishes it will try
            // to get the next command from a now empty list and cause an error
            // !!!!!bonus points if you make it that we don't need the endCommand!!!!!
        }
    }
}
