package com.lh.nexusunsky.baselib.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.lh.nexusunsky.baselib.base.context.AppContext;
import com.lh.nexusunsky.baselib.log.Logger;


/**
 * @author Nexusunsky
 */
public class UIHelper {

    // =============================================================tools
    // methods

    /**
     * dip转px
     */
    public static int dipToPx(float dip) {
        return (int) (dip * AppContext.getResource().getDisplayMetrics().density + 0.5f);
    }

    /**
     * px转dip
     */
    public static int pxToDip(float pxValue) {
        final float scale = AppContext.getResource().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值
     */
    public static int sp2px(float spValue) {
        final float fontScale = AppContext.getResource().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取屏幕分辨率：宽
     */
    public static int getScreenWidthPix(@Nullable Context context) {
        if (context == null)
            context = AppContext.getAppContext();
        int width = context.getResources().getDisplayMetrics().widthPixels;
        return width;
    }

    /**
     * 获取屏幕分辨率：高
     */
    public static int getScreenHeightPix(@Nullable Context context) {
        if (context == null)
            context = AppContext.getAppContext();
        int height = context.getResources().getDisplayMetrics().heightPixels;
        return height;
    }

    /**
     * 获取状态栏的高度
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources()
                    .getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 隐藏软键盘
     */
    public static void hideInputMethod(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) view.getContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e);
        }
    }

    public static void hideInputMethod(final View view, long delayMillis) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideInputMethod(view);
            }
        }, delayMillis);
    }

    /**
     * 显示软键盘
     */
    public static void showInputMethod(View view) {
        if (view == null) {
            return;
        }
        if (view instanceof EditText) {
            view.requestFocus();
        }
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            boolean success = imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            Logger.i("showSoftKeyboard", " isSuccess   >>>   " + success);
        }
    }

    /**
     * 显示软键盘
     */
    public static void showInputMethod(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 多少时间后显示软键盘
     */
    public static void showInputMethod(final View view, long delayMillis) {
        if (view != null)
        // 显示输入法
        {
            view.postDelayed(new Runnable() {

                @Override
                public void run() {
                    UIHelper.showInputMethod(view);
                }
            }, delayMillis);
        }
    }

    /**
     * =============================================================
     * 资源工具
     */
    public static int getResourceColor(int colorResId) {
        try {
            return AppContext.getResource().getColor(colorResId);
        } catch (Exception e) {
            return Color.TRANSPARENT;
        }
    }
}
