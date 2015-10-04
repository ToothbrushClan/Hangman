package com.toothbrushclan.hangman.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.toothbrushclan.hangman.R;

/**
 * Created by ushaikh on 04/10/15.
 */
public class ConfirmationDialog extends DialogFragment {

    public interface ConfirmationDialogListener {
        public void onDialogYesClick(DialogFragment dialog);
        public void onDialogNoClick(DialogFragment dialog);
    }

    ConfirmationDialogListener confirmationDialogListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            confirmationDialogListener = (ConfirmationDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement ConfirmationDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.goBack);
        builder.setMessage(R.string.gameInProgress);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                confirmationDialogListener.onDialogYesClick(ConfirmationDialog.this);
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                confirmationDialogListener.onDialogNoClick(ConfirmationDialog.this);
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
