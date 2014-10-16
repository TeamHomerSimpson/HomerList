package android.homerlist;

import java.util.ArrayList;
import java.util.List;

import com.telerik.everlive.sdk.core.EverliveApp;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	List<String> notes = new ArrayList<String>();
	public String username;
	public String note;
	public Button createNote, addPicture, selectLocationBtn;
	public EditText userInput, submitNote;
	// for camera
	private ImageView mView;

	Intent intent;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		userInput = (EditText) findViewById(R.id.userInput);
		submitNote = (EditText) findViewById(R.id.submitNote);
		// add picture button
		addPicture = (Button) findViewById(R.id.addPicture);
		addPicture.setOnClickListener(this);
		// create note button
		createNote = (Button) findViewById(R.id.createNote);
		createNote.setOnClickListener(this);
		// select location button
		selectLocationBtn = (Button) findViewById(R.id.selectLocationBtn);
		selectLocationBtn.setOnClickListener(this);
		// touch
		// onTouchEvent(event);		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = MotionEventCompat.getActionMasked(event);
		// startActivity(intent);
		if (action == MotionEvent.ACTION_OUTSIDE) {

			return true;
		}

		return super.onTouchEvent(event);
	}

	private void startCamera() {
		Intent camera = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		this.startActivityForResult(camera, 100);
	}

	public void onClick(View v) {

		try {
			// Google maps
			if (v.getId() == R.id.selectLocationBtn) {
				final Intent locationScreenIntent = new Intent(this,
						LocationActivity.class);
				overridePendingTransition(android.R.anim.fade_in,
						android.R.anim.fade_out);
				startActivity(locationScreenIntent);
			} else if (v.getId() == R.id.createNote) {
				intent = new Intent(this, SecondScreen.class);
				username = userInput.getText().toString();
				note = submitNote.getText().toString();
				// post note
				if (isFillUserOrNote()) {
					if (v.getId() == R.id.createNote) {
						intent.putExtra("username", username);
						intent.putExtra("post", note);
						Toast.makeText(this, "Welcome " + username,
								Toast.LENGTH_SHORT).show();
						// animation
						overridePendingTransition(android.R.anim.fade_in,
								android.R.anim.fade_out);
						// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);
					}
				}
				// camera
			} else if (v.getId() == R.id.addPicture) {
				final Intent cameraScreen = new Intent(MainActivity.this, ThirdScreen.class);
				
				overridePendingTransition(android.R.anim.fade_in,
						android.R.anim.fade_out);				
				startActivity(cameraScreen);
				startCamera();
			}

		} catch (Exception e) {
			Toast.makeText(this, "There is a problem!", Toast.LENGTH_SHORT)
					.show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			mView = (ImageView) findViewById(R.id.imageView1);
			Bundle extras = data.getExtras();
			Bitmap photo = (Bitmap) extras.get("data");
			data.putExtra("snimka", extras);
			//startActivity(data);
			// to do work with backend			
//			BackendService telerikService = new BackendService();
//			telerikService.UploadFile(telerikService.app, photo, "image/jpg",
//					inputStream);
			// mView.setImageBitmap(photo);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// check to fill required fields
	private boolean isFillUserOrNote() {
		if (username.equals("")) {
			Toast.makeText(this, "You must choose username!!!",
					Toast.LENGTH_LONG).show();
			return false;
		} else if (note.equals("")) {
			Toast.makeText(this, "You must write something!!!",
					Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}		
}