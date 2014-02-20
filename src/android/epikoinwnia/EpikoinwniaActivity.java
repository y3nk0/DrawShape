package android.epikoinwnia;

import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EpikoinwniaActivity extends Activity {
	Vector<point> coordinates=new Vector<point>();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(), Activity2.class);
                startActivityForResult(myIntent, 0);
			}
		});
        
        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				exit();
				
			}});
    }
    
    public void exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
               .setCancelable(false)
               .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                        EpikoinwniaActivity.this.finish();
                   }
               })
               .setNegativeButton("No", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                   }
               });
        AlertDialog alert = builder.create();
        alert.show();

    }   
}
