package android.epikoinwnia;

import java.util.Random;
import java.util.Vector;

import android.epikoinwnia.R.drawable;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class CirclePredictor extends Activity2 implements OnTouchListener
{
	private CountDownTimer innertime;
	private boolean bonus;
	public final TextView text;
	public final TextView timerInfo;
	public final TextView score;
	public final ImageView view;
	private Vector<point> finger1=new Vector<point>();
	private Vector<point> finger2=new Vector<point>();
	private Vector<point> coordinates = new Vector<point>();
	private Circle circle;
	private boolean backsidechecked;
	private long remaining;
	
	public CirclePredictor(TextView text,TextView timerInfo,TextView score,ImageView view,boolean backsidechecked)
	{
		this.innertime=new CountDownTimer(3000, 1000){

			@Override
			public void onFinish() {
				bonus=false;
				
			}

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				
			}}.start();
		this.backsidechecked=backsidechecked;
		this.text=text;
		this.text.setText(text.getText());
		this.timerInfo=timerInfo;
		this.score=score;
		this.view=view;
		if(!backsidechecked)
		this.view.setImageResource(R.drawable.cardcircle);
		this.bonus=true;
	}
	
	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_MOVE)
		{	
			if(event.getPointerCount()==2)//An exei patisei duo daxtila krata tis suntetagmenes
			{
			     finger1.add(new point( event.getX(0), event.getY(0) ));
			     finger2.add(new point( event.getX(1), event.getY(1) ));
			     return true;
			}
			
			text.setTextColor(Color.WHITE);
			text.setText(new point((int)event.getX(),(int)(v.getHeight()-event.getY())).toString());
			coordinates.add(new point(event.getX(),(v.getHeight()-event.getY())));// exei allaxei o typos apo (int) se float
			if(event.getX()>v.getWidth()||event.getY()>v.getHeight()||event.getY()<0)
			{
				text.setText("Please draw isnide the picture");
				return false;
			}
		}
		
		if(event.getAction()==MotionEvent.ACTION_UP)
		{
						
			if(finger1.size()>10)//Otan eixe 2 daxtila patimena checkare gia slide 
			{
				Rotation ratata = new Rotation(finger1,finger2);
				ratata.setDeviation(view.getWidth()*(0.15f));
				
				if( new Line(finger1,20f).predict())
				{
					if(finger2.size()>10)
					{
					Line isLine = new Line (finger1,20f);
					if( new Line(finger2,20f).predict())
					{
						Line isLine2 = new Line (finger2,20f);

						StraightLine liner1 = new StraightLine(isLine.getStart(),isLine.getEnd());
						StraightLine liner2 = new StraightLine(isLine2.getStart(),isLine2.getEnd());
						if(liner1.isParallel(liner2))
						{
							Log.d("Lines are "," Parallel");
							Points-=10;
							score.setText(String.valueOf(Points));
							view.setOnTouchListener(getRandomListener());
						}
						else
							Log.d("Lines are ", " NOT Parallel");
						
					}
					}
				}
				else if(ratata.predict())
				{
					if(!backsidechecked)
					{
				    	remaining=mytimer.getTimetoFinish();
				    	mytimer.cancel();
						backside();
						Myview.sendMessageDelayed(new Message(), 100);
		    			score.setText(String.valueOf(Points));
				    	Log.d(String.valueOf(remaining),String.valueOf(mytimer.getTimetoFinish()));
						return true;
					}
					finger1.clear();
					finger2.clear();
				}
				else 
					Log.d("dn itan rotation"," asdsad");
				finger1.clear();
				finger2.clear();
			}
			
			circle=new Circle(coordinates);
			circle.setCenter(new point(v.getWidth()/2,v.getHeight()/2));
			circle.setRadius( (v.getWidth()*1/3f) );
			circle.setDeviation(v.getWidth()*2/5f);
			if(circle.predict())
			{
				correct=true;
				Log.d("center is "+circle.getCenter().toString(),"aktina is "+String.valueOf(circle.getRadius()));
			}
				
			
			if(correct)
			{
				text.setTextColor(Color.GREEN);
				text.setText("user drew correct shape !!!");
				if(bonus)
					Points+=10;
				else
					Points+=5;
				score.setText(String.valueOf(Points+" points"));
				printVector(coordinates);
				view.setOnTouchListener(getRandomListener());
			}
			else
			{   text.setTextColor(Color.RED);
				text.setText("user drew incorrect shape");
			}
			correct=false;
		     
			if(coordinates.size()==0)
				return false;
			
			
					
			coordinates.clear();
		}
		return true;
	}    
    void printVector(Vector<point> vec)
   	{
   		int i=0;
   		for(point item:vec)
   		{
   			Log.d(" point is "+String.valueOf(i), item.toString());
   			i++;
   		}
   		Log.d("----------------- ", "------------");
   	}
    
    private OnTouchListener getRandomListener()
    {
    	Random random = new Random();
    	int choice=random.nextInt(4);
    	switch(choice)
    	{
    	case 0: return new CrossPredictor(text,timerInfo,score,view,false);
    	case 1: return new TripleLinesPredictor(text,timerInfo,score,view,false);
    	case 2: return new SquarePredictor(text,timerInfo,score,view,false);
    	case 3: return new VerticalLinesPredictor(text,timerInfo,score,view,false);
    	default: return new CirclePredictor(text,timerInfo,score,view,false);
    	}
    }
    
    private void backside()
    {
    	Random random = new Random();
    	int choice=random.nextInt(4);
    	switch(choice)
    	{
    	case 0: view.setImageResource(R.drawable.cardbackside);
    			break;
    	case 1: view.setImageResource(R.drawable.cardkillself);
    			Points-=10;
    			break;
    	case 2: view.setImageResource(R.drawable.cardtimeextend);
    			remaining+=20000;
				break;
    	case 3: view.setImageResource(R.drawable.cardtimeshorten);
    			remaining-=5000;
				break;
		    	default: view.setImageResource(R.drawable.cardblank);
		    	}
    	view.setOnTouchListener(new CirclePredictor(text,timerInfo,score,view,true));
	}
    private Handler Myview = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mytimer=new timer(remaining,1000,timerInfo,view);
			mytimer.start();
        	view.setImageResource(R.drawable.cardcircle);
        };
    };
}
