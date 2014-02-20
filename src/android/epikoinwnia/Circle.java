package android.epikoinwnia;

import java.util.Vector;

import android.util.Log;

public class Circle extends Predictor{
	
	private Vector<point> coordinates;
	private point center;
	private float aktina;
	private float deviation=15;
	private int begin=-1,end=-1;
	
	public Circle(Vector<point> coordinates)
	{
		this.coordinates=coordinates;
		aktina=0;
	}
	
	public boolean predict(){
		getHead();
		if(begin==-1 || (end-begin<10))
		{
			Log.d(" ----- "," i="+String.valueOf(begin)+" j="+String.valueOf(begin));
			return false;
		}
		for(int i=begin; i<end;i++)
		{
			Log.d("distance of current element ",String.valueOf(distance(coordinates.get(i))));
			if(distance(coordinates.get(i))>aktina+deviation||distance(coordinates.get(i))<aktina-deviation)
				return false;
		}
		return true;
	}
	
	void getHead()
	{
		for(int i=0; i<coordinates.size(); i++){
			for(int j=i+5; j<coordinates.size()-5;j++)
			{
				if(coordinates.get(i).isEqualTouch(coordinates.get(j),2f))
				{
					begin=i;
					end=j;
					Log.d("vrike arxi", " i="+String.valueOf(i)+" j="+String.valueOf(j));
					return;
				}
			}
		}
		return;
	}
	
	public void setCenter(point xy)
	{
		this.center=xy;
	}
	
	public float distance(point xy)
	{
		return (float)Math.pow( (Math.pow(xy.getx()-center.getx(),2) + Math.pow((xy.gety()-center.gety()),2)) ,0.5 );
	}
	
	public point getCenter()
	{
		return center;
	}
	
	public void setRadius(float radius)
	{
		aktina=radius;
	}
	
	public float getRadius()
	{
		return aktina;
	}
	
	public void printVector()
	{
		int i=0;
		for(point item:coordinates)
		{
			Log.d(" point is "+String.valueOf(i), item.toString());
			i++;
		}
	}
	
	public void setDeviation(float deviation)
	{
		this.deviation=deviation;
	}
}
