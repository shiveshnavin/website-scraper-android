package in.semibit.webviewautomation;

import android.util.Base64;
import android.webkit.WebView;

import java.io.InputStream;
import java.net.URLEncoder;

public class JSLibrary {

    public static void injectJS(WebView webView) {
        injectFile(webView,"jquery.js");
        injectFile(webView,"library.js");
    }
    private static void injectFile(WebView webView,String filename){
        try {
            InputStream inputStream = webView.getContext().getAssets().open(filename);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();

            String js = new String(buffer, "UTF-8");
            // preserve non-english letters
            String uriEncoded = URLEncoder.encode(js, "UTF-8").replace("+", "%20");

            String encoded = Base64.encodeToString(uriEncoded.getBytes(), Base64.NO_WRAP);
//            webView.loadUrl("javascript:(function() {" +
//                    "var parent = document.getElementsByTagName('head').item(0);" +
//                    "var script = document.createElement('script');" +
//                    "script.type = 'text/javascript';" +
//                    // don't forget to use decodeURIComponent after base64 decoding
//                    "script.innerHTML = decodeURIComponent(window.atob('" + encoded + "'));" +
//                    "parent.appendChild(script)" +
//                    "})()");

            webView.evaluateJavascript(js,(s)->{});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
