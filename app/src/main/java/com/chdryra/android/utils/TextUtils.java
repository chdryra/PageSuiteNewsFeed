package com.chdryra.android.utils;

import android.text.Html;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: Rizwan Choudrey
 * On: 02/07/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class TextUtils {
    public static String removeScriptsAndClean(String text) {
        return clean(removeScripts(text));
    }

    public static String clean(String pre) {
        return pre.replace("&nbsp;", " ");
    }

    public static String removeScripts(String text) {
        String re = "(<script.*</script>)";

        Pattern pattern = Pattern.compile(re);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) return text.replace(matcher.group(1), "");

        return null;
    }

    public static Spanned fromHtml(String html) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(html);
        }
    }
}
