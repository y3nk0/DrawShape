package android.epikoinwnia;

import java.util.Vector;

public class Cross {
	
	private Vector<point> vec1;
	private Vector<point> vec2;
	private float deviation;
	public Cross(Vector<point> vec1, Vector<point> vec2,float deviation)
	
	{
		this.deviation=deviation;
		this.vec1=vec1;
		this.vec2=vec2;
	}
	
	public boolean predict()
	{
		
		Line isLine1 = new Line (vec1);
		isLine1.setDeviation(deviation);
		Line isLine2 = new Line (vec2);
		isLine2.setDeviation(deviation);
		StraightLine liner1 = new StraightLine(isLine1.getStart(),isLine1.getEnd());
		StraightLine liner2 = new StraightLine(isLine2.getStart(),isLine2.getEnd());
		if(liner1.getL()*liner2.getL()<0)
		{
			if( Math.abs(liner1.getL())-0.2 <  Math.abs(liner2.getL())  && Math.abs(liner2.getL()) < Math.abs(liner1.getL())+0.2  ) 
				if(vec1.get( vec1.size()/2).isEqualTouch( vec2.get(vec2.size()/2), deviation) )
					return true;
			
		}
		return false;
	}
}
