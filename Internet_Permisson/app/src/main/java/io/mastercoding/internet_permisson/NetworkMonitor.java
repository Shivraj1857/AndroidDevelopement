package io.mastercoding.internet_permisson;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

import java.util.HashSet;
import java.util.Set;

public class NetworkMonitor {

    public interface Listener {
        void onAvailable();
        void onLost();
    }

    private final ConnectivityManager cm;
    private final ConnectivityManager.NetworkCallback callback;
    private final Set<Listener> listeners = new HashSet<>();
    private boolean registered = false;

    public NetworkMonitor(Context context) {
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        callback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                if (hasInternet()) notifyAvailable();
            }
            @Override
            public void onLost(Network network) {
                if (!hasInternet()) notifyLost();
            }
        };
    }

    public void addListener(Listener l) { listeners.add(l); }
    public void removeListener(Listener l) { listeners.remove(l); }

    public void start() {
        if (registered) return;
        NetworkRequest req = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build();
        cm.registerNetworkCallback(req, callback);
        registered = true;
    }

    public void stop() {
        if (!registered) return;
        cm.unregisterNetworkCallback(callback);
        registered = false;
    }

    public boolean hasInternet() {
        if (cm == null) return false;
        Network active = cm.getActiveNetwork();
        if (active == null) return false;
        NetworkCapabilities caps = cm.getNetworkCapabilities(active);
        return caps != null && caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
    }

    private void notifyAvailable() {
        for (Listener l : listeners) l.onAvailable();
    }

    private void notifyLost() {
        for (Listener l : listeners) l.onLost();
    }
}









