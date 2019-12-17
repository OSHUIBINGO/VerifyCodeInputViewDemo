package com.verifycodeinputviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatEditText;


public class VerifyCodeEditText extends AppCompatEditText {

    private long lastTime = 0;

    public VerifyCodeEditText(Context context) {
        super(context);
    }

    public VerifyCodeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerifyCodeEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        //设置光标始终在最后
        this.setSelection(this.getText().length());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //拦截双击事件，防止连续双击出现复制粘贴的选项
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //根据当前点击和上一次点击的时间间隔判断是否是双击
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastTime < 500) {
                    lastTime = currentTime;
                    return true;
                } else {
                    lastTime = currentTime;
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
