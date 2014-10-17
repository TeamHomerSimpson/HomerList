package android.homerlist;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.UUID;

import com.telerik.everlive.sdk.core.EverliveApp;
import com.telerik.everlive.sdk.core.model.system.File;
import com.telerik.everlive.sdk.core.query.definition.FileField;
import com.telerik.everlive.sdk.core.result.RequestResult;
import com.telerik.everlive.sdk.core.result.RequestResultCallbackAction;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class ThirdScreen extends Activity {

	private Context context = this;

	private SecureRandom random = new SecureRandom();
	// ListView list;
	// ArrayList<String> stringList;
	// ArrayAdapter<String> adapter;

	EverliveApp app;
	ImageView img;

	// MyReceiver myReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_screen);

		startCamera();
		// stringList = new ArrayList<String>();
		// adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1,stringList);
		// list = (ListView)findViewById(R.id.listView1);
		// list.setAdapter(adapter);
		//
		// myReceiver = new MyReceiver();
		//
		// IntentFilter intentFilter = new IntentFilter();
		// intentFilter.addAction(BackendService.MY_ACTION);
		// registerReceiver(myReceiver, intentFilter);
		//
		// Intent intent = new Intent(ThirdScreen.this, BackendService.class);
		// startService(intent);
	}

	private void startCamera() {
		Intent camera = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		this.startActivityForResult(camera, 100);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// working

		if (resultCode == RESULT_OK) {
			img = (ImageView) findViewById(R.id.imageView1);
			Bundle extras = data.getExtras();
			Bitmap photo = (Bitmap) extras.get("data");
			img.setImageBitmap(photo);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			if (photo != null) {
				photo.compress(Bitmap.CompressFormat.JPEG, 50,
						byteArrayOutputStream);
				byte[] image = byteArrayOutputStream.toByteArray();
				InputStream stream = new ByteArrayInputStream(image);

				// uploadImage(stream);
				addItems(photo, stream);
			}
		}
	}

	private void addItems(Bitmap photo, InputStream inputStream) {
		// String fileName = new BigInteger(130, random).toString(32) + ".jpg";
		// String contentType = "image/jpeg";
		// FileField fileField = new FileField(fileName, contentType,
		// inputStream);

		app = new EverliveApp("OyATyKiCAzGo7eex");
		app.workWith().authentication().login("achoraev", "1000").executeSync();

		Image img = new Image();
		img.setName("Audi");
		app.workWith().data(Image.class).create(img).executeAsync();
		// app.workWith().files().upload(fileField).executeAsync();
	}
}