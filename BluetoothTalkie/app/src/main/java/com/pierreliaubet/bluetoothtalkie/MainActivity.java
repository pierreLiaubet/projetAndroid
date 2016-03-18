package com.pierreliaubet.bluetoothtalkie;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public BluetoothAdapter btAdpt;
    private Switch btManager;
    private EditText pseudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btAdpt = BluetoothAdapter.getDefaultAdapter();
        btManager = (Switch) findViewById(R.id.bt);
        if (btAdpt.isEnabled()) {
            btManager.toggle();
        }
        btManager.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    btAdpt.enable();
                } else
                    btAdpt.disable();
            }
        });
        pseudo = (EditText) findViewById(R.id.pseudo);
        pseudo.setText(btAdpt.getName());
        pseudo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.equals("")) {
                    btAdpt.setName(String.valueOf(pseudo.getText()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void demarrer(View sender)
    {
        if (btAdpt.isEnabled())
            startActivity(new Intent(this,Activity_talkie.class));
        else {
            afficherAlerteBT();
        }
    }

    public void afficherAlerteBT() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Attention");
        builder.setMessage("Vous devez activer le bluetooth pour commencer la communication !");
        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
