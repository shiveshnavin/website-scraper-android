package in.semibit.webviewautomation;

import android.webkit.JavascriptInterface;

import com.google.common.base.Strings;
import com.semibit.ezandroidutils.EzUtils;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class DataSync {

    Map<String ,Profile> data = new HashMap<>();
    Queue<Profile> profilesToScrape = new ArrayDeque<>();
    int profilesScraped = 0;

    int lastSize = 0;

    @JavascriptInterface
    public long getScrollDelay(){
        return 2000;
    }

    @JavascriptInterface
    public boolean isLoadedEnoughProfiles(){
        return data.size() > 100;
    }

    @JavascriptInterface
    public String getNextProfileToScrape(){
        if(profilesToScrape.isEmpty()){
            if(profilesScraped > 0){

                return null;
            }
            else
            {
                profilesToScrape.addAll(data.values());
            }
        }

        return profilesToScrape.remove().getUrl();
    }

    @JavascriptInterface
    public void updateProfile(String url, String passedyear,String passedFrom,String passedLocation){
       Profile profile = data.get(url);
       if(profile == null)
           return;
        profile.setPassoutYear(passedyear);
        profile.setPassoutFrom(passedFrom);
        profile.setPassoutLocation(passedLocation);
        data.put(url,profile);
        profilesScraped++;
    }

    @JavascriptInterface
    public void addProfileUrl(String url,
                              String name,
                              String role,
                              String location,
                              String img){

        url = "https://www.linkedin.com/"+ url;
        if(!Strings.isNullOrEmpty(url) && !data.containsKey(url)){
            Profile p = new Profile.Builder()
                    .url(url)
                    .name(name)
                    .role(role)
                    .location(location)
                    .img(img)
                    .build();
            data.put(url,p);
            EzUtils.e("WebView",p);
        }
        if(lastSize != data.size()){
            lastSize = data.size();
            EzUtils.e("WebView","NEW SIZE = "+data.size());

        }

    }


}