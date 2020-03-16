package com.rayhahah.easyworld.architecture.netstate;

import android.content.IntentFilter;
import android.net.ConnectivityManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import static java.util.Objects.requireNonNull;

/**
 * Create by KunMinX at 19/10/11
 */
public class NetworkStateManager implements DefaultLifecycleObserver {

    private static final NetworkStateManager S_MANAGER = new NetworkStateManager();
    public final UnPeekLiveData<NetState> mNetworkStateCallback = new UnPeekLiveData<>();
    private NetworkStateReceive mNetworkStateReceive;

    private NetworkStateManager() {
    }

    public static NetworkStateManager getInstance() {
        return S_MANAGER;
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        mNetworkStateReceive = new NetworkStateReceive();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        if (owner instanceof AppCompatActivity) {
            ((AppCompatActivity) owner).registerReceiver(mNetworkStateReceive, filter);
        } else if (owner instanceof Fragment) {
            requireNonNull(((Fragment) owner).getActivity())
                    .registerReceiver(mNetworkStateReceive, filter);
        }
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        if (owner instanceof AppCompatActivity) {
            ((AppCompatActivity) owner).unregisterReceiver(mNetworkStateReceive);
        } else if (owner instanceof Fragment) {
            requireNonNull(((Fragment) owner).getActivity())
                    .unregisterReceiver(mNetworkStateReceive);
        }
    }
}
