package rp.assignments.individual.ex1;


import lejos.robotics.RangeFinder;
import rp.robotics.DifferentialDriveRobot;
import rp.robotics.EventBasedTouchSensor;
import rp.systems.ControllerWithTouchSensor;
import rp.systems.StoppableRunnable;

/**
 * In this class you must edit the "create*" methods to provide instances of
 * your controller objects in order to pass the automated tests. For example, if
 * you have a class called BumperCar which implements
 * {@code ControllerWithTouchSensor} for the bumper controller part of the
 * assignment, you would edit the createBumperController method as follows:
 * 
 * <pre>
 * {@code
 * public static ControllerWithTouchSensor createBumperController(
 * 		DifferentialDriveRobot _robot) {
 * 	return new BumperCar(_robot);
 * }
 * }
 * </pre>
 * 
 *
 */
public class SolutionFactory {

	public static StoppableRunnable createPentagonController(
			DifferentialDriveRobot _robot, Float _sideLength) {
		return new PentagonController(_robot, _sideLength);
	}

	public static StoppableRunnable createOctagonController(
			DifferentialDriveRobot _robot, Float _sideLength) {
		return null;
	}

	public static StoppableRunnable createNonagonController(
			DifferentialDriveRobot _robot, Float _sideLength) {
		return null;
	}

}
