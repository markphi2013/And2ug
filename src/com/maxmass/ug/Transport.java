package com.maxmass.ug;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

public class Transport extends SherlockFragmentActivity{

	TextView title,details;
	ImageView image;
	ViewPager Tab;
	ActionBar actionBar;
    TabPagerAdapter TabAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6BD6AD")));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Transport Services");

		TabAdapter = new TabPagerAdapter(getSupportFragmentManager());
		
		Tab = (ViewPager)findViewById(R.id.pager);
        Tab.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                       
                    	actionBar = getSupportActionBar();
                    	actionBar.setSelectedNavigationItem(position);                    }
                });
        Tab.setAdapter(TabAdapter);
        
        actionBar = getSupportActionBar();
        //Enable Tabs on Action Bar
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener(){

			@Override
			public void onTabSelected(com.actionbarsherlock.app.ActionBar.Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				Tab.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(com.actionbarsherlock.app.ActionBar.Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTabReselected(com.actionbarsherlock.app.ActionBar.Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
        	
        };
        //Add New Tab
		actionBar.addTab(actionBar.newTab().setText("SpecialHire").setTabListener(tabListener));
		actionBar.addTab(actionBar.newTab().setText("Tour").setTabListener(tabListener));
		actionBar.addTab(actionBar.newTab().setText("Other").setTabListener(tabListener));
		
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
            break;
 
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
