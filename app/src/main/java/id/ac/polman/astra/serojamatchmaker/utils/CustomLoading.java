package id.ac.polman.astra.serojamatchmaker.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import id.ac.polman.astra.serojamatchmaker.R;

public class CustomLoading {
    Activity activity;
    AlertDialog dialog;
    TextView mDialogText;

    public CustomLoading(Activity layoutActivity) {
        this.activity=layoutActivity;
    }
    public void startLoading(String dialogtext){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.loader_widget, null);
        builder.setView(view);
        builder.setCancelable(false);
        mDialogText=view.findViewById(R.id.loaderText);
        mDialogText.setText(dialogtext);
        dialog = builder.create();
        dialog.show();
    }
    public void stopLoading(){
        dialog.dismiss();
    }
}
