package com.android.ohara.question1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

import java.util.Calendar;

public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private OnDateSet onDateSet;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (!(context instanceof OnDateSet)) throw new RuntimeException("Não é do tipo OnDateSet");

        onDateSet = (OnDateSet) context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        if (onDateSet != null) {
            String dateChosen = String.format("%02d/%02d/%d", dayOfMonth, (month + 1), year);
            onDateSet.setDate(dateChosen);
        }
    }

    public interface OnDateSet {
        public void setDate(String date);
    }
}
