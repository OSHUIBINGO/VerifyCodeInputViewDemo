package com.verifycodeinputviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button verifyButton;
    VerifyCodeInputView verifyCodeInputView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verifyButton = findViewById(R.id.bu_verify);
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空内容
                verifyCodeInputView.setClear();

                Toast.makeText(getApplicationContext(),"清空内容", Toast.LENGTH_SHORT).show();
            }
        });

        verifyCodeInputView = findViewById(R.id.verify_code_view);
        verifyCodeInputView.setInputContentListener(new VerifyCodeInputView.InputContentListener() {
            @Override
            public void inputComplete() {
                //确认按钮变黄
                verifyButton.setBackgroundResource(R.drawable.input_verify_code_button2);

                //6位验证码输入完成监听
                Toast.makeText(getApplicationContext(), "6位验证码输入完成：" + verifyCodeInputView.getEditContent(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void contentChange() {
                //确认按钮变灰
                verifyButton.setBackgroundResource(R.drawable.input_verify_code_button1);

                //验证码内容改变监听
                Toast.makeText(getApplicationContext(),"验证码内容改变" + verifyCodeInputView.getEditContent(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}