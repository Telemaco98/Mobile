package com.android.ohara.telemaco.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

public class BackToLogin extends DialogFragment implements DialogInterface.OnClickListener {
    private BackListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );

        if (!(context instanceof BackListener)) throw new RuntimeException( "It isn't BackListener" );
        listener = (BackListener) context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        builder.setMessage( "Tem certeza que deseja voltar? Todos os dados preenchidos serão perdidos");
        builder.setNegativeButton( "Não", this );
        builder.setPositiveButton( "Sim", this );

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE && listener != null)
            listener.onBack();
    }

    public interface BackListener {
        public void onBack();
    }
}
