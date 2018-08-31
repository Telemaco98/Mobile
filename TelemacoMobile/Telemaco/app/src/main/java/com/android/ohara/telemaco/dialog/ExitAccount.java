package com.android.ohara.telemaco.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

public class ExitAccount extends DialogFragment implements DialogInterface.OnClickListener {
    private SingoutListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        if (!(context instanceof  SingoutListener)) throw new RuntimeException("");
        listener = (SingoutListener) context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        builder.setMessage( "Deseja sair da sua conta?");
        builder.setNegativeButton( "Não", this );
        builder.setPositiveButton( "Sim", this );

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE && listener != null)
            listener.onSingOut();
    }

    public interface SingoutListener {
        public void onSingOut();
    }
}
