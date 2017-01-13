package rp.assignments.individual.ex1;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * This class gives an example of how to run all the tests for Individual
 * Exercise 1.
 * 
 * @author Nick Hawes
 *
 */
public class TestHarness {

	public static void main(String[] args) {

		// This line loads and runs the tests you have been provided with
		Result result = JUnitCore
				.runClasses(rp.assignments.individual.ex1.Ex1Tests.class);

		// This prints out the results
		System.out.println(String.format("%d/%d tests successful",
				result.getRunCount() - result.getFailureCount(),
				result.getRunCount()));
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

	}

}
