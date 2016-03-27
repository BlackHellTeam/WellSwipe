package com.well.swipe.tools;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;

import com.well.swipe.R;

/**
 * Created by mingwei on 3/27/16.
 */
public class SwipeBluetooth {

    BluetoothAdapter adapter = null;

    private volatile static SwipeBluetooth mInstance;

    private SwipeBluetooth() {
        adapter = BluetoothAdapter.getDefaultAdapter();
    }

    public static SwipeBluetooth getInstance() {
        if (mInstance == null) {
            synchronized (SwipeBluetooth.class) {
                if (mInstance == null) {
                    mInstance = new SwipeBluetooth();
                }
            }
        }
        return mInstance;
    }

    public void changeState() {
        if (adapter.getState() == BluetoothAdapter.STATE_OFF) {
            adapter.enable();
        } else if (adapter.getState() == BluetoothAdapter.STATE_ON) {
            adapter.disable();
        }
    }

    public BitmapDrawable getDrawableState(Context context) {
        switch (adapter.getState()) {
            case BluetoothAdapter.STATE_OFF:
                return (BitmapDrawable) context.getResources().getDrawable(R.drawable.ic_bluetooth_off);
            case BluetoothAdapter.STATE_ON:
                return (BitmapDrawable) context.getResources().getDrawable(R.drawable.ic_bluetooth_on);
        }
        return (BitmapDrawable) context.getResources().getDrawable(R.drawable.ic_bluetooth_off);
    }

}
