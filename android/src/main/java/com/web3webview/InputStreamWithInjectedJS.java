
package com.web3webview;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.*;
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class InputStreamWithInjectedJS extends InputStream {
    private InputStream pageIS;
    private InputStream scriptIS;
    private Charset charset;
    private static final String TAG = "InputStreamWithInjectedJS";
    private static Map<Charset, String> script = new HashMap<>();
    private boolean hasJS = false;
    private boolean headWasFound = false;
    private boolean scriptWasInjected = false;
    private boolean openingHeadFound = false;
    private StringBuffer contentBuffer = new StringBuffer();
    private static Charset getCharset(String charsetName) {
        Charset cs = UTF_8;
        try {
            if (charsetName != null) {
                cs = Charset.forName(charsetName);
            }
        } catch (UnsupportedCharsetException e) {
            Log.d("CustomWebview", "wrong charset: " + charsetName);
        }
        return cs;
    }
    private static InputStream getScript(Charset charset) {
        String js = script.get(charset);
        if (js == null) {
            String defaultJs = script.get(UTF_8);
            js = new String(defaultJs.getBytes(UTF_8), charset);
            script.put(charset, js);
        }
        return new ByteArrayInputStream(js.getBytes(charset));
    }
    InputStreamWithInjectedJS(InputStream is, String js, Charset charset, Context c) {
        if (js == null) {
            this.pageIS = is;
        } else {
            this.hasJS = true;
            this.charset = charset;
            Charset cs = UTF_8;
            String jsScript = "<script>" + js + "</script>";
            script.put(cs, jsScript);
            this.pageIS = is;
        }
    }
    @Override
    public int read() throws IOException {
        if (scriptWasInjected || !hasJS) {
            return pageIS.read();
        }

        // NOW I NEED TO FIX THIS SHIT

        if (!scriptWasInjected && headWasFound) {
            int nextByte = scriptIS.read();
            if (nextByte == -1) {
                scriptIS.close();
                scriptWasInjected = true;
                return pageIS.read();
            } else {
                return nextByte;
            }
        }
        if (!headWasFound) {
            int nextByte = pageIS.read();
            char nextByteStr =  (char) nextByte;
            contentBuffer.append(nextByteStr);
            int bufferLength = contentBuffer.length();
            String headString = "<head";
            if(openingHeadFound){
                 if(nextByte == 62){
                    this.scriptIS = getScript(this.charset);
                    headWasFound = true;
                 }
            } else {
                boolean isLetterD = (nextByte == 68 || nextByte == 100);
                if (isLetterD  && bufferLength >= 6) {
                    String stringToMatch = contentBuffer.substring(bufferLength - 5).toLowerCase();
                    if (stringToMatch.contains(headString)) {
                        openingHeadFound = true;
                    }
                }
            }
            return nextByte;
        }
        return pageIS.read();
    }
}
