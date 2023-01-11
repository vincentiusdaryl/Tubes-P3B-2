package com.pppb.if_apps;

import android.os.Handler;
import android.os.Message;

import com.pppb.if_apps.View.FragmentLogin;

public class UIHandler extends Handler {
    protected final static int MSG_START_COUNTER = 0;
    protected final static int MSG_WAIT_COUNTER = 1;
    //    protected final static int LOGIN_ATTEMPT = 1;
    protected fragment_ss splashFragment;
    protected FragmentLogin loginFragment;

    // CONSTRUCTOR
    public UIHandler (fragment_ss splashFragment, FragmentLogin loginFragment) {
        this.splashFragment = splashFragment;
        this.loginFragment = loginFragment;
    }

    // HANDLE MESSAGE
    @Override
    public void handleMessage (Message msg) {
        if (msg.what == UIHandler.MSG_START_COUNTER) {
            this.splashFragment.showCounting((int)msg.obj);
            // JIKA COUNTER MENCAPAI 100, MAKA GANTI HALAMAN KE HALAMAN LOGIN
            if (this.splashFragment.counter == 100) {
                this.splashFragment.changePage(1);
            }
        }else if (msg.what == UIHandler.MSG_WAIT_COUNTER) {
        }
    }

    // DIPANGGIL OLEH INCREASING_COUNTER_THREAD (SEBAGAI PASSING KE UI THREAD) --> SPLASH SCREEN
    public void startIncreasing (int counter) {
        Message msg = new Message();
        msg.what = MSG_START_COUNTER;
        msg.obj = counter;
        this.sendMessage(msg);
    }

    // DIPANGGIL OLEH LOGIN_WAIT_THREAD -- > LOGIN WAIT TIME
    public void displayWaitCounter (int counter) {
        Message msg = new Message();
        msg.what = MSG_WAIT_COUNTER;
        msg.obj = counter;
        this.sendMessage(msg);
    }


    public void sentToken (String token) {
        this.loginFragment.setToken(token);
    }

}
