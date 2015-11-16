package com.maxmass.ug;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends SherlockActivity {

	Button tp,hb,ta,ds;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6BD6AD")));
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setTitle("Home");
		tp = (Button)findViewById(R.id.transportbutton);
		hb = (Button)findViewById(R.id.hotelbutton);
		ta = (Button)findViewById(R.id.touratttractionbutton);
		ds = (Button)findViewById(R.id.dangerspotbutton);
	
		tp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent tpIntent = new Intent(MainActivity.this, Transport.class);
				tpIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(tpIntent);
			}
		});
		
		hb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent htIntent = new Intent(MainActivity.this, Hotel.class);
				htIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(htIntent);
			}
		});
		
		ta.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent taIntent = new Intent(MainActivity.this, Tourist.class);
				taIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(taIntent);
			}
		});
		
		ds.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent dsIntent = new Intent(MainActivity.this, Danger.class);
				dsIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(dsIntent);
			}
			
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.actionmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.currceny_convertor:
			
			break;
			
		case R.id.about_us:

			break;
			
		case android.R.id.home:
            // ProjectsActivity is my 'home' activity
            startActivityAfterCleanup(MainActivity.class);
            return true;
			
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void startActivityAfterCleanup(Class<?> cls) {
		
	   // if (projectsDao != null) projectsDao.close();
	    Intent intent = new Intent(getApplicationContext(), cls);
	    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    startActivity(intent);
	    
	}

}
