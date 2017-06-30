package com.aiyaapp.camera.sdk.base;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import javax.net.ssl.HttpsURLConnection;

/**
 * Created by aiya on 2017/6/22.
 */

public class CUtils {

    public static String request(String url,String data,byte[] ret) throws IOException,
        NoSuchAlgorithmException, KeyManagementException, NoSuchProviderException {
        StringBuffer sb=new StringBuffer();
        URL CUrl=new URL(url);
        HttpsURLConnection connection=(HttpsURLConnection) CUrl.openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(10*1000);
        connection.setDoOutput(true);
        OutputStream os=connection.getOutputStream();
        os.write(data.getBytes("UTF-8"));
        os.flush();
        int code = connection.getResponseCode();
        if(code==HttpsURLConnection.HTTP_OK){
            BufferedReader is=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String buf;
            do {
                buf=is.readLine();
                if (buf==null){
                    break;
                }
                sb.append(buf);
            }while (true);
            System.out.print(sb.toString());
            is.close();
            if(ret!=null&&ret.length>0){
                ret[0]=0;
            }
            return sb.toString();
        }
        connection.disconnect();
        if(ret!=null&&ret.length>0){
            ret[0]=-1;
        }
        return null;
    }


}
