package io.github.anywhere.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import io.github.anywhere.ui.activity.LoginFragmentActivity;
import io.github.anywhere.ui.activity.Map_FragmentActivity;
import io.github.anywhere.ui.activity.RecycleFragmentActivity;
import io.github.anywhere.ui.activity.SampleTagCloudActivity;
import io.github.anywhere.ui.utils.DataModel;
import io.github.anywhere.ui.utils.Data_Model;
import io.github.anywhere.ui.utils.ImageData;

/**
 * Created by PCPC on 2017-05-27.
 */

public class TabPageAdabter extends FragmentStatePagerAdapter
{
    private int tabCount;
    ArrayList<ImageData> imageDatas;
    ArrayList<DataModel> textDatas;
    public TabPageAdabter(FragmentManager fm, int tabCount  ,  ArrayList<DataModel>textDatas ,ArrayList<ImageData> imageDatas ) {
        super(fm);
        this.tabCount = tabCount;
        Log.i("TabActivity" , tabCount +"");
        this.textDatas = textDatas;
        this.imageDatas = imageDatas;
        Log.i("TabPageAdapter " ,this.imageDatas.get(0).getImgName()[0]);

    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                RecycleFragmentActivity recycleFragmentActivity = new RecycleFragmentActivity(textDatas ,imageDatas);
                return recycleFragmentActivity;
            case 1:
                Map_FragmentActivity map_fragmentActivity = new Map_FragmentActivity(textDatas);
                return map_fragmentActivity;
            case 2:
                SampleTagCloudActivity sampleTagCloudActivity = new SampleTagCloudActivity(textDatas);
                return sampleTagCloudActivity;

            case 3:
                LoginFragmentActivity loginFragmentActivity = new LoginFragmentActivity();
                return loginFragmentActivity;

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
