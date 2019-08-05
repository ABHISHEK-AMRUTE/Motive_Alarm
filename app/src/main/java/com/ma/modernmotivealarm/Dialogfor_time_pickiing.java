package com.ma.modernmotivealarm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;



import java.util.Calendar;

public class Dialogfor_time_pickiing extends AppCompatDialogFragment {

   int hour; int minute;
  TimePicker time1;

    private setalarm listner;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder bilder =new AlertDialog.Builder(getActivity());
        LayoutInflater infalt =getActivity().getLayoutInflater();
        View view =infalt.inflate(R.layout.timepicker,null);
        time1 = view.findViewById(R.id.timepicker);

        bilder.setView(view).setTitle("Pick up the Time").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY,time1.getCurrentHour());
                        calendar.set(Calendar.MINUTE,time1.getCurrentMinute());

                            hour = time1.getCurrentHour();
                            minute = time1.getCurrentMinute();

                            listner.taketime(hour ,minute);
                    }
                });

        return bilder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listner =(setalarm) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement Example");
        }

    }

    public interface take_the_time
    {
        void taketime(int hour, int minute);

    }

}
