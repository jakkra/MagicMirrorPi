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
import java.util.TimeZone;

/**
 * Created by jakkra on 2016-06-27.
 */

public class NodeReminder {

    public void postReminder(String title, Date date, String email) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost("https://radiant-wave-58367.herokuapp.com/api/reminder/create");
            httppost.setHeader("x-access-token", "eyJhbGciOiJIUzI1NiJ9.aWpha2tyYUBnbWFpbC5jb20.kukrD_C1oJizmCvBc5-UOoUJBevMKC1o0BXRChidL5E");
            StringEntity input = new StringEntity("{\"title\":\"" + title + "\",\"time\":\"" + df.format(date) + "\",\"reminderActive\":\"" + true + "\"}");
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

    public void getReminders() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet("https://radiant-wave-58367.herokuapp.com/api/reminder/list");
            httpGet.setHeader("x-access-token", "eyJhbGciOiJIUzI1NiJ9.aWpha2tyYUBnbWFpbC5jb20.kukrD_C1oJizmCvBc5-UOoUJBevMKC1o0BXRChidL5E");

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

    public void postTemperature(String temp) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost("https://radiant-wave-58367.herokuapp.com/api/temperature");
            httppost.setHeader("x-access-token", "eyJhbGciOiJIUzI1NiJ9.aWpha2tyYUBnbWFpbC5jb20.kukrD_C1oJizmCvBc5-UOoUJBevMKC1o0BXRChidL5E");
            StringEntity input = new StringEntity("{\"temperature\":\"" + temp + "\"}");
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

    public void postMotion() throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost("https://radiant-wave-58367.herokuapp.com/api/surveillance");
            httppost.setHeader("x-access-token", "eyJhbGciOiJIUzI1NiJ9.aWpha2tyYUBnbWFpbC5jb20.kukrD_C1oJizmCvBc5-UOoUJBevMKC1o0BXRChidL5E");
            StringEntity input = new StringEntity("{\"time\":\"" + df.format(new Date()) + "\"}");
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

    public static void main(String[] args) {
        NodeReminder n = new NodeReminder();
        try {
            //n.postTemperature("20");
            //n.getReminders();
            n.postMotion();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
