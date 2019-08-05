package com.ma.modernmotivealarm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class dialog_name extends AppCompatDialogFragment {

    private EditText takename;

    private setalarm listner;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder bilder =new AlertDialog.Builder(getActivity());
        LayoutInflater infalt =getActivity().getLayoutInflater();
        View view =infalt.inflate(R.layout.dialog_edit_name,null);
        bilder.setView(view).setTitle("Edit name").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nametext = takename.getText().toString();
                        listner.applyteaxt(nametext);
                    }
                });

        takename = view.findViewById(R.id.editText);

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

    public interface exampledialoglistner
    {
        void applyteaxt(String name);

    }
}
