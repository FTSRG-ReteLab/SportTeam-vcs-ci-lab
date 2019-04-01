package hu.bme.mit.train.controller;

//ez a train controller - commentelve
import hu.bme.mit.train.interfaces.TrainController;

import java.util.Timer;
import java.util.TimerTask;

public class TrainControllerImpl implements TrainController {

	private int conflict = 0;
	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;

	Timer timer = new Timer();

	public TrainControllerImpl(){
		        timer.schedule(new TimerTask() {
					@Override
					public void run() {
						   followSpeed();
					}
				}, 500 );
	}



	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}


	
	/*public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 5;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed = 5;
            } else {
		        referenceSpeed = 5;
            }
		}

		enforceSpeedLimit();
	}*/
	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}

}
