package org.firstinspires.ftc.teamcode.AutonomousCommands;

/*
this is following a command pattern https://en.wikipedia.org/wiki/Command_pattern
this allows you to create a class that represents an action you want the robot to do
this is useful for the autonomous mode as you can create commands and put them in
list to be run sequentially and it makes it easy to quickly make a list of commands
that you want to run
 */

public interface ICommand {

    // this is the method that all the commands will need to have
    // this command is meant to be run in a loop and will return true when the command is finished
    boolean Run();
}
