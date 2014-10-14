package android.homerlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class DBPref extends DBHelper {

	public DBPref(Context context) {
		super(context);		
	}
	
	public void addRecord(String val){		
		ContentValues contValues = new ContentValues();
		contValues.put("username", val);
//		contValues.put("post", val);
//		contValues.put("dateCreated", val);
		this.db.insert("posts", null, contValues);
	}
	
	public Cursor getVals(){		
		return this.db.query("posts", new String[]{"username"}, null, null, null, null, null);
	}
}