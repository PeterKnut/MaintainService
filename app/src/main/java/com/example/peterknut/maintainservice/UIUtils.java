package com.example.peterknut.maintainservice;

import android.widget.Toast;


/**
 * UI通用方法类
 */
public class UIUtils {
    private static Toast mToast;

    public static final void toastLongMessage(final String message) {
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                    mToast = null;
                }
                mToast = Toast.makeText(DemoApp.getApplication(), message,
                        Toast.LENGTH_LONG);
                // mToast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                mToast.show();
            }
        });
    }


    public static final void toastShortMessage(final String message) {
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                    mToast = null;
                }
                mToast = Toast.makeText(DemoApp.getApplication(), message,
                        Toast.LENGTH_SHORT);
                // mToast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                mToast.show();
            }
        });
    }

}
