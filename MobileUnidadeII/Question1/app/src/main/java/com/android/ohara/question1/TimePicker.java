package com.android.ohara.question1;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import java.util.Calendar;

public class TimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private OnTimeSet onTimeSet;

    public void onAttach (Context context) {
        super.onAttach(context);

        if (!(context instanceof OnTimeSet)) throw new RuntimeException("Não é do tipo OnTimeSet");

        onTimeSet = (OnTimeSet) context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute, true);
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        if (onTimeSet != null) {
            String timeChosen = String.format("%02d:%02d", hourOfDay, minute);
            onTimeSet.setTime(timeChosen);
        }
    }

    public interface OnTimeSet {
        public void setTime(String time);
    }
}