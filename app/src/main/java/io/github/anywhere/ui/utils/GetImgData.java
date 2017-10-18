package io.github.anywhere.ui.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by PCPC on 2017-06-06.
 */

public class GetImgData {

    private static final String TAG_RESULTS = "result";
    private static final String TAG_NAME = "name";
    private static final String TAG_VIEWCOUNT = "viewcount";

    private static final String TAG_PIC1 = "pic1";
    private static final String TAG_PIC2 = "pic2";
    private static final String TAG_PIC3 = "pic3";
    private static final String TAG_PIC4 = "pic4";
    private static final String TAG_PIC5 = "pic5";
    private static final String TAG_PIC6 = "pic6";
    private static final String TAG_PIC7 = "pic7";
    ArrayList<ImageData>imageDatas = new ArrayList<>();
    String ImgJson;


    public void getImageData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        Log.i("Json Data", json);
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }


            }

            @Override
            protected void onPostExecute(String result) {
                ImgJson = result;
                getImgPath();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

    public void getImgPath() {
        try {
            JSONObject jsonObj = new JSONObject(ImgJson);
            JSONArray ImgJson = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < ImgJson.length(); i++) {
                JSONObject c = ImgJson.getJSONObject(i);

                String name = c.getString(TAG_NAME);
                int viewcont = c.getInt(TAG_VIEWCOUNT);
                String pic1 = c.getString(TAG_PIC1);
                String pic2 = c.getString(TAG_PIC2);
                String pic3 = c.getString(TAG_PIC3);
                String pic4 = c.getString(TAG_PIC4);
                String pic5 = c.getString(TAG_PIC5);
                String pic6 = c.getString(TAG_PIC6);
                String pic7 = c.getString(TAG_PIC7);
                String [] picarray = {pic1 , pic2 , pic3 , pic4 , pic5 , pic6 , pic7};
                Log.i("Image Data List",picarray[1]);
                imageDatas.add(new ImageData(name, viewcont, picarray));
                imageDatas.get(i).setImgName(picarray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ImageData> getImgArray(){
        return imageDatas;
    }
    }




