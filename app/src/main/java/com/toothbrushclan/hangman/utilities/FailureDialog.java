package com.toothbrushclan.hangman.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.toothbrushclan.hangman.HangmanActivity;
import com.toothbrushclan.hangman.R;

/**
 * Created by ushaikh on 03/10/15.
 */
public class FailureDialog extends DialogFragment {

    public interface FailureDialogListener {
        public void onDialogNextClick(DialogFragment dialog);
        public void onDialogBackClick(DialogFragment dialog);
    }

    FailureDialogListener failureDialogListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            failureDialogListener = (FailureDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement FailureDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        setCancelable(false);
        builder.setTitle(R.string.failure);
        builder.setMessage(R.string.proceedNext);
        builder.setPositiveButton(R.string.nextQuestion, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                failureDialogListener.onDialogNextClick(FailureDialog.this);
            }
        });
        builder.setNegativeButton(R.string.back, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                failureDialogListener.onDialogBackClick(FailureDialog.this);
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
