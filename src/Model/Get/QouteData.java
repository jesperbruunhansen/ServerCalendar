package Model.Get;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by jesperbruun on 29/11/14.
 */
public class QouteData {



    private static String readUrl(String urlString){
        try {
            BufferedReader reader = null;
            try {
                URL url = new URL(urlString);
                reader = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuffer buffer = new StringBuffer();
                int read;
                char[] chars = new char[1024];
                while ((read = reader.read(chars)) != -1)
                    buffer.append(chars, 0, read);

                return buffer.toString();
            } finally {
                if (reader != null)
                    reader.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getQuote(){
        String jsonQuoute = readUrl("http://dist-sso.it-kartellet.dk/quote/");
        return jsonQuoute;
    }



}