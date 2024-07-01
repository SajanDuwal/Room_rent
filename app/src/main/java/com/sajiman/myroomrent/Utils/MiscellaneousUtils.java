package com.sajiman.myroomrent.Utils;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.sajiman.myroomrent.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiscellaneousUtils {

    public static String capitalize(String capString) {
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()) {
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }
        return capMatcher.appendTail(capBuffer).toString();
    }

    public static void AnimationFadeIn(Context context, NestedScrollView nestedScrollView) {
        Animation animationFadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        nestedScrollView.setAnimation(animationFadeIn);
    }

    public static void AnimationFadeInDialog(Context context, View DialogView) {
        Animation animationFadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        DialogView.setAnimation(animationFadeIn);
    }

    public static String getMonthName(int monthPosition) {
        String monthName = null;
        switch (monthPosition) {
            case 0:
                monthName = "Never Paid";
                break;

            case 1:
                monthName = "बैशाख";
                break;

            case 2:
                monthName = "जेठ";
                break;

            case 3:
                monthName = "असार";
                break;

            case 4:
                monthName = "श्रावण";
                break;

            case 5:
                monthName = "भदौ";
                break;

            case 6:
                monthName = "आश्विन";
                break;

            case 7:
                monthName = "कार्तिक";
                break;

            case 8:
                monthName = "मंसिर";
                break;

            case 9:
                monthName = "पुष";
                break;

            case 10:
                monthName = "माघ";
                break;

            case 11:
                monthName = "फाल्गुन";
                break;

            case 12:
                monthName = "चैत्र";
                break;
        }
        return monthName;
    }
}
