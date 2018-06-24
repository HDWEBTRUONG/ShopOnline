package com.example.pctruong.appbanhang.view.CustomView;

import android.os.CountDownTimer;

import rx.functions.Func1;

/**
 * Created by PCTruong on 17/06/2018.
 */

public abstract class SearchTextWatcher extends SimpleTextWatcher {

    public static final int TEXT_CHANGED_DELAY_TIME = 500;
    private CountDownTimer countDownTimer;
    private int delayMilliseconds = TEXT_CHANGED_DELAY_TIME;
    private String searchKey = "";

    public SearchTextWatcher() {
        initTimer();
    }

    public SearchTextWatcher(int delayMilliseconds, Func1<String,Void> runnable) {
        this.delayMilliseconds = delayMilliseconds;
        initTimer();
    }

    private void initTimer() {
        countDownTimer = new SimpleCountDownTimer(delayMilliseconds, delayMilliseconds) {
            @Override
            public void onFinish() {
                onSearch(searchKey);
            }
        };
    }

    public abstract void onSearch(String key);

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        searchKey = s.toString();
        countDownTimer.cancel();
        countDownTimer.start();
    }
}
