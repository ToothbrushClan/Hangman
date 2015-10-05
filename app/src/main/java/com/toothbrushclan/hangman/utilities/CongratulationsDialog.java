package com.toothbrushclan.hangman.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.toothbrushclan.hangman.R;

/**
 * Created by ushaikh on 03/10/15.
 */
public class CongratulationsDialog extends DialogFragment {

    public interface CongratulationsDialogListener {
        public void onDialogNextClick(DialogFragment dialog);
        public void onDialogBackClick(DialogFragment dialog);
    }

    CongratulationsDialogListener congratulationsDialogListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            congratulationsDialogListener = (CongratulationsDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement CongratulationsDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        setCancelable(false);
        builder.setTitle(R.string.congratulations);
        builder.setMessage(R.string.proceedNext);
        builder.setPositiveButton(R.string.nextQuestion, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                congratulationsDialogListener.onDialogNextClick(CongratulationsDialog.this);
            }
        });
        builder.setNegativeButton(R.string.back, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                congratulationsDialogListener.onDialogBackClick(CongratulationsDialog.this);
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
