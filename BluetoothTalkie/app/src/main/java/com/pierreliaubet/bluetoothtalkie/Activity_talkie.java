package com.pierreliaubet.bluetoothtalkie;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by pierreliaubet on 17/03/2016.
 */
public class Activity_talkie extends AppCompatActivity {


    public BluetoothAdapter btAdpt;
    private ListView appareils;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talkie);
        appareils = (ListView) findViewById(R.id.appareils);
        btAdpt.startDiscovery();
        
    }
}
