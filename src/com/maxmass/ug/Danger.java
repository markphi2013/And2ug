package com.maxmass.ug;

import com.actionbarsherlock.app.SherlockListActivity;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Danger extends SherlockListActivity{

	TextView title,details;
	ImageView image;
	private DisplayMetrics metrics;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pager_list_content);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6BD6AD")));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Dangerous Spots");
		metrics = new DisplayMetrics();
		
		new PagesFetcher().execute();
		
	}
	private class PageItem {
		@SuppressWarnings("unused")
		public String title, details, url;
		
		public PageItem(String title, String details, String url) {
			this.title = title; 
			this.details = details; 
			this.url = url;
		}
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Toast.makeText(Danger.this, "Clicked item at index: "+position, Toast.LENGTH_SHORT).show();
		
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
			
			TextView details = (TextView) convertView.findViewById(R.id.PG_DETAILS);
			details.setText(getItem(position).details);
			
			ImageView image = (ImageView) convertView.findViewById(R.id.PG_IMAGE);
			image.setImageResource(R.drawable.gorilla_uganda);
			
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
			dialog = ProgressDialog.show(Danger.this, "Loading","Wait...", true);
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dialog.dismiss();
			PagesAdapter adapter = new PagesAdapter(Danger.this, metrics);
			
			for(int i = 0; i < 10; i++){
				adapter.add(new PageItem("Demo Page Title", "This is a place where men come and share life together, "
   		                 + "and expose themselves to topics that grow them "
   		                + "individually make sure you dont miss it tis time round", ""));
			}
			
			setListAdapter(adapter);
		}
		
		
	}
	

}
