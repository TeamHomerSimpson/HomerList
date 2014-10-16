package android.homerlist;

import java.util.ArrayList;

import com.telerik.everlive.sdk.core.EverliveApp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class ThirdScreen extends Activity {

	ListView list;
	ArrayList<String> stringList;
	ArrayAdapter<String> adapter;

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		unregisterReceiver(myReceiver);
		super.onStop();
	}

	MyReceiver myReceiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_screen);
		
//		stringList = new ArrayList<String>();		
//		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,stringList);
		
		ImageView img = (ImageView) findViewById(R.id.imageView1);
		//Bundle extras = data.getExtras();
		//Bitmap photo = (Bitmap) extras.get("data");
		//list = (ListView)findViewById(R.id.listView1);
//		list.setAdapter(adapter);		
//		
//		myReceiver = new MyReceiver();
//		
//		IntentFilter intentFilter = new IntentFilter();
//		intentFilter.addAction(BackendService.MY_ACTION);
//		registerReceiver(myReceiver, intentFilter);
//		
//		Intent intent = new Intent(ThirdScreen.this, BackendService.class);
//		startService(intent);		
	}
	
	EverliveApp app;
	private void addItems() {
		
		app = new EverliveApp("F2gsRAKTLx5556Y5");
		app.workWith().authentication().login("telerik_test", "1234")
		        .executeSync();
		
		// create books
//		for (int start = 0; start < 8; start++) {
//			Books testBook = new Books();
//			testBook.setTitle(String.valueOf(start) + ".Harry Potter");
//			app.workWith().data(Books.class).create(testBook).executeAsync();
//		}
	}

	private class MyReceiver extends BroadcastReceiver
	{		
		@Override
		public void onReceive(Context arg0, Intent arg1) {			
			
			String datapassed = arg1.getStringExtra("DATAPASSED");						
			if(!stringList.contains(datapassed))
			{
				stringList.add(datapassed);
				
				adapter.notifyDataSetChanged();
				Toast.makeText(
				        ThirdScreen.this,
				        "Triggered by Service!\n" + "Data passed: "
				                + String.valueOf(datapassed), Toast.LENGTH_LONG)
				        .show();
			}												
		}		
	}
}