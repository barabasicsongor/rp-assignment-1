package rp.assignments.individual.ex1;

import lejos.util.Delay;
import rp.robotics.DifferentialDriveRobot;
import rp.systems.StoppableRunnable;

/**
 * 
 * A placeholder to show you how you could start writing a controller for the
 * first part of the first individual assignment (creating a controller which
 * drives in a pentagon). Note that you don't have to follow this structure for
 * your controller as there are more elegant and efficient (at least in terms of
 * numbers of lines of code) in which you can implement the different shape
 * controllers.
 * 
 * @author Nick Hawes
 *
 */
public class PentagonController implements StoppableRunnable {

	private static final float PI = 180.0f;
	private static final float INTERNAL_ANGLE = 108.0f;
	private static final int NUMBER_OF_SIDES = 5;
	
	private final DifferentialDriveRobot robot;
	private final float sideLength;
	private boolean robot_run;
	
	public PentagonController(DifferentialDriveRobot robot, float sideLength) {
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
