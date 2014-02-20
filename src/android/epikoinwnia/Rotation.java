package android.epikoinwnia;

import java.util.Vector;

public class Rotation {
	
	private Vector<point> arc1;
	private Vector<point> arc2;
	private point center;
	private float radius;
	private float deviation=30;
	
	public Rotation (Vector<point> arc1,Vector<point> arc2)
	{
		this.arc1=arc1;
		this.arc2=arc2;
	}
	
	void getCenter()
	{
		center=new point(arc1.firstElement().getx()+(arc2.firstElement().getx()-arc1.firstElement().getx())/2f ,arc1.firstElement().gety()+(arc2.firstElement().gety()-arc1.firstElement().gety())/2f);
		
	}
	
	float distance(point xy)
	{
		return (float)Math.pow( (Math.pow(xy.getx()-center.getx(),2) + Math.pow((xy.gety()-center.gety()),2)) ,0.5 );
	}
	
	void getRadius()
	{
		radius=distance(arc1.firstElement());
	}
	
	public boolean predict()
	{
		getCenter();
		getRadius();
		
		int end=Math.min(arc1.size(),arc2.size());
		
		if(end<10)
			return false;
		
		for(int i=0; i<end; i++)
		{
			if(distance(arc1.get(i))>radius+deviation||distance(arc1.get(i))<radius-deviation)
				return false;
			if(distance(arc2.get(i))>radius+deviation||distance(arc2.get(i))<radius-deviation)
				return false;
		}
		
		if(arc1.size()>arc2.size())
		{
			for(int i=end; i<arc1.size(); i++)
				if(distance(arc1.get(i))>radius+deviation||distance(arc1.get(i))<radius-deviation)
					return false;
		}
		else
			for(int i=end; i<arc2.size(); i++)
				if(distance(arc2.get(i))>radius+deviation||distance(arc2.get(i))<radius-deviation)
					return false;
		
		if( (arc1.lastElement().getx()-arc1.firstElement().getx())*(arc2.lastElement().getx()-arc2.firstElement().getx())<0 )
			return true;
		
		return false;
	}

	public float getDeviation() {
		return deviation;
	}

	public void setDeviation(float deviation) {
		this.deviation = deviation;
	}
	
	
}
