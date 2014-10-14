package android.homerlist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	List<String> notes = new ArrayList<String>();
	public String username;
	public String note;
	public Button createNote, deleteBtn, selectLocationBtn;
	public EditText userInput, submitNote;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		userInput = (EditText) findViewById(R.id.userInput);
		submitNote = (EditText) findViewById(R.id.submitNote);

		createNote = (Button) findViewById(R.id.createNote);
		createNote.setOnClickListener(this);
		
		selectLocationBtn = (Button) findViewById(R.id.selectLocationBtn);
		selectLocationBtn.setOnClickListener(this);
	}

	public void onClick(View v) {
		
		try {
			
			if (v.getId() == R.id.selectLocationBtn) {
				final Intent locationScreenIntent = new Intent(this, LocationActivity.class);
				startActivity(locationScreenIntent);
			}
			
			else if (v.getId() == R.id.createNote) {
				final Intent intent = new Intent(this, SecondScreen.class);
				username = userInput.getText().toString();
				note = submitNote.getText().toString();

				if (isFillUserOrNote()) {
					if (v.getId() == R.id.createNote) {
						intent.putExtra("username", username);
						intent.putExtra("post", note);
						Toast.makeText(this, "Welcome " + username, Toast.LENGTH_SHORT)
								.show();

						startActivity(intent);
					}
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

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