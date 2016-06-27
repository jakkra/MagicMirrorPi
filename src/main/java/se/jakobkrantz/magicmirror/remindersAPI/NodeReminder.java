package se.jakobkrantz.magicmirror.remindersAPI;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jakkra on 2016-06-27.
 */

public class NodeReminder {

    public void postReminder(String title, Date date, String email) throws IOException {
        System.out.println(date);
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss'Z'");
        System.out.println(df.format(date));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost("https://radiant-wave-58367.herokuapp.com/reminder");

            StringEntity input = new StringEntity("{\"title\":\"" + title + "\",\"time\":\"" + df.format(date) + "\",\"email\":\"" + email + "\"}");
            input.setContentType("application/json");
            httppost.setEntity(input);

            System.out.println("Executing request: " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println(response.getStatusLine());
                System.out.println(EntityUtils.toString(response.getEntity()));
            } finally {
                response.close();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
        }
    }
    public void getReminders(String email) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet("http://localhost:5000/reminders?email=" + email);

            System.out.println("Executing request: " + httpGet.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                System.out.println(response.getStatusLine());
                System.out.println(EntityUtils.toString(response.getEntity()));
            } finally {
                response.close();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
        }
    }
}
