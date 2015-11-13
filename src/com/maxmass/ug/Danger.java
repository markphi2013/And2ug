package com.maxmass.ug;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class Danger extends SherlockListActivity{

	// All static variables
	static final String URL = "http://154.0.129.6/database/spot/spot.xml";
	// XML node keys
	static final String KEY_SPOT = "spot"; // parent node
	static final String KEY_DANGER = "danger";
	static final String KEY_NAME = "name";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pager_list_content);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6BD6AD")));
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setTitle("Danger");
		ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

		XMLParser parser = new XMLParser();
		String xml = parser.getXmlFromUrl(URL); // getting XML
		Document doc = parser.getDomElement(xml); // getting DOM element

		NodeList nl = doc.getElementsByTagName(KEY_SPOT);
		
		// looping through all item nodes <item>
				for (int i = 0; i < nl.getLength(); i++) {
					// creating new HashMap
					HashMap<String, String> map = new HashMap<String, String>();
					Element e = (Element) nl.item(i);
					// adding each child node to HashMap key => value
					map.put(KEY_DANGER, parser.getValue(e, KEY_DANGER));
					map.put(KEY_NAME, parser.getValue(e, KEY_NAME));
					
					// adding HashList to ArrayList
					menuItems.add(map);
				}
				// Adding menuItems to ListView
				ListAdapter adapter = new SimpleAdapter(this, menuItems,
						R.layout.plc_row,
						new String[] { KEY_NAME, KEY_DANGER}, new int[] {
								R.id.PG_TITLE, R.id.address,R.id.PG_IMAGE });

				setListAdapter(adapter);
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
