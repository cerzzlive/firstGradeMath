package g1.math;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HomeActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ImageView imageView1 = (ImageView)findViewById(R.id.menu1);
        ImageView imageView2 = (ImageView)findViewById(R.id.menu2);
        scaleBitmap(imageView1);
        scaleBitmap(imageView2);
        
        imageView1.setOnTouchListener(new OnTouchListener(){

			public boolean onTouch(View arg0, MotionEvent arg1) {
				if(arg1.getAction() == MotionEvent.ACTION_DOWN) {
					Intent i = new Intent(HomeActivity.this, L1Activity.class);
	        		startActivity(i);
				}
				return false;
				
			}
			
        });
        
    }
    
    

    private void scaleBitmap(ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        float scalingFactor = getBitmapScalingFactor(bitmap, imageView);

        Bitmap newBitmap = Util.ScaleBitmap(bitmap, scalingFactor);
        imageView.setImageBitmap(newBitmap);
		
	}



	private float getBitmapScalingFactor(Bitmap bm, ImageView imageView) {
        // Get display width from device
        int displayWidth = getWindowManager().getDefaultDisplay().getWidth();

        // Get margin to use it for calculating to max width of the ImageView
        LinearLayout.LayoutParams layoutParams = 
            (LinearLayout.LayoutParams)imageView.getLayoutParams();
        int leftMargin = layoutParams.leftMargin;
        int rightMargin = layoutParams.rightMargin;

        // Calculate the max width of the imageView
        int imageViewWidth = displayWidth - (leftMargin + rightMargin);

        // Calculate scaling factor and return it
        return ( (float) imageViewWidth / (float) bm.getWidth() );
    }
}