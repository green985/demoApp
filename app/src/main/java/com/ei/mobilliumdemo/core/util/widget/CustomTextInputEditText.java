package com.ei.mobilliumdemo.core.util.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;

import com.ei.mobilliumdemo.core.util.KeyboardUtil;

import org.jetbrains.annotations.Contract;

import java.util.Objects;


/**
 * A custom {@link TextInputEditText} which is designed especially
 * if there are multiple views and should be arranged separately.
 * This also handles keyboard and focusable events according to input type.
 *
 * @author EiAppcompany
 */
public class CustomTextInputEditText extends TextInputEditText {

    private KeyboardUtil mKeyboardUtil;
    private ActionListener mActionListener;

    /**
     * Default constructor
     *
     * @param context a {@link Context}
     */
    public CustomTextInputEditText(Context context) {
        super(context);
        init(context);
    }

    /**
     * Default constructor
     *
     * @param context a {@link Context}
     * @param attrs   an {@link AttributeSet}
     */
    public CustomTextInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * Default constructor
     *
     * @param context      a {@link Context}
     * @param attrs        an {@link AttributeSet}
     * @param defStyleAttr default style attributes
     */
    public CustomTextInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * A method that initializes the custom layout with its attributes
     */
    @Contract("null->fail")
    private void init(@NonNull Context context) {
        setId(this.getId());
        setFocusableInTouchMode(true);
        setFocusable(true);

        mKeyboardUtil = new KeyboardUtil(context);
    }

    /**
     * A method that sniffs keyboard button and back press in order to take action
     *
     * @param keyCode represents back press button
     * @param event   represents the key has been released
     * @return {@code true} or {@code false}
     */
    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_UP) {
            mKeyboardUtil.hideKeyboard(this);
            setFocusable(false);
            setFocusableInTouchMode(true);
        }

        return super.onKeyPreIme(keyCode, event);
    }

    /**
     * A method that sniffs keyboard's done button in order to take action
     *
     * @param actionCode represents done button on the keyboard
     */
    @Override
    public void onEditorAction(int actionCode) {
        if (actionCode == EditorInfo.IME_ACTION_DONE
                || actionCode == EditorInfo.IME_ACTION_SEARCH) {
            mKeyboardUtil.hideKeyboard(this);
            setFocusable(false);
            setFocusableInTouchMode(true);
            mActionListener.onActionTriggered(Objects.requireNonNull(getText()).toString().toLowerCase());
        }else{
            super.onEditorAction(actionCode);
        }

    }

    /**
     * Sets listener
     *
     * @param actionListener represents an instance of {@link ActionListener}
     */
    public void setListener(@NonNull ActionListener actionListener) {
        this.mActionListener = actionListener;
    }

    /**
     * An interface which is responsible to deliver query
     */
    public interface ActionListener {

        /**
         * Gets called when action is done on the keyboard
         *
         * @param query stands for a query
         */
        void onActionTriggered(@Nullable String query);
    }
}