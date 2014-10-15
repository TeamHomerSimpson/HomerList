package android.homerlist;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	public static final String TABLE_NOTES = "notes";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_USERNAME = "username";
	public static final String COLUMN_NOTE = "note";
	public static final String COLUMN_DATE = "dateCreated";
	private String[] allColumns = { DBHelper.COLUMN_ID, DBHelper.COLUMN_USERNAME, 
			DBHelper.COLUMN_NOTE, DBHelper.COLUMN_DATE };
	
	static final String DB_NAME = "notes.db";
	static final int DB_VERSION = 1;
	protected SQLiteDatabase db;
	
	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
				+ TABLE_NOTES + "( " + COLUMN_ID
				+ " integer primary key autoincrement, " + COLUMN_USERNAME
				+ " text not null, " + COLUMN_NOTE
				+ " text null, " + COLUMN_DATE
				+ " text null);";
		
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		open();		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		Log.d("D1", "Database created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DBHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
		onCreate(db);		
	}
	
	public void open()throws SQLException{
		db = getWritableDatabase();	
	}
	
	public void close(){
		db.close();
	}
	
	public Note createNote(Note note) {
		ContentValues values = new ContentValues();		
		values.put(DBHelper.COLUMN_USERNAME, note.getUsername());
		values.put(DBHelper.COLUMN_NOTE, note.getNote());
		values.put(DBHelper.COLUMN_DATE, getDateTimeString());
		long insertId = db.insert(DBHelper.TABLE_NOTES, null,
				values);
		Cursor cursor = db.query(DBHelper.TABLE_NOTES,
				allColumns, DBHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Note newNote = cursorToNote(cursor);
		cursor.close();
		return newNote;
	}

	public void deleteNote(Note note) {
		long id = note.getId();				
		db.delete(DBHelper.TABLE_NOTES, DBHelper.COLUMN_ID
				+ " = " + id, null);
	}

	public ArrayList<Note> getAllNotes() {
		ArrayList<Note> notes = new ArrayList<Note>();

		Cursor cursor = db.query(DBHelper.TABLE_NOTES,
				allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {		
			Note note = cursorToNote(cursor);
			notes.add(note);
			cursor.moveToNext();
		}
		// close the cursor
		cursor.close();
		return notes;
	}

	private Note cursorToNote(Cursor cursor) {
		Note note = new Note();
		note.setId(cursor.getLong(0));
		note.setUsername(cursor.getString(1));
		note.setNote(cursor.getString(2));
		note.setDateCreated(cursor.getString(3));
		return note;
	}
	
	private String getDateTimeString(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss ");
		String dateTimeNow = dateFormat.format(calendar.getTime());
		
		return dateTimeNow;
	}
}