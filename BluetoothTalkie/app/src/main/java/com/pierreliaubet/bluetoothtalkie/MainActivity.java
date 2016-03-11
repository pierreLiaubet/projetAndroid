package com.pierreliaubet.bluetoothtalkie;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
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
        if (btAdpt.isEnabled())
        {
            btManager.toggle();
        }
        btManager.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    btAdpt.enable();
                }
                else
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
                if (!s.equals(""))
                {
                    btAdpt.setName(String.valueOf(pseudo.getText()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }





}
