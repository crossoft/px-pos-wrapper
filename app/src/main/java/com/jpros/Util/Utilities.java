package com.jpros.Util;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.ProgressBar;
import com.jpros.R;


public class Utilities {
    static Dialog pdialog = null;
    public static void showProgressDialog(Activity mActivity) {
        pdialog = new Dialog(mActivity);
        pdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pdialog.setContentView(R.layout.dialog_progess);
        pdialog.setCanceledOnTouchOutside(false);
        ProgressBar circlePB = (ProgressBar) pdialog.findViewById(R.id.circlePB);

        pdialog.show();
    }
    public static void hideProgressDialog() {
        if (pdialog.isShowing()) {
            pdialog.dismiss();
        }
    }
}
