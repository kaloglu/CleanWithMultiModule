package com.kaloglu.boilerplate.utility;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public final class KeyboardUtil {

    /**
     * Private constructor for util class.
     */
    private KeyboardUtil() {
    }

    /**
     * Utility method for showing keyboard.
     *
     * @param view which has keyboard focus
     */
    public static void showKeyboard(View view) {
        getInputMethodManager(view.getContext())
                .showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * Utility method for hiding keyboard.
     *
     * @param view which has keyboard focus
     */
    public static void hideKeyboard(View view) {
        getInputMethodManager(view.getContext())
                .hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Utility method for check keyboard state.
     *
     * @param context which is current context
     */
    public static boolean isKeyboardShown(Context context) {
        return getInputMethodManager(context).isAcceptingText();
    }

    /**
     * Return a system-level {@link InputMethodManager} service
     * for accessing input methods.
     *
     * @param context the context in which system services are obtained from.
     * @return An {@link InputMethodManager InputMethodManager}
     */
    private static InputMethodManager getInputMethodManager(Context context) {
        return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }
}
