package android.homerlist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class DialogViewer extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(R.string.confirm_delete)
				.setPositiveButton(R.string.ok_message,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// delete
							}
						})
				.setNegativeButton(R.string.cancel_message,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// not delete
							}
						});
		// Create the AlertDialog object and return it
		return builder.create();
	}
}