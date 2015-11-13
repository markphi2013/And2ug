package com.maxmass.ug;

import com.maxmass.ug.fragments.Other;
import com.maxmass.ug.fragments.SpecialHire;
import com.maxmass.ug.fragments.Tour;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TransportPagerAdapter extends FragmentStatePagerAdapter{
	
	public TransportPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int index) {
		// TODO Auto-generated method stub
		switch (index) {
		
			case 0:
				return new SpecialHire();
				
			case 1:
				return new Tour();
				
			case 2:
				return new Other();
				

			default:
				break;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
