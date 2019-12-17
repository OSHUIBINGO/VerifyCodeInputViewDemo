package com.verifycodeinputviewdemo;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class VerifyCodeInputView extends RelativeLayout {
    private EditText editText;
    private TextView[] textViews;
    private View[] views;
    private final int MAX = 6;
    private String inputContent;

    private InputContentListener inputContentListener;

    public VerifyCodeInputView(Context context) {
        this(context, null);
    }

    public VerifyCodeInputView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerifyCodeInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //加载布局文件
        View view= View.inflate(context, R.layout.view_verify_code_input, this);

        //初始化布局ID
        textViews = new TextView[MAX];
        textViews[0] = view.findViewById(R.id.tv_0);
        textViews[1] = view.findViewById(R.id.tv_1);
        textViews[2] = view.findViewById(R.id.tv_2);
        textViews[3] = view.findViewById(R.id.tv_3);
        textViews[4] = view.findViewById(R.id.tv_4);
        textViews[5] = view.findViewById(R.id.tv_5);

        views = new View[MAX];
        views[0] = view.findViewById(R.id.view_0);
        views[1] = view.findViewById(R.id.view_1);
        views[2] = view.findViewById(R.id.view_2);
        views[3] = view.findViewById(R.id.view_3);
        views[4] = view.findViewById(R.id.view_4);
        views[5] = view.findViewById(R.id.view_5);

        editText = view.findViewById(R.id.verify_code_input_edit_text);

        //隐藏光标
        editText.setCursorVisible(false);

        //监听输入的状态
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //获取输入框内容
                inputContent = editText.getText().toString();

                if (inputContentListener != null) {

                    //如果输入长度等于字符长度最大值
                    if (inputContent.length() == MAX) {
                        //回调输入完成监听
                        inputContentListener.inputComplete();
                    } else {
                        //否则回调输入内容改变监听
                        inputContentListener.contentChange();
                    }
                }

                //每次输入框内容改变后，把输入框的内容循环赋值给TextView,并改变下划线颜色
                for (int i = 0; i < MAX; i++) {

                    //把输入框的内容循环赋值给TextView
                    if (i < inputContent.length()) {
                        textViews[i].setText(String.valueOf(inputContent.charAt(i)));
                    } else {
                        textViews[i].setText("");
                    }

                    //并改变下划线颜色
                    if (i == inputContent.length()-1){
                        views[i].setBackgroundColor(getContext().getColor(R.color.colorAccent));
                    }else {
                        views[i].setBackgroundColor(getContext().getColor(R.color.colorPrimary));
                    }
                }
            }
        });
    }


    //获取内容
    public String getEditContent() {
        return inputContent;
    }

    //内容清空
    public void setClear() {
        editText.getText().clear();
    }

    public void setInputContentListener(InputContentListener inputContentListener) {
        this.inputContentListener = inputContentListener;
    }

    public interface InputContentListener {

        //输入框完成回调
        void inputComplete();

        //输入框改变回调
        void contentChange();
    }
}
