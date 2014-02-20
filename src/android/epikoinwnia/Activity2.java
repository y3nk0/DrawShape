package android.epikoinwnia;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Activity2  extends Activity {
	
	LinearLayout mLinearLayout;
	long timeremaining=12000;
	boolean ticking=false;
	boolean cross=false;
	public static int Points=0;
	boolean correct=false;
	public static timer mytimer;
	
        @Override
	protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  mLinearLayout = new LinearLayout(this);
		  mLinearLayout.setOrientation(LinearLayout.VERTICAL);
		  Points=0;
			 final TextView text = new TextView(this);
			 final TextView timerInfo = new TextView(this);
			 final TextView score = new TextView(this);
		  
		  timerInfo.setTextColor(Color.YELLOW);
		  timerInfo.setText("--");
		  score.setText(String.valueOf(Points)+" points");
		  
		  ImageView i = new ImageView(this);
		  i.setAdjustViewBounds(false); // set the ImageView bounds to match the Drawable's dimensions
		  i.setImageResource(R.drawable.cardcircle);
		  text.setText("Draw the shape");
		  i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		  i.setOnTouchListener(new CirclePredictor(text,timerInfo,score,i,false));
		  mytimer=new timer(60000,1000,timerInfo,i);
		  mytimer.start();
		  mLinearLayout.addView(timerInfo);
		  mLinearLayout.addView(i);
		  mLinearLayout.addView(text);
		  mLinearLayout.addView(score);
		  mLinearLayout.setBackgroundResource(R.drawable.surface);
		  setContentView(mLinearLayout);
	}
}


