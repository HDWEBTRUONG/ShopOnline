package com.example.pctruong.appbanhang.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.EditText;

import com.example.pctruong.appbanhang.R;

/**
 * Created by PCTruong on 15/06/2018.
 */

public class RoundedBorderedTextInputLayout extends TextInputLayout {
    private Context context;

    public RoundedBorderedTextInputLayout(Context context) {
        super(context);
        this.context = context;
    }

    public RoundedBorderedTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public RoundedBorderedTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();

        EditText editText = getEditText();
        if (editText != null) {
            editText.setBackground(ContextCompat.getDrawable(this.context, R.drawable.color));
            editText.setPadding(20,0,0,0);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void setError(@Nullable final CharSequence error) {
        super.setError(error);

        EditText editText = getEditText();
        if(editText != null) {
            editText.setBackground(ContextCompat.getDrawable(this.context, R.drawable.color));
        }
    }

}
