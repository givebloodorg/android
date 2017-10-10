package doesangue.doesangue.me.settings;

/**
 * Created by JM on 09/10/2017.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/***
 * This class manager the user session state and information
 */
public class SessionManager {

    private static final String SESSION_TOKEN = "doesangue.doesangue.me.settings.SESSION_TOKEN";
    private static final String TOKEN_TYPE = "doesangue.doesangue.me.settings.TOKEN_TYPE";

    // The Preferences to store data
    private final SharedPreferences preference;
    // To edit data inside the Preferences
    private final SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        this.preference = PreferenceManager.getDefaultSharedPreferences(context);
        this.editor = preference.edit();
    }

    public String getSessionToken() {
        return preference.getString(SESSION_TOKEN, "");
    }

    public void setSessionToken(String sessionToken) {
        editor.putString(SESSION_TOKEN, sessionToken)
                .apply();
    }

    public String getTokenType() {
        return preference.getString(TOKEN_TYPE, "");
    }

    public void setTokenType(String tokenType) {
        editor.putString(TOKEN_TYPE, tokenType)
        .apply();
    }
}
