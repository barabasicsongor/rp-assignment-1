package rp.assignments.individual.ex1;

import java.util.ArrayList;

import lejos.robotics.RangeFinder;
import rp.config.RangeFinderDescription;
import rp.robotics.EventBasedTouchSensor;
import rp.robotics.TouchSensorEvent;
import rp.robotics.TouchSensorListener;
import rp.systems.StoppableRunnable;

public class VirtualBumperController implements EventBasedTouchSensor, StoppableRunnable {

	private final RangeFinderDescription desc;
	private final RangeFinder ranger;
	private final Float touchRange;

	private ArrayList<TouchSensorListener> listeners;
	private boolean is_pressed, is_running;
	private float lastValue;

	public VirtualBumperController(RangeFinderDescription desc, RangeFinder ranger, Float touchRange) {
		this.desc = desc;
		this.ranger = ranger;
		this.touchRange = touchRange;
		this.is_pressed = false;
		this.is_running = true;
		this.lastValue = desc.getMaxRange();
		listeners = new ArrayList<TouchSensorListener>();
		(new Thread(this)).start();
	}

	@Override
	public void run() {
		while (is_running) {

			if (ranger.getRange() <= touchRange + desc.getNoise() && !is_pressed) {
				is_pressed = true;
				for (TouchSensorListener l : listeners) {
					l.sensorPressed(new TouchSensorEvent(lastValue, ranger.getRange()));
				}
			} else if (is_pressed && ranger.getRange() > touchRange + desc.getNoise()) {
				is_pressed = false;
				for (TouchSensorListener l : listeners) {
					l.sensorReleased(new TouchSensorEvent(lastValue, ranger.getRange()));
					l.sensorBumped(new TouchSensorEvent(lastValue, ranger.getRange()));
				}
			}
			lastValue = ranger.getRange();

		}
	}

	@Override
	public void stop() {
		is_running = false;
	}

	@Override
	public boolean isPressed() {
		return this.is_pressed;
	}

	@Override
	public void addTouchSensorListener(TouchSensorListener _listener) {
		this.listeners.add(_listener);
	}

}