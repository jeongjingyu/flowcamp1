package com.example.flowcamp1;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CustomDialog extends Dialog {
    private  com.google.android.material.textfield.TextInputLayout txt_contents;
    private  com.google.android.material.textfield.TextInputLayout txt_phone;
    private Button shutdownClick;

    public CustomDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog);

        txt_contents = findViewById(R.id.txt_contents);
        txt_phone = findViewById(R.id.txt_phone);
        shutdownClick = findViewById(R.id.btn_shutdown);
        shutdownClick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d("txt", txt_phone.getEditText().toString());
                dismiss();
            }
        });
    }
}
