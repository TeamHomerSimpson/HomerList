package android.homerlist;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.telerik.everlive.sdk.core.EverliveApp;
import com.telerik.everlive.sdk.core.query.definition.FileField;
import com.telerik.everlive.sdk.core.result.RequestResult;

public class BackendService extends Service
{	
	final static String MY_ACTION = "MY_ACTION";
	EverliveApp app;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		app = new EverliveApp("OyATyKiCAzGo7eex");
		app.workWith().authentication().login("achoraev", "1000")
		        .executeSync();				
	}
	
	public void UploadFile(EverliveApp app, String fileName, String contentType, InputStream inputStream) {
	    FileField fileField = new FileField(fileName, contentType, inputStream);
	    app.workWith().files().upload(fileField).executeSync();
	}
	
//	public String getDownloadLink(EverliveApp app, UUID fileId)
//	{
//	    return app.workWith().files().getFileDownloadUrl(fileId);
//	}
	
	public void deleteFileById(EverliveApp app, UUID fileId) {
	    app.workWith().files().deleteById(fileId).executeAsync();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent, int startId) {		
		super.onStart(intent, startId);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {		
		Check test = new Check();
		test.start();
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	private class Check extends Thread
	{
		@Override
		public void run() {
			super.run();
			while (true) {
				try {
					Thread.sleep(100);
					Intent intent = new Intent();
					intent.setAction(MY_ACTION);
									
					RequestResult allItems = app.workWith().data(Image.class)
					        .getAll().executeSync();
					if (allItems.getSuccess()) {
						ArrayList images = (ArrayList) allItems.getValue();
						for (Object image : images) {
							Image img = (Image) image;
							intent.putExtra("DATAPASSED", img.getAuthor());
						}						
					}
					
					// intent.putExtra("DATAPASSED", "123");
					
					sendBroadcast(intent);
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}
			}
		}		
	}	
}