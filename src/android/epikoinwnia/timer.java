package android.epikoinwnia;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class timer extends CountDownTimer
{
	private long currenttime=0;
	private TextView timerInfo;
	private ImageView view;
	public timer(long millisInFuture, long countDownInterval,TextView timerInfo,ImageView view) {
		super(millisInFuture, countDownInterval);
		this.currenttime=millisInFuture;
		this.timerInfo=timerInfo;
		this.view=view;
	}

	public void onTick(long millisUntilFinished) {
        timerInfo.setText("seconds remaining: " + millisUntilFinished / 1000);
        currenttime=millisUntilFinished;
    }

    public void onFinish() {
   	 timerInfo.setText("Game over!");
   	 view.setOnTouchListener(null);     
     cancel();
    }
    public long getTimetoFinish()
    {
    	return currenttime;
    }
    public int getCanonicalTime()
    {
    	return (int)getTimetoFinish() / 1000;
    }
}