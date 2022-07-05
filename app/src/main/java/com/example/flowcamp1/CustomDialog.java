package com.example.flowcamp1;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CustomDialog extends Dialog {
    private  com.google.android.material.textfield.TextInputLayout txt_contents;
    private  com.google.android.material.textfield.TextInputLayout txt_phone;
    private Button shutdownClick;
    private String text_contents;
    private TextView resNameTextView;

    public CustomDialog(@NonNull Context context, String title, String address) {
        super(context);
        setContentView(R.layout.dialog);

        resNameTextView = findViewById(R.id.resNameTextView);
        resNameTextView.setText(title);

        txt_contents = findViewById(R.id.txt_contents);
        shutdownClick = findViewById(R.id.btn_shutdown);
        shutdownClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_contents = txt_contents.getEditText().getText().toString();
                Log.d("text_contents",text_contents);

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String sendMessage = title + "_" + address + "\n" + text_contents;
                intent.putExtra(Intent.EXTRA_TEXT, sendMessage);
                Intent shareIntent = Intent.createChooser(intent, "share");
                getContext().startActivity(shareIntent);
                dismiss();
            }
        });
    }
}
