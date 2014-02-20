package android.epikoinwnia;

import android.util.Log;

public class StraightLine {

	private float A,B,C;
	private float l,g;
	point start,end;
	float lengthDeviation=15;
	
	public StraightLine(point a,point b)
	{
		start=a;
		end=b;
		getA();
		getB();
		getC();
		l=(A/(float)B)*(-1);
		g=C/(float)B*(-1);
	}
	private void getA()
	{
		if( Math.abs((end.getx()-start.getx()))<10 )
			{
				A=0.007f;
				return ;
			}
		
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
	
	public float getL()
	{
		return l;
	}
	
	public float getG()
	{
		return g;
	}
	
	public void setLengthDeviation(float deviation)
	{
		lengthDeviation=deviation;
	}
	
	public float getLength()
	{
		return (float)Math.pow(  (float) (Math.pow(start.getx()-end.getx(),2) + (float)Math.pow(start.gety()-end.gety(), 2)) , 0.5);
	}
	
	public String toString()
	{
		return "A="+String.valueOf(A)+" B="+String.valueOf(B)+" C="+String.valueOf(C);
	}
	
	public boolean isEqualLine(StraightLine line)
	{
		if(  line.getLength()-lengthDeviation<getLength() && getLength()<line.getLength()+lengthDeviation )
			return true;
		return false;
	}
	
	public boolean isEqualLine(StraightLine line,int deviation)
	{
		if(  line.getLength()-deviation<getLength() && getLength()<line.getLength()+deviation )
			return true;
		return false;
	}
	
	public boolean isParallel(StraightLine line2)
	{
		Log.d(String.valueOf(getL()) ,String.valueOf(line2.getL()));
		if(getLength()<20|| line2.getLength()<20)
			return false;
		if(line2.getL()-0.2f<getL() && getL()<line2.getL()+0.2f)
				return true;
		return false;
	}
	
	public float getKlisi()
	{
		return A;
	}
}
