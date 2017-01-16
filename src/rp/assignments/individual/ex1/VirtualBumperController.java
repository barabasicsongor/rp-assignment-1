package rp.assignments.individual.ex1;

import lejos.nxt.TouchSensor;
import lejos.robotics.RangeFinder;
import rp.config.RangeFinderDescription;
import rp.robotics.EventBasedTouchSensor;
import rp.robotics.TouchSensorEvent;
import rp.robotics.TouchSensorListener;

public class VirtualBumperController implements EventBasedTouchSensor {

	private final RangeFinderDescription desc;
	private final RangeFinder ranger;
	private final Float touchRange;
	
	private boolean is_pressed, is_bumped;
	private float lastValue;
	
	public VirtualBumperController(RangeFinderDescription desc, RangeFinder ranger, Float touchRange) {
		this.desc = desc;
		this.ranger = ranger;
		this.touchRange = touchRange;
		this.is_pressed = false;
		this.is_bumped = false;
		this.lastValue = desc.getMaxRange();
	}

	@Override
	public boolean isPressed() {
		return this.is_pressed;
	}

	@Override
	public void addTouchSensorListener(TouchSensorListener _listener) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						
						if(ranger.getRange() <= touchRange + desc.getNoise() && !is_pressed) {
							is_pressed = true;
							_listener.sensorPressed(new TouchSensorEvent(lastValue, ranger.getRange()));
						} else if(is_pressed && ranger.getRange() > touchRange + desc.getNoise()) {
							is_pressed = false;
							_listener.sensorReleased(new TouchSensorEvent(lastValue, ranger.getRange()));
							_listener.sensorBumped(new TouchSensorEvent(lastValue, ranger.getRange()));
						}
						
						lastValue = ranger.getRange();
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}