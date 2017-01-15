package rp.assignments.individual.ex1;

import rp.robotics.DifferentialDriveRobot;
import rp.systems.StoppableRunnable;

public class OctagonController implements StoppableRunnable {

	private static final float PI = 180.0f;
	private static final float INTERNAL_ANGLE = 135.0f;
	
	private final DifferentialDriveRobot robot;
	private final float sideLength;
	private boolean robot_run;
	
	public OctagonController(DifferentialDriveRobot robot, float sideLength) {
		this.robot = robot;
		this.sideLength = sideLength;
		this.robot_run = true;
	}

	@Override
	public void run() {
		while(this.robot_run) {
			try {
				robot.getDifferentialPilot().travel(sideLength);
				robot.getDifferentialPilot().rotate(PI - INTERNAL_ANGLE);
				
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.robot.getDifferentialPilot().stop();
	}

	@Override
	public void stop() {
		this.robot_run = false;
	}

}
