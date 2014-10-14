package android.homerlist;

import java.util.ArrayList;

import android.app.Activity;
import android.homerlist.Note;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class SecondScreen extends Activity implements OnClickListener {
	String username;
	String noteFromInput;
	Button deleteBtn;
	ListView showInput;	
	DBHelper datasource;
	ArrayList<Note> posts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_screen);
		
		datasource = new DBHelper(getApplicationContext());
		datasource.open();
		showInput = (ListView) findViewById(R.id.list_view);
		deleteBtn = (Button) findViewById(R.id.btn_delete);
		//deleteBtn.setOnClickListener(l);
		username = getIntent().getStringExtra("username");
		noteFromInput = getIntent().getStringExtra("post");
		
		posts = new ArrayList<Note>();
		
		Note note = new Note();
		note.setUsername(username);
		note.setNote(noteFromInput);
		//note.setDateCreated("14.10.2014");
		datasource.createNote(note);
		posts.add(note);		
		
		NoteAdapter adapter = new NoteAdapter(this, R.layout.post_row, datasource.getAllNotes());
		showInput.setAdapter(adapter);
		datasource.close();
	}

	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		datasource.open();
	}

	@Override
	protected void onPause() {
		super.onPause();
		datasource.close();
	}
}