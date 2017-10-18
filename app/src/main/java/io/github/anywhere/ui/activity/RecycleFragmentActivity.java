package io.github.anywhere.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import io.github.anywhere.R; ;
import io.github.anywhere.ui.adapter.FeedAdapter;
import io.github.anywhere.ui.adapter.FeedItemAnimator;
import io.github.anywhere.ui.utils.DataModel;
import io.github.anywhere.ui.utils.ImageData;
import io.github.anywhere.ui.view.FeedContextMenu;
import io.github.anywhere.ui.view.FeedContextMenuManager;

/**
 * Created by PCPC on 2017-05-27.
 */

public class RecycleFragmentActivity  extends Fragment implements FeedAdapter.OnFeedItemClickListener,
        FeedContextMenu.OnFeedContextMenuItemClickListener{


    RecyclerView rvFeed;
    ArrayList<ImageData> imageDatas;
    ArrayList<DataModel> textDatas;
    private FeedAdapter feedAdapter;
    ImageView sampleImg;

    public RecycleFragmentActivity(ArrayList<DataModel>textDatas , ArrayList<ImageData>imageDatas){
        this.imageDatas = imageDatas;
        this.textDatas = textDatas;

    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recyclerfragment, container, false);
        rvFeed = (RecyclerView)v.findViewById(R.id.rvFeed);
        sampleImg = (ImageView)v.findViewById(R.id.sample_image);
        setupFeed();
        feedAdapter.updateItems(true);
        return v;
    }

    private void setupFeed() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvFeed.setLayoutManager(linearLayoutManager);
        Log.i("RecycleFragment " ,imageDatas.get(0).getImgName()[0]);
        feedAdapter = new FeedAdapter(getActivity() , textDatas ,imageDatas );

        feedAdapter.setOnFeedItemClickListener( this);
        rvFeed.setAdapter(feedAdapter); //여기다가 데이터를 넣어야한다.
        /*
        rvFeed.addOnItemTouchListener(new FeedAdapter.RecyclerTouchListener(getActivity() , rvFeed , new FeedAdapter.ClickListener(){


            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", imageDatas);
                bundle.putInt("position", position);
                bundle.putString("name" , imageDatas.get(position).getName());
                FragmentTransaction ft =  getActivity().getSupportFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

*/
        rvFeed.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
            }
        });
        rvFeed.setItemAnimator(new FeedItemAnimator());
    }




    @Override
    public void onCommentsClick(View v, int position) {
        final Intent intent = new Intent(getActivity(), CommentsActivity.class);
        int[] startingLocation = new int[2];
        v.getLocationOnScreen(startingLocation);
        intent.putExtra(CommentsActivity.ARG_DRAWING_START_LOCATION, startingLocation[1]);
        startActivity(intent);
       // overridePendingTransition(0, 0);
    }

    @Override
    public void onMoreClick(View v, int itemPosition) {
        FeedContextMenuManager.getInstance().toggleContextMenuFromView(v, itemPosition, this);
    }

    @Override
    public void onProfileClick(View v) {
        int[] startingLocation = new int[2];
        v.getLocationOnScreen(startingLocation);
        startingLocation[0] += v.getWidth() / 2;
        UserProfileActivity.startUserProfileFromLocation(startingLocation, getActivity());
        //overridePendingTransition(0, 0);
    }

    @Override
    public void onReportClick(int feedItem) {
        FeedContextMenuManager.getInstance().hideContextMenu();
    }

    @Override
    public void onSharePhotoClick(int feedItem) {
        FeedContextMenuManager.getInstance().hideContextMenu();
    }

    @Override
    public void onCopyShareUrlClick(int feedItem) {
        FeedContextMenuManager.getInstance().hideContextMenu();
    }

    @Override
    public void onCancelClick(int feedItem) {
        FeedContextMenuManager.getInstance().hideContextMenu();
    }

    private void showFeedLoadingItemDelayed() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    rvFeed.smoothScrollToPosition(0);
                 feedAdapter.showLoadingView();
            }
        }, 500);
    }
}
