package com.pierreliaubet.bluetoothtalkie;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Set;

/**
 * Created by pierreliaubet on 17/03/2016.
 */
public class Activity_talkie extends AppCompatActivity {


    public BluetoothAdapter btAdpt;
    private ArrayAdapter<String> mArrayAdapter;
    Set<BluetoothDevice> pairedDevices;
    private ListView appareils;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talkie);
        appareils = (ListView) findViewById(R.id.appareils);
        btAdpt = BluetoothAdapter.getDefaultAdapter();
        testAcces();
        checkPairedDevices();

    }

    private void checkPairedDevices() {
        pairedDevices = btAdpt.getBondedDevices();
        if (pairedDevices.size() > 0) {
            //On le laisse chosir
        }
        for (BluetoothDevice device : pairedDevices) {
            mArrayAdapter.add(device.getName() + "\n"+device.getAddress());
        }
    }

    private void testAcces() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
        } else {
            btAdpt.startDiscovery();
        }
    }

    //a ce qui parait c'est super important ça
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            btAdpt.startDiscovery();

        } else {
            //on s'en tape un peut du else
        }

    }
}
