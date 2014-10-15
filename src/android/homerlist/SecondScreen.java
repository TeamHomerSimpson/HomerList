package android.homerlist;

import java.util.ArrayList;

import android.app.ListActivity;
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
	ListView showInput;
	DBHelper datasource;
	ArrayList<Note> posts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_screen);

		datasource = new DBHelper(getApplicationContext());
		datasource.open();
		showInput = (ListView) findViewById(android.R.id.list);
		deleteBtn = (Button) findViewById(R.id.btn_delete);
		deleteBtn.setOnClickListener(this);
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
		ArrayList<Note> allNotes = datasource.getAllNotes();
		Note toDeleteNote = allNotes.get(0);
		if (v.getId() == R.id.btn_delete) {
			if (allNotes.toArray().length != 0) {
				datasource.deleteNote(toDeleteNote);
				Toast.makeText(
						this,
						String.valueOf(toDeleteNote.getUsername() + "'s post deleted"),
						Toast.LENGTH_SHORT).show();
				refreshListView();
			}
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		ArrayList<Note> allNotes = datasource.getAllNotes();
		Note toDeleteNote = allNotes.get(position);
		DialogViewer newDialog = new DialogViewer();
		newDialog.show(getFragmentManager(), "DialogViewer");
		if (allNotes.toArray().length != 0) {
			datasource.deleteNote(toDeleteNote);
			Toast.makeText(
					this,
					String.valueOf(toDeleteNote.getUsername() + "'s post deleted"),
					Toast.LENGTH_SHORT).show();
			refreshListView();
		}
	}

	private void refreshListView() {
		//datasource.open();
		NoteAdapter adapter = new NoteAdapter(this, R.layout.post_row,
				datasource.getAllNotes());
		showInput.setAdapter(adapter);
		//datasource.close();
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