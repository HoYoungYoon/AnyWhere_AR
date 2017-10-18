package io.github.anywhere.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import io.github.anywhere.R; ;
import io.github.anywhere.ui.utils.DataModel;
import io.github.anywhere.ui.utils.GetData;
import io.github.anywhere.ui.utils.GetImgData;
import io.github.anywhere.ui.utils.ImageData;

/**
 * Created by PCPC on 2017-06-06.
 */

public class StartActivity extends AppCompatActivity {
    private ArrayList<DataModel> Data_List = new ArrayList();
    private ArrayList<ImageData> Img_List = new ArrayList<>();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        final GetData getData = new GetData();
        final GetImgData imageData = new GetImgData();
        getData.getData("http://anywhere9.dothome.co.kr/php/appOutput.php");
        imageData.getImageData("http://anywhere9.dothome.co.kr/php/appImagepath.php");

        final Handler handler =new Handler();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        try
                        {
                            Data_List = getData.getDataModel();
                            Img_List = imageData.getImgArray();
                            Log.i("Data return List :" ,Data_List.get(1).getName());
                            Log.i("Img Data return List :" ,Img_List.get(1).getName());
                            Intent i = new Intent(StartActivity.this , MainActivity.class);
                            i.putParcelableArrayListExtra("Data" , Data_List);
                            i.putParcelableArrayListExtra("Img" , Img_List);
                            startActivity(i);
                            finish();


                        }
                        catch ( Exception e )
                        {
                            e.printStackTrace();
                        }

                    }
                }, 3000);
            }
        });
    }
}
