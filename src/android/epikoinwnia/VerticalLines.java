package android.epikoinwnia;

import java.util.Vector;

import android.util.Log;

public class VerticalLines {

	
	private Vector<point> vec1,vec2;
	
	public VerticalLines(Vector<point> vec1, Vector<point> vec2)
	{
		this.vec1=vec1;
		this.vec2=vec2;
	}
	
	public boolean predict()
	{
		Line isLine1 = new Line (vec1);
		Line isLine2 = new Line (vec2);
		
		StraightLine liner1 = new StraightLine(isLine1.getStart(),isLine1.getEnd());
		StraightLine liner2 = new StraightLine(isLine2.getStart(),isLine2.getEnd());
		

		if(liner1.getKlisi()==0.007f && liner2.getKlisi()==0.007f)
		{
			Log.d("EINAI "," KATHETES");
			return true;
		}
		
		if(liner1.isParallel(liner2))
		{
			int deviation=30;
			float c=vec1.firstElement().getx();
			for(int i=1; i<vec1.size(); i++)
			{
				if( !(vec1.get(i).getx()-deviation<c && c<vec1.get(i).getx()+deviation) )
				{
					return false;
				}
			}

			Log.d(String.valueOf(Math.abs(vec1.firstElement().getx()-vec2.firstElement().getx()))," diafora");
			if(Math.abs(vec1.firstElement().getx()-vec2.firstElement().getx()) < 40)
			{
				Log.d(String.valueOf(Math.abs(vec1.firstElement().getx()-vec2.firstElement().getx()))," diafora metaxi tous");
				return false;
			}
		}
		else
		{
			Log.d("Den einai paralliles", " asdasd");
			return false;
		}
		
		return true;
	}
	
}
