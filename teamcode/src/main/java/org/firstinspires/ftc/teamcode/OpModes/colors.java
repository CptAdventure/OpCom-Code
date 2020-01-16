package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.RobotComponents.Color;

@Autonomous(name="Get Brick Color Sensor", group = "OpMode")
public class colors extends OpMode {
    private Color color;
    private DistanceSensor distance;

    @Override
    public void init() {
        color = new Color(hardwareMap.colorSensor.get("brickColor"));
        distance = hardwareMap.get(DistanceSensor.class, "brickColor");
    }

    @Override
    public void loop() {
        telemetry.addLine("RBG");
        telemetry.addData("R",color.getRed());
        telemetry.addData("G",color.getGreen());
        telemetry.addData("B",color.getBlue());
        telemetry.addLine();
        telemetry.addLine("CYM");
        telemetry.addData("C",color.getCyan());
        telemetry.addData("Y",color.getYellow());
        telemetry.addData("M",color.getPurple());
        telemetry.addLine();
        telemetry.addLine("Other");
        telemetry.addData("H",color.getHue());
        telemetry.addData("A",color.getAlpha());
        telemetry.addLine();
        telemetry.addLine("Distance");
        telemetry.addData("IN",distance.getDistance(DistanceUnit.INCH));
        telemetry.addData("M",distance.getDistance(DistanceUnit.METER));
        telemetry.addData("CM",distance.getDistance(DistanceUnit.CM));
        telemetry.addData("MM",distance.getDistance(DistanceUnit.MM));
        telemetry.update();
    }
}
