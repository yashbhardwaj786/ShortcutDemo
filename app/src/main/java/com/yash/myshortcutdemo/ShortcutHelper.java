package com.yash.myshortcutdemo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.N_MR1;

public class ShortcutHelper {

    public static final String ACTION_APP_SHORTCUT = "ACTION_APP_SHORTCUT";

    public static void refresh(String type) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            Context context = Application.context;
            ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
            if (type.equalsIgnoreCase("Add")) {
                assert shortcutManager != null;
                shortcutManager.setDynamicShortcuts(getUserShortcuts());
            } else if (type.equalsIgnoreCase("Remove")) {
                assert shortcutManager != null;
                shortcutManager.removeAllDynamicShortcuts();
            }
        }
    }

    @RequiresApi(N_MR1)
    private static List<ShortcutInfo> getUserShortcuts() {

        Context context = Application.context;

        List<ShortcutInfo> shortcuts = new ArrayList<>();

        Intent intent = new Intent(context, SearchActivity.class);
        intent.setAction(ACTION_APP_SHORTCUT);
        Intent visitsIntent = new Intent(context, ScheduleVisitActivity.class);
        visitsIntent.setAction(ACTION_APP_SHORTCUT);


        ShortcutInfo shortcut = new ShortcutInfo.Builder(context, "find")
                .setShortLabel("Search")
                .setLongLabel("Search for properties")
                .setIcon(Icon.createWithResource(context, R.drawable.ic_launcher_background))
                .setIntent(intent).build();

        shortcuts.add(shortcut);

        ShortcutInfo shortcut1 = new ShortcutInfo.Builder(context, "scheduled_visit")
                .setShortLabel("Scheduled visits")
                .setLongLabel("Your scheduled visits")
                .setIcon(Icon.createWithResource(context, R.drawable.ic_launcher_background))
                .setIntent(visitsIntent).build();

        shortcuts.add(shortcut1);

        return shortcuts;

    }

    /*@RequiresApi(api = Build.VERSION_CODES.N_MR1)
    public static void reportShortcutUsed(String id) {

        String event = "APP_SHORTCUT_CLICKED_" + id.toUpperCase();
        Log.d(TAG, "reportShortcutUsed: " + event);

        Context context = Application.context;
        ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
        assert shortcutManager != null;
        shortcutManager.reportShortcutUsed(id);
    }*/

}
