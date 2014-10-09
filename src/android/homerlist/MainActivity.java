package android.homerlist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	String username;
	String note;
	Button createNote;
	EditText userInput, submitNote;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		userInput = (EditText) findViewById(R.id.userInput);
		submitNote = (EditText) findViewById(R.id.submitNote);
		createNote = (Button) findViewById(R.id.createNote);

		createNote.setOnClickListener(this);

	}

	public void onClick(View v) {
		note = submitNote.getText().toString();
		username = userInput.getText().toString();
		
		if (v.getId() == R.id.createNote) {
			if (note == "" || note == null) {
				Toast.makeText(this, "You must write something!!!",
						Toast.LENGTH_LONG).show();
			} else if (username == "" || username == null) {
				Toast.makeText(this, "You must choose username!!!",
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "Welcome " + username, Toast.LENGTH_SHORT)
						.show();
				Toast.makeText(this, submitNote.getText(), Toast.LENGTH_LONG)
						.show();
			}
		}

	}
}