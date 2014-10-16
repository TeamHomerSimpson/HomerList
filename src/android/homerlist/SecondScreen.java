package android.homerlist;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.homerlist.Note;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class SecondScreen extends ListActivity implements View.OnClickListener {

	String username;
	String noteFromInput;
	Button deleteBtn;
	Button createNewNoteBtn;
	ListView showInput;
	DBHelper datasource;
	ArrayList<Note> posts;
	public static String isClickYes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_screen);

		datasource = new DBHelper(getApplicationContext());
		datasource.open();
		showInput = (ListView) findViewById(android.R.id.list);
		deleteBtn = (Button) findViewById(R.id.btn_delete);
		deleteBtn.setOnClickListener(this);
		createNewNoteBtn = (Button) findViewById(R.id.btn_createNewNote);
		createNewNoteBtn.setOnClickListener(this);
		
		username = getIntent().getStringExtra("username");
		noteFromInput = getIntent().getStringExtra("post");

		posts = new ArrayList<Note>();

		Note note = new Note();
		note.setUsername(username);
		note.setNote(noteFromInput);
		datasource.createNote(note);
		posts.add(note);

		refreshListView();
	}	

	public void onClick(View v) {		
		if (v.getId() == R.id.btn_delete) {
			deleteItem(0);
		}
		else if (v.getId() == R.id.btn_createNewNote) {
			final Intent createNewNoteIntent = new Intent(this, MainActivity.class);
			overridePendingTransition(android.R.anim.fade_in,
					android.R.anim.fade_out);
			startActivity(createNewNoteIntent);
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);		
		DialogViewer newDialog = new DialogViewer();
		newDialog.show(getFragmentManager(), "DialogViewer");
		deleteItem(position);		
		// todo make this work
//		isClickYes = getIntent().getStringExtra("isClickYes");
//		if (isClickYes == "true") {
//			// delete
//		}		
	}
	
	public void deleteItem(int position){
		ArrayList<Note> allNotes = datasource.getAllNotes();
		Note toDeleteNote = allNotes.get(position);
		if (allNotes.toArray().length != 0) {
			datasource.deleteNote(toDeleteNote);
			Toast.makeText(
					this,
					String.valueOf(toDeleteNote.getUsername()
							+ "'s post deleted"), Toast.LENGTH_SHORT).show();
			refreshListView();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();		
	}

	@Override
	protected void onPause() {
		super.onPause();		
	}
	
	@Override
	protected void onDestroy() {		
		super.onDestroy();
		datasource.close();
	}

	private void refreshListView() {		
		NoteAdapter adapter = new NoteAdapter(this, R.layout.post_row,
				datasource.getAllNotes());
		showInput.setAdapter(adapter);		
	}
}