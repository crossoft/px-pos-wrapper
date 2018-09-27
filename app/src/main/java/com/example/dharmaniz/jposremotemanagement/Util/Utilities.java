package com.example.dharmaniz.jposremotemanagement.Util;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.ProgressBar;

import com.example.dharmaniz.jposremotemanagement.R;

/**
 * Created by dharmaniz on 31/8/18.
 */

public class Utilities {
    static Dialog pdialog = null;
    public static void showProgressDialog(Activity mActivity) {
        pdialog = new Dialog(mActivity);
        pdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        pdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pdialog.setContentView(R.layout.dialog_progess);
        pdialog.setCanceledOnTouchOutside(false);
        ProgressBar circlePB = (ProgressBar) pdialog.findViewById(R.id.circlePB);
//        circlePB.getIndeterminateDrawable().setColorFilter(R.color.progess,
//                android.graphics.PorterDuff.Mode.MULTIPLY);

        pdialog.show();
    }
    public static void hideProgressDialog() {
        if (pdialog.isShowing()) {
            pdialog.dismiss();
        }
    }
}
