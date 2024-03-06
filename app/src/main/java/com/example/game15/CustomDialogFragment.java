package com.example.game15;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment {
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder.setTitle("Победа").setMessage("Поздравляем вы собрали пятнашки\nДля закрытия окна нажмите ОК").create();
    }
}
