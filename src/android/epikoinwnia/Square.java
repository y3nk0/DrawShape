package android.epikoinwnia;

import java.util.Vector;

import android.util.Log;

public class Square extends Predictor{

	private Vector<point> coordinates;
	private StraightLine line1;
	private StraightLine line2;
	private StraightLine line3;
	private StraightLine line4;
	private int begin=-1,end=-1;
	private float deviation=0;
	
	public Square(Vector<point> coordinates)
	{
		this.coordinates=coordinates;
	}
	
	private void getHead()
	{
		for(int i=0; i<coordinates.size(); i++){
			for(int j=i+1; j<coordinates.size()-1;j++)
			{
				if(coordinates.get(i).isEqualTouch(coordinates.get(j),15f))
				{
					if(j-i<15)
						continue;
					begin=i;
					end=j;
					Log.d("Einai sunexes sxima", " i="+String.valueOf(begin)+" j="+String.valueOf(end));
					return;
				}
			}
		}
		return;
	}
	
	public boolean predict(){
		getHead();
		if(begin==-1 || coordinates.size()<50)
		{
			Log.d(" ----- "," i="+String.valueOf(begin)+" j="+String.valueOf(begin));
			return false;
		}
			
		int step=coordinates.size()/4;
		
		line1= new StraightLine(coordinates.firstElement(),coordinates.get(step));
		line2= new StraightLine(coordinates.get(step),coordinates.get(step*2));
		line3= new StraightLine(coordinates.get(step*2),coordinates.get(step*3));
		line4= new StraightLine(coordinates.get(step*3),coordinates.lastElement());
		
		Vector<point> temp = new Vector<point>();
		int start=0;
		int finish=step;
		boolean checking=true;
		int turn=1;
		Line line;
		while(checking)
		{	
			temp.clear();
			for(int i=start; i<=finish; i++)
				temp.add(coordinates.get(i));
			line=new Line(temp,deviation);
			if(!line.predict(deviation))
			{
				line.print();
				Log.d("Is not a line",String.valueOf(turn));
				return false;
			}
			turn++;
			start=finish;
			finish+=step;
			if(finish>coordinates.size()-4)
				checking=!checking;
		}
		
		line1.setLengthDeviation(deviation);
		line2.setLengthDeviation(deviation);
		line3.setLengthDeviation(deviation);
		line4.setLengthDeviation(deviation);
		
		if(line1.isEqualLine(line2)&&line2.isEqualLine(line3)&&line3.isEqualLine(line4))
		{
			Log.d("einai tetragono", "sdd");
			return true;
			}
		else 
			Log.d("einai kikliko ","alla oxi tetragono");
		
		return false;
	}
	
	public void setDeviation(float deviation)
	{
		this.deviation=deviation;
	}
	
}
