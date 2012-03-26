package g1.math;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class SplashScreenActivity extends Activity {

	//how long until we go to the next activity
	protected int _splashTime = 5000; 

	private Thread splashThread;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.splash);

	    final SplashScreenActivity sPlashScreen = this; 

	    // thread for displaying the SplashScreen
	    splashThread = new Thread() {
	        @Override
	        public void run() {
	            try {
	            	synchronized(this){

	            		//wait 5 sec
	            		wait(_splashTime);
	            	}

	            } catch(InterruptedException e) {}
	            finally {
	                finish();

	                //start a new activity
	                Intent i = new Intent();
	                i.setClass(sPlashScreen, HomeActivity.class);
	        		startActivity(i);
	            }
	        }
	    };

	    splashThread.start();
	}

	//Function that will handle the touch
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    if (event.getAction() == MotionEvent.ACTION_DOWN) {
	    	synchronized(splashThread){
	    		splashThread.notifyAll();
	    	}
	    }
	    return true;
	}

}