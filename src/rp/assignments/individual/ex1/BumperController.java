package rp.assignments.individual.ex1;

import lejos.robotics.navigation.Pose;
import rp.robotics.DifferentialDriveRobot;
import rp.robotics.TouchSensorEvent;
import rp.systems.ControllerWithTouchSensor;

public class BumperController implements ControllerWithTouchSensor {

	private final DifferentialDriveRobot robot;
	private boolean robot_run;
	private boolean is_pressed;

	public BumperController(DifferentialDriveRobot robot) {
		this.robot = robot;
		this.robot_run = true;
		this.is_pressed = false;
	}

	@Override
	public void stop() {
		robot_run = false;
	}

	@Override
	public void run() {
		while (this.robot_run) {

			try {

				if (this.is_pressed) {
					this.robot.getDifferentialPilot().stop();
					this.robot.getDifferentialPilot().travel(-0.1);
					
//					if(this.robot.getPose().getHeading() == 180.0) {
//						this.robot.setPose(new Pose(this.robot.getPose().getX(), this.robot.getPose().getY(), 0.0f));
//					} else {
//						this.robot.setPose(new Pose(this.robot.getPose().getX(), this.robot.getPose().getY(), 180.0f));
//					}
					
					this.robot.getDifferentialPilot().rotate(180.00);
					this.robot.getDifferentialPilot().stop();
					
					this.is_pressed = false;
				}
				
				this.robot.getDifferentialPilot().forward();

				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.robot.getDifferentialPilot().stop();
	}

	@Override
	public void sensorPressed(TouchSensorEvent _e) {
		// TODO Auto-generated method stub
		this.is_pressed = true;
	}

	@Override
	public void sensorReleased(TouchSensorEvent _e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void sensorBumped(TouchSensorEvent _e) {
		// TODO Auto-generated method stub
	}

}
