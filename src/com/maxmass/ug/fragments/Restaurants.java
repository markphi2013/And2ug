package com.maxmass.ug.fragments;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.actionbarsherlock.app.SherlockListFragment;
import com.maxmass.ug.R;
import com.maxmass.ug.XMLParser;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

public class Restaurants extends SherlockListFragment{

	private DisplayMetrics metrics;  
	String tag;
	
	// All static variables
	static final String URL = "http://154.0.129.6/database/hotel/restaurant.xml";
	// XML node keys
	static final String KEY_APARTMENT = "restaurant"; // parent node
	static final String KEY_ADDRESS = "address";
	static final String KEY_NAME = "name";
	static final String KEY_TELEPHONE = "telphone";
	static final String KEY_DISTRICT = "district";
	static final String KEY_TYPE = "type";

	public Restaurants() {
		
	}

	@SuppressLint("InflateParams")
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.pager_list_content, null);
	}	

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getActivity().setTitle("Restaurants");
		
		metrics = new DisplayMetrics();
	    getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

			new PagesFetcher().execute();
	
	}
	private class PageItem {
		@SuppressWarnings("unused")
		public String title, address, telphone,district,type;
		
		public PageItem(String title, String address, String telphone, String district, String type) {
			this.title = title; 
			this.address = address; 
			this.telphone = telphone;
			this.district = district;
			this.type = type;
			
		}
	}
	
	public class PagesAdapter extends ArrayAdapter<PageItem> {
		private Context context;
		  private LayoutInflater mInflater;
		  @SuppressWarnings("unused")
		private DisplayMetrics metrics_;
		  
		public PagesAdapter(Context context, DisplayMetrics metrics) {
			super(context, 0);
			this.context = context;
			this.mInflater = (LayoutInflater) this.context
				     .getSystemService(Context.LAYOUT_INFLATER_SERVICE);			
			this.metrics_ = metrics;
		}
		@SuppressLint("InflateParams")
		public View getView(int position, View convertView, ViewGroup parent) {
			Animation animation = null;
			
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.plc_row, null);
			}
			
			animation = AnimationUtils.loadAnimation(context, R.anim.slide_top_to_bottom);
			
			TextView title = (TextView) convertView.findViewById(R.id.PG_TITLE);
			title.setText(getItem(position).title);
			
			TextView address = (TextView) convertView.findViewById(R.id.address);
			address.setText(getItem(position).address);
			
			TextView telphone = (TextView) convertView.findViewById(R.id.telphone);
			telphone.setText(getItem(position).telphone);
			
			TextView district = (TextView) convertView.findViewById(R.id.district);
			district.setText(getItem(position).district);
			
			TextView type = (TextView) convertView.findViewById(R.id.type);
			type.setText(getItem(position).district);
			
			ImageView image = (ImageView) convertView.findViewById(R.id.PG_IMAGE);
			image.setImageResource(R.drawable.ic_launcher);
			
			   animation.setDuration(500);
			   convertView.startAnimation(animation);
			   animation = null;
			return convertView;
		}

	}
	
	private class PagesFetcher extends AsyncTask<String, String, String> {

		ProgressDialog dialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog = ProgressDialog.show(getActivity(), "Loading","Wait...", true);
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String response = "";						
			
			return response;
			
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
			
			PagesAdapter adapter = new PagesAdapter(getActivity(), metrics);
			ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

			XMLParser parser = new XMLParser();
			String xml = parser.getXmlFromUrl(URL); // getting XML
			Document doc = parser.getDomElement(xml); // getting DOM element

			NodeList nl = doc.getElementsByTagName(KEY_APARTMENT);
			
			// looping through all item nodes <item>
			for (int i = 0; i < nl.getLength(); i++) {
				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				Element e = (Element) nl.item(i);
				// adding each child node to HashMap key => value
				map.put(KEY_ADDRESS, parser.getValue(e, KEY_ADDRESS));
				map.put(KEY_NAME, parser.getValue(e, KEY_NAME));
				map.put(KEY_TELEPHONE, parser.getValue(e, KEY_TELEPHONE));
				map.put(KEY_DISTRICT, parser.getValue(e, KEY_DISTRICT));
				map.put(KEY_TYPE, parser.getValue(e, KEY_TYPE));

				// adding HashList to ArrayList
				menuItems.add(map);
			}
			
			adapter.add(new PageItem(KEY_NAME,KEY_ADDRESS,KEY_TELEPHONE,KEY_DISTRICT,KEY_TYPE));
			setListAdapter(adapter);
		}

	}
}
