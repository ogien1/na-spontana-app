package com.skaminski.naspontana;

import android.content.Context;

import com.github.brunodles.simplepreferences.lib.ActivePreferences;
import com.github.brunodles.simplepreferences.lib.Property;

/**
 * Created by skaminski on 09.12.2016.
 */

public class TokenSave extends ActivePreferences {
    @Property
    public String json;
    @Property
    public String friendsList;

    public TokenSave(Context context) {
        super(context);
    }
}
