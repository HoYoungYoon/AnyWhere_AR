package io.github.anywhere.ui.activity;
/**
 * Komodo Lab: Tagin! Project: 3D Tag Cloud
 * Google Summer of Code 2011
 * @authors Reza Shiftehfar, Sara Khosravinasr and Jorge Silva
 */

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

import io.github.anywhere.R; ;
import io.github.anywhere.ui.utils.DataModel;
import io.github.anywhere.ui.utils.Tag;
import io.github.anywhere.ui.view.TagCloudView;

/**
 * SampleTagCloud class:
 * this is a sample program to show how the 3D Tag Cloud can be used.
 * It Creates the activity and sets the ContentView to our TagCloudView class
 */
public class SampleTagCloudActivity extends Fragment {




		//Step0: to get a full-screen View:

		//Step1: get screen resolution:

		//Step4: (Optional) adding a new tag and resetting the whole 3D TagCloud
		//you can also add individual tags later:
		//mTagCloudView.addTag(new Tag("AAA", 5, "http://www.aaa.com"));
		// .... (several other tasg can be added similarly )
		//indivual tags will be placed along with the previous tags without moving 
		//old ones around. Thus, after adding many individual tags, the TagCloud 
		//might not be evenly distributed anymore. reset() re-positions all the tags:
		//mTagCloudView.reset();
		
		//Step5: (Optional) Replacing one of the previous tags with a new tag
		//you have to create a newTag and pass it in together 
		//with the Text of the existing Tag that you want to replace
		//Tag newTag=new Tag("Illinois", 9, "http://www.illinois.com");
		//in order to replace previous tag with text "Google" with this new one:
		//boolean result=mTagCloudView.Replace(newTag, "google");
		//result will be true if "google" was found and replaced. else result is falsr
		private static final String url = "http://anywhere9.dothome.co.kr/php/page_move.php?id=";
		private ArrayList<DataModel>dataModelArrayList = new ArrayList<>();
		public SampleTagCloudActivity(ArrayList<DataModel>dataModelArrayList){
			this.dataModelArrayList = dataModelArrayList;
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

			Display display = getActivity().getWindowManager().getDefaultDisplay();
			int width = display.getWidth();
			int height = display.getHeight() - 100;

			//Step2: create the required TagList:
			//notice: All tags must have unique text field
			//if not, only the first occurrence will be added and the rest will be ignored
			List<Tag> myTagList= createTags();

			//Step3: create our TagCloudview and set it as the content of our MainActivity
			mTagCloudView = new TagCloudView(getActivity(), width, height, myTagList ); //passing current context
			View view = mTagCloudView;
			mTagCloudView.requestFocus();
			mTagCloudView.setFocusableInTouchMode(true);
			mTagCloudView.setBackgroundColor(Color.parseColor("#ffffff"));

			return view;
		}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	private List<Tag> createTags(){
		//create the list of tags with popularity values and related url
		List<Tag> tempList = new ArrayList<Tag>();

		for (int i = 0; i<dataModelArrayList.size()/2; i++){
			tempList.add(new Tag(dataModelArrayList.get(i).getName(),dataModelArrayList.get(i).getLike_cnt(),
					url+dataModelArrayList.get(i).getName()
					));
		}
	//	tempList.add(new Tag("Google", 7, "http://www.google.com"));  //1,4,7,... assumed values for popularity

		return tempList;
	}
	
	private TagCloudView mTagCloudView;
}