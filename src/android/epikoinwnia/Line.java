package android.epikoinwnia;

import java.util.Vector;
import java.lang.Math;

import android.util.Log;

public class Line extends Predictor {
	
	Vector<point> coordinates;
	point start,end;
	float deviation=4f;
	float A,B,C;
	
	public Line(Vector<point> coordinates){
		this.coordinates=coordinates;
		start=coordinates.firstElement();
		end=coordinates.lastElement();
		getA();
		getB();
		getC();
	}
	
	public Line(Vector<point> coordinates,float deviation){
		this.coordinates=coordinates;
		start=coordinates.firstElement();
		end=coordinates.lastElement();
		this.deviation=deviation;
		getA();
		getB();
		getC();
	}
	
	public boolean predict(){
		start=coordinates.firstElement();
		end=coordinates.lastElement();

		point current=start;
		int i=1;
		while(current!=end)
		{
			current=coordinates.get(i);
			if(deviation<getDistance(current))
				return false;
			i++;
		}
		return true;
	}
	
	public boolean predict(float error){
		start=coordinates.firstElement();
		end=coordinates.lastElement();

		point current=start;
		int i=1;
		while(current!=end)
		{
			current=coordinates.get(i);
			if(error<getDistance(current))
				return false;
			i++;
		}
		return true;
	}
	
	float getDistance(point x)
	{
		float distance=Math.abs( (A*x.getx()+x.gety()-B)+C ) / (float)Math.pow((A*A+B*B),0.5);
		return distance;
	}
	@Override
	public String getMessage()
	{
		return "A ="+String.valueOf(A)+" B="+String.valueOf(B+" C="+C);
	}
	
	private void getA()
	{
		A= ( (end.gety()-start.gety()) / (float)(end.getx()-start.getx()) )*(-1);
	}
	
	private void getB()
	{
		B=1;
	}
	
	private void getC()
	{
		C=(-1)*A*start.getx()-start.gety()*B;
	}
	public void print()
	{
		for(point item:coordinates)
		{
			Log.d("point is",item.toString());
		}
	}
	public point getStart()
	{
		return start;
	}
	public point getEnd()
	{
		return end;
	}
	public void setDeviation(float deviation)
	{
		this.deviation=deviation;
	}
}
