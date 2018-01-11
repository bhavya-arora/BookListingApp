package com.bhavya.booklistingapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhavya Arora on 1/10/2018.
 */

public class QueryUtils {
    private static String json;
    public static final String TAG = "bhavya";

    public static List<book> fetchBooksData(String url){
        Log.i(TAG, "fetchBooksData: "+ url);
        ArrayList<book> arrayList = null;

        try{
            Thread.sleep(1000);
            URL Url = createUrl(url);
            json = makeHttpRequest(Url);

            arrayList = extractJson(json);

        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        return arrayList;
    }

    private static URL createUrl(String url){
        URL Url = null;
        try {
            Url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return Url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        String json = null;
        if(url != null){
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
        }

        if(httpURLConnection.getResponseCode() == 200){
            inputStream = httpURLConnection.getInputStream();
            json = streamToJson(inputStream);
        }
        return json;
    }

    private static String streamToJson(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if(inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while(line != null){
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }

    private static ArrayList<book> extractJson(String json) throws JSONException {
        String title = "title";
        String author = "author";
        String infoUrl = "infoUrl";
        String imageUrl = "imageUrl";
        String publisher = "publisher";

        ArrayList<book> bookList = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(json);
        JSONArray items = jsonObject.getJSONArray("items");
        for(int i=0; i < items.length(); i++){
            JSONObject jsonObject1 = items.getJSONObject(i);
            JSONObject volumeInfo = jsonObject1.getJSONObject("volumeInfo");
            title = volumeInfo.getString("title");
            if(volumeInfo.has("publisher")){
                publisher = volumeInfo.getString("publisher");
            }
            Log.i(TAG, "extractJson: ");
            Log.i(TAG, "extractJson: "+volumeInfo.has("authors"));
            if(volumeInfo.has("authors")){
                Log.i(TAG, "extractJson: ");
                JSONArray authors = volumeInfo.getJSONArray("authors");
                author = authors.getString(0);
            }
            infoUrl = volumeInfo.getString("infoLink");
            if(volumeInfo.has("imageLinks")){
                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                imageUrl = imageLinks.getString("smallThumbnail");
            }
            book bookItem = new book(title, author, infoUrl, imageUrl, publisher);
            bookList.add(bookItem);

        }
        return bookList;
    }

}
