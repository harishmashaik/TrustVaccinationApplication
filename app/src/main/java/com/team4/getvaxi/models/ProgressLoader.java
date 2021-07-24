package com.team4.getvaxi.models;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

import com.team4.getvaxi.R;

public class ProgressLoader {

  Activity activity;
  AlertDialog dialog;

  public ProgressLoader(Activity myActivity) {
    this.activity = myActivity;
  }

  public void StartProgressLoader() {
    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
    LayoutInflater inflater = activity.getLayoutInflater();
    builder.setView(inflater.inflate(R.layout.progress_loader, null));
    builder.setCancelable(true);

    dialog = builder.create();
    dialog.show();
  }

  public void stopProgresBar() {
    dialog.dismiss();
  }
}
