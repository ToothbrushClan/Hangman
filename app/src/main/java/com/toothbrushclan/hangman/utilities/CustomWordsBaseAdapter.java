package com.toothbrushclan.hangman.utilities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.toothbrushclan.hangman.R;
import com.toothbrushclan.hangman.categories.Question;

/**
 * Created by ushaikh on 07/10/15.
 */
public class CustomWordsBaseAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    Question[] questions;
    CustomButtonListener customListner;

    public CustomWordsBaseAdapter (Context context, LayoutInflater inflater, Question[] questions) {
        this.context= context;
        this.inflater = inflater;
        this.questions = questions;
    }

    public interface CustomButtonListener {
        public void onButtonClickListener(View view, int position, Question question);
    }

    public void setCustomButtonListener(CustomButtonListener listener) {
        this.customListner = listener;
    }

    public class ViewHolder {
        TextView textViewQuestion;
        Button buttonEdit;
        Button buttonDelete;
    }

    public void updateDate(Question[] questions) {
        this.questions = questions;
    }


    @Override
    public int getCount() {
        if (this.questions == null) {
            return 0;
        }
        return this.questions.length;
    }

    @Override
    public Question getItem(int position) {
        return this.questions[position];
    }

    @Override
    public long getItemId(int position) {
        return this.questions[position].getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_words_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewQuestion = (TextView) convertView.findViewById(R.id.customQuestion);
            viewHolder.buttonEdit = (Button) convertView.findViewById(R.id.customEdit);
            viewHolder.buttonDelete = (Button) convertView.findViewById(R.id.customDelete);
            viewHolder.buttonEdit.setBackgroundColor(Color.TRANSPARENT);
            viewHolder.buttonDelete.setBackgroundColor(Color.TRANSPARENT);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Question question = getItem(position);
        viewHolder.textViewQuestion.setText(question.getQuestion());

        viewHolder.buttonEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (customListner != null) {
                    customListner.onButtonClickListener(v, position, question);
                }

            }
        });

        viewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (customListner != null) {
                    customListner.onButtonClickListener(v, position, question);
                }

            }
        });

        return convertView;
    }

}
