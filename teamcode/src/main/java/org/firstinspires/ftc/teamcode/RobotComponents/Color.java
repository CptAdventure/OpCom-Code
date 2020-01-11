package org.firstinspires.ftc.teamcode.RobotComponents;

import com.qualcomm.robotcore.hardware.ColorSensor;


public class Color {
    private ColorSensor color;
    public Color (ColorSensor color) {
        this.color = color;
    }

    public int getRed ()    { return color.red();   }

    public int getBlue ()   { return color.blue();  }

    public int getGreen ()  { return color.green(); }

    public int getAlpha ()  { return color.alpha(); }

    public int getHue ()    { return color.argb();  }

    public int getYellow () { return (color.green()+color.red())/2;  }

    public int getPurple () { return (color.blue()+color.red())/2;   }

    public int getCyan ()   { return (color.green()+color.blue())/2; }
}
