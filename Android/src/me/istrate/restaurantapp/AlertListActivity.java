package me.istrate.restaurantapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class AlertListActivity extends Activity {
	private ArrayList < Alert > m_Alerts = new ArrayList< Alert >();
	int convert = 0;
	
	SharedPreferences prefs;
	Editor editor;
	JSONArray jsonArray = null;
	ArrayAdapter<Alert> adapter;
	
	//
	JSONObject dummy = new JSONObject();
	
	//
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alert_layout);

		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		editor = prefs.edit();

		//this is where you put your json string kind sir
		String json = prefs.getString("alerts", "[]");
		//
		//get json array
		try {
			jsonArray = new JSONArray(json);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		//this is where you put alerts as objects oherwise look for comments in header
		try {
			dummy.put("Alert", "Some Alert Goes Here");
			jsonArray.put(dummy);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		//
		for (int i = 0; i < jsonArray.length(); i++) {
			String alertContent = "";
			try {
				alertContent = jsonArray.getJSONObject(i).get("Alert").toString();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			m_Alerts.add(new Alert().AlertContent(alertContent));
		}		
		
		ListView alertList = (ListView)findViewById(R.id.alert_list);
		
		//register list for context menu
		registerForContextMenu(alertList);
		
		adapter = new ArrayAdapter<Alert>(this, android.R.layout.simple_list_item_1, m_Alerts);
		alertList.setAdapter(adapter);	
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.context_menu, menu);
	}
	
	//removes one element from json array
	public JSONArray removeElement(JSONArray oldArray, int pos) {
		JSONArray newArray = new JSONArray();
		for (int i = 0; i < oldArray.length(); i++) {
			if (i != pos) {
				try {
					newArray.put(oldArray.getJSONObject(i));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		return newArray;
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo)item.getMenuInfo();
		switch(item.getItemId()) {
			case (R.id.delete_order):
		        // Show message
			jsonArray = removeElement(jsonArray, menuInfo.position);
			editor.putString("alerts", jsonArray.toString());
			editor.commit();		
			adapter.remove(adapter.getItem(menuInfo.position));
	        break;
		}
		return true;
	}
}
