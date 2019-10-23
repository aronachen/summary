package com.qst.giftems;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 简单对话框
 */
public class Dialogs {

    /**
     * 显示一个信息提示对话框
     * 
     * @param context
     *            Context
     * @param message
     *            信息
     * @param cancelable
     *            是否可取消
     * @param onOkListener
     *            确定按钮点击事件
     */
    public static void showSimpleDialog(Context context, String message,
            boolean cancelable, final OnOkListener onOkListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_simple, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        final AlertDialog dialog = builder.create();

        TextView messageTextView = view.findViewById(R.id.messageTextView);
        Button okButton = view.findViewById(R.id.okButton);
        Button cancelButton = view.findViewById(R.id.cancelButton);

        messageTextView.setText(message);

        okButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOkListener != null)
                    onOkListener.onOk();
                dialog.dismiss();
            }
        });

        dialog.setCancelable(cancelable);
        if (cancelable) {
            cancelButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });
        } else {
            cancelButton.setVisibility(View.GONE);
        }

        dialog.show();
    }

    public interface OnOkListener {
        void onOk();
    }
}
