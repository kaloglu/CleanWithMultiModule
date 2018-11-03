package com.kaloglu.boilerplate.utility;

import android.content.Context;
import android.content.res.Resources;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;

public class NavigationBarUtil {

    public static boolean hasDeviceNavigationBar(Context context) {
        boolean hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);

        return !hasMenuKey && !hasBackKey;
    }

    public static int getDeviceNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");

        return resourceId > 0 ? resources.getDimensionPixelSize(resourceId) : 0;
    }
}
