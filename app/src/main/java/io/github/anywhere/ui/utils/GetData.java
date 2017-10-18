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

public class GetData {


    String myJSON;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_NAME = "name";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_TELNUM = "telnum";
    private static final String TAG_VIEWCOUNT = "viewcount";
    private static final String TAG_LATITUDE = "latitude";
    private static final String TAG_LONGITUDE = "lognitude";
    private static final String TAG_ALTITUDE = "altitude";
    private static final String TAG_LOGO = "logo";

    ArrayList<DataModel> dataModels = new ArrayList<>();

    public void getData(String url) {
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
                        Log.i("Myson" , sb.toString().trim());
                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }


            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                Log.v("Result" , myJSON);
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

    public void showList() {

        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            JSONArray peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String name = c.getString(TAG_NAME);
                String address = c.getString(TAG_ADDRESS);
                String telnum = c.getString(TAG_TELNUM);
                int viewcont = c.getInt(TAG_VIEWCOUNT);
                String lat = c.getString(TAG_LATITUDE);
                String lon = c.getString(TAG_LONGITUDE);
                String alt = c.getString(TAG_ALTITUDE);
                String logo = c.getString(TAG_LOGO);


                dataModels.add(new DataModel(name, address, telnum, viewcont, Double.valueOf(lat).doubleValue()
                        , Double.valueOf(lon).doubleValue(), Double.valueOf(alt).doubleValue(),logo ) );

                Log.i("Data List", "name :" + dataModels.get(i).getName() + "Address :" + dataModels.get(i).getAddress()
                        + " Tel :" + dataModels.get(i).getTel_num() + "View Count :" + dataModels.get(i).getLike_cnt() +
                        "Lat : " + dataModels.get(i).getLat() + "Lon :" + dataModels.get(i).getLon() +
                        "Alt : " + dataModels.get(i).getAltitude());
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<DataModel> getDataModel(){

        return dataModels;
    }

}
