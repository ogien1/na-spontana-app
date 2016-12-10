package com.skaminski.naspontana.facebook;

import android.net.Uri;
import android.util.Log;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

/**
 * Created by piotr on 12/10/16.
 */

public class FacebookUtils {

    private static final String TITLE = "NaSpontana";

    private static final String URL = "https://play.google.com/store/apps/dev?id=7908612043055486674";

    private static String buildDescription(String activityName, String date) {
        StringBuilder descrition = new StringBuilder("Moja zbliżająca się aktywność: ");
        descrition.append(activityName);
        descrition.append(", data: ");
        descrition.append(date);
        descrition.append(". Możecie dołączyć do mnie poprzez aplikację NaSpontana.");
        return descrition.toString();
    }

    public static void showPostOnWall(ShareDialog shareDialog, String activityName,
                                      String dateString) {
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle(TITLE)
                    .setContentDescription(buildDescription(activityName, dateString))
                    .setContentUrl(Uri.parse(URL))
                    .build();

            shareDialog.show(linkContent);
        } else {
            Log.e("Facebook", "Nie mozna otworzyc okna do publikacji posta");
        }
    }

}
