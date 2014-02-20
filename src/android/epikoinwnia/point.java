package android.epikoinwnia;


public class point
{
	private float x,y;
	private boolean isValid;
	public point()
	{
		isValid=false;
	}
	public point(float xi,float yi)
	{
		x=xi;
		y=yi;
		isValid=true;
	}
	public float getx(){
		return x;
	}
	
	public float gety(){
		return y;
	}
	
	public boolean isequal(point item)
	{
		return (x==item.getx()&&y==item.gety())? true: false;
	}
	
	public boolean isEqualTouch(point item,float deviation)
	{
		if(item.getx()-deviation<x && x<item.getx()+deviation && ( item.gety()-deviation<y && y<item.gety()+deviation))
			return true;
		return false;
	}
	public String toString()
	{
		return this.x+":"+this.y;
	}
	public boolean isValid()
	{
		return isValid;
	}
}
