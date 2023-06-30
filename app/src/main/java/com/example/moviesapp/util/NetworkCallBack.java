package com.example.moviesapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

import androidx.annotation.NonNull;

public class NetworkCallBack extends ConnectivityManager.NetworkCallback {
    ConnectivityManager cm;
    NetworkRequest networkRequest;

    public NetworkCallBack(Context context){
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkRequest = new NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build();
    }

    @Override
    public void onAvailable(@NonNull Network network) {
        super.onAvailable(network);

    }

    @Override
    public void onUnavailable() {
        super.onUnavailable();

    }

    @Override
    public void onLost(@NonNull Network network) {
        super.onLost(network);
    }




    public void register(NetworkCallBack networkCallbackAbstract){
        cm.registerNetworkCallback(this.networkRequest,networkCallbackAbstract);
    }

    public void unRegister(NetworkCallBack networkCallbackAbstract){
        cm.unregisterNetworkCallback(networkCallbackAbstract);
    }
}
