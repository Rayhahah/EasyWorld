package com.rayhahah.easyworld.architecture.netstate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.rayhahah.libbase.BaseApp;
import com.rayhahah.libbase.R;
import com.rayhahah.libbase.utils.NetWorkUtil;

import java.util.Objects;

/**
 * Create by KunMinX at 19/8/5
 */
public class NetworkStateReceive extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Objects.equals(intent.getAction(), ConnectivityManager.CONNECTIVITY_ACTION)) {
            if (!NetWorkUtil.INSTANCE.isNetWorkConnected(BaseApp.getAppContext())) {
                Toast.makeText(context, context.getString(R.string.network_not_good), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
