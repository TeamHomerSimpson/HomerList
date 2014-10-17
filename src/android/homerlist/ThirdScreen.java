package android.homerlist;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.telerik.everlive.sdk.core.EverliveApp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class ThirdScreen extends Activity {
	
	EverliveApp app;
	ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_screen);

		startCamera();		
	}

	private void startCamera() {
		Intent camera = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		this.startActivityForResult(camera, 100);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);		

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