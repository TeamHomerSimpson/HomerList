package android.homerlist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class DialogViewer extends DialogFragment {
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(R.string.confirm_delete)
				.setPositiveButton(R.string.ok_message,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
//								final Intent intent = new Intent(getActivity().getApplicationContext(), SecondScreen.class);
//								intent.putExtra("isClickYes", true);
							}
						})
				.setNegativeButton(R.string.cancel_message,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Toast.makeText(getActivity(), "Not deleted", Toast.LENGTH_SHORT).show();
							}
						});
		// Create the AlertDialog object and return it
		return builder.create();
	}
}