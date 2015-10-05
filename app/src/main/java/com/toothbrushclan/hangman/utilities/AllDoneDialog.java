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
public class AllDoneDialog extends DialogFragment {

        public interface AllDoneDialogListener {
            public void onDialogBackClick(DialogFragment dialog);
        }

    AllDoneDialogListener allDoneDialogListener;

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            // Verify that the host activity implements the callback interface
            try {
                // Instantiate the NoticeDialogListener so we can send events to the host
                allDoneDialogListener = (AllDoneDialogListener) activity;
            } catch (ClassCastException e) {
                // The activity doesn't implement the interface, throw exception
                throw new ClassCastException(activity.toString()
                        + " must implement AllDoneDialogListener");
            }
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            setCancelable(false);
            builder.setTitle(R.string.allDone);
            builder.setMessage(R.string.noMoreQuestions);
            builder.setPositiveButton(R.string.back, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    allDoneDialogListener.onDialogBackClick(AllDoneDialog.this);
                }
            });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

