package com.pierreliaubet.bluetoothtalkie.model;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by pierreliaubet on 18/03/2016.
 */
public class Server extends Thread {
    public BluetoothAdapter btAdpt;
    private UUID uUID;
    private final BluetoothServerSocket mmServerSocket;
    public Server(UUID uuid) {
        // Use a temporary object that is later assigned to mmServerSocket,
        // because mmServerSockets is final
        BluetoothServerSocket tmp = null;
        try {
            // MY_UUID is the app's UUID string, also used by the client code
            tmp = btAdpt.listenUsingRfcommWithServiceRecord("serveurTalkie",uUID);
        } catch (IOException e) { }
        mmServerSocket = tmp;
    }

    public void run() {
        BluetoothSocket socket = null;
        // Keep listening until exception occurs or a socket is returned
        while (true) {
            try {
                socket = mmServerSocket.accept();
            } catch (IOException e) {
                break;
            }
            // If a connection was accepted
            if (socket != null) {
                // Do work to manage the connection (in a separate thread)

                //Surement TheConnectedThread.run, mais pas sûr
                manageConnectedSocket(socket); //Normal que ce soit rouge pour l'instant
                try {
                    mmServerSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /** Will cancel the listening socket, asnd cause the thread to finish */
    public void cancel() {
        try {
            mmServerSocket.close();
        } catch (IOException e) { }
    }
}