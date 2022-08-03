package in.semibit.webviewautomation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.semibit.ezandroidutils.EzUtils;
import com.semibit.ezandroidutils.interfaces.GenricCallback;
import com.semibit.ezandroidutils.views.AdvancedWebView;

import java.util.ArrayDeque;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    String SEACH_KEYWORD = "engineer at accenture bangalore";

    AdvancedWebView myWebView;
    Queue<GenricCallback> jsActions = new ArrayDeque<>();

    WebViewClient worker = new WebViewClient() {

        @Override
        public void onPageFinished(WebView view, String url) {
            JSLibrary.injectJS(myWebView);
            super.onPageFinished(view, url);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView = findViewById(R.id.web);
        worker = new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                if (!jsActions.isEmpty()) {
                    jsActions.remove().onStart();
                }
            }
        };

        myWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.e("WebView", "[" + consoleMessage.lineNumber() + "] " + consoleMessage.message());
                return true;
            }
        });
        myWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                EzUtils.e("WebView", "Loaded " + url);
                JSLibrary.injectJS(myWebView);
                worker.onPageFinished(view, url);
            }
        });
        DataSync dataSync = new DataSync();
        myWebView.addJavascriptInterface(dataSync, "JSInterface");



        myWebView.loadUrl("https://www.linkedin.com/login");
        jsActions.add(() -> {
            if(myWebView.getUrl().contains("/feed")){
                myWebView.loadUrl("https://www.linkedin.com/feed/");
            }
            else {
                doAction(() -> "login('"+Credentials.user+"','"+Credentials.pwd+"');", null);
            }
            jsActions.add(()->{
                myWebView.loadUrl("https://www.linkedin.com/mwlite/search/results/all?origin=TYPEAHEAD_ESCAPE_HATCH&keywords="+SEACH_KEYWORD);
                jsActions.add(() -> {

                    doAction(() -> "scrapProfiles();", null);

                });


            });


        });

    }


    public void doAction(JSAction jsAction, JSComplete jsComplete) {
        if (jsComplete == null)
            jsComplete = (result) -> {
            };
        JSComplete finalJsComplete = jsComplete;
        myWebView.evaluateJavascript(jsAction.getJS(), finalJsComplete::onJSComplete);
    }


    @Override
    public void onBackPressed() {
        if(myWebView.canGoBack()){
            myWebView.onBackPressed();
        }else {
            super.onBackPressed();
        }
    }
}


