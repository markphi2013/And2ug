package com.maxmass.ug;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class Danger extends SherlockListActivity{

	// All static variables
	static final String URL = "http://154.0.129.6/database/spot/spot.xml";
	// XML node keys
	static final String KEY_SPOT = "spot"; // parent node
	static final String KEY_DANGER = "danger";
	static final String KEY_NAME = "name";
	// Progress dialog
	ProgressDialog pDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pager_list_content);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#6BD6AD")));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Danger");
		
		new PagesFetcher().execute(new String[] { URL });

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
	
	    Intent intent = new Intent(getApplicationContext(), cls);
	    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    startActivity(intent);
	    
    }
	public Document getDomElement(String xml){
		
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        try {
 
            DocumentBuilder db = dbf.newDocumentBuilder();
 
            InputSource is = new InputSource();
                is.setCharacterStream(new StringReader(xml));
                doc = db.parse(is); 
 
            } catch (ParserConfigurationException e) {
            	
                Log.e("Error: ", e.getMessage());
                return null;
                
            } catch (SAXException e) {
            	
                Log.e("Error: ", e.getMessage());
                return null;
                
            } catch (IOException e) {
            	
                Log.e("Error: ", e.getMessage());
                return null;
            }
                // return DOM
            return doc;
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
		protected String doInBackground(String... urls) {
			// TODO Auto-generated method stub
			String xml = null;
            for (String url : urls) {
                xml = getXmlFromUrl(url);
            }
            return xml;
			
		}

		@Override
		protected void onPostExecute(String xml) {
			// TODO Auto-generated method stub
			super.onPostExecute(xml);
			dialog.dismiss();

			ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

			XMLParser parser = new XMLParser();

			InputStream stream = new ByteArrayInputStream(xml.getBytes());
	        Document doc = parser.getDocument(stream);
			NodeList nl = doc.getElementsByTagName(KEY_SPOT);
			
			// looping through all item nodes <item>
			for (int i = 0; i < nl.getLength(); i++) {
				
				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				Element e = (Element) nl.item(i);
				// adding each child node to HashMap key => value
				
				map.put(KEY_NAME, parser.getValue(e, KEY_NAME));
				map.put(KEY_DANGER, parser.getValue(e, KEY_DANGER));
				

				// adding HashList to ArrayList
				menuItems.add(map);
			}

			ListAdapter adapters = new SimpleAdapter(Danger.this, menuItems,
					R.layout.plc_row,
					new String[] { KEY_NAME, KEY_DANGER }, new int[] {
							R.id.PG_TITLE, R.id.address });

			setListAdapter(adapters);
			
		}
		private String getXmlFromUrl(String urlString) {
			
            StringBuffer output = new StringBuffer("");
 
            InputStream stream = null;
            java.net.URL url;
            
            try {
            	
                url = new java.net.URL(urlString);
                URLConnection connection = url.openConnection();
 
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();
 
                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                	
                    stream = httpConnection.getInputStream();
                    BufferedReader buffer = new BufferedReader(
                            new InputStreamReader(stream));
                    String s = "";
                    while ((s = buffer.readLine()) != null)
                        output.append(s);
                }
                
            } catch (MalformedURLException e) {
            	
                Log.e("Error", "Unable to parse URL", e);
                
            } catch (IOException e) {
            	
                Log.e("Error", "IO Exception", e);
            }
                     
            return output.toString();
            
		}
		
	}

}
