package com.maxmass.ug;

import com.maxmass.ug.fragments.Apartments;
import com.maxmass.ug.fragments.Clubs;
import com.maxmass.ug.fragments.Hotels;
import com.maxmass.ug.fragments.Restaurants;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
	
    public TabPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int i) {
		switch (i) {
		
	        case 0:
	           
	            return new Apartments();
	            
	        case 1:
	           
	            return new Clubs();
	            
	        case 2:
	            
	            return new Hotels();
	            
	        case 3:
	        	
	        	return new Restaurants();
        }
		return null;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4; //No of Tabs
	}


 }