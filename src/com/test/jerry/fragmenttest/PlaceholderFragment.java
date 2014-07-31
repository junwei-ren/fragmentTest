package com.test.jerry.fragmenttest;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final int TAB_NUM = 3;
	private static final String TAG="PlaceholderFragment";
//	private static final String ARG_SECTION_NUMBER = "section_number";
	private static Fragment mTabFragments[] = new Fragment[TAB_NUM];  //fragments for switch tab to show
	private static HashMap<Integer, Fragment> mFragmentList; //All created fragments
	private int mPosition;
	private OnClickListener mBtnListener;
	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static PlaceholderFragment newInstance(int sectionNumber) {
		Log.d(TAG, "sectionNumber: "+sectionNumber);

//		if(mTabFragments[sectionNumber-1] == null) {
//			PlaceholderFragment fragment = new PlaceholderFragment(sectionNumber);
//			mTabFragments[sectionNumber-1] = fragment;
//			Bundle args = new Bundle();
//			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//			fragment.setArguments(args);
			if(mFragmentList == null) {
				mFragmentList = new HashMap<Integer, Fragment>();
			}
			if(!mFragmentList.containsKey(sectionNumber)) {
				PlaceholderFragment fragment = new PlaceholderFragment(sectionNumber);
				mFragmentList.put(sectionNumber, fragment);
				return fragment;
			} else {
				return (PlaceholderFragment) mFragmentList.get(sectionNumber);
			}
			
//		} else {
//			return (PlaceholderFragment) mTabFragments[sectionNumber-1];
//		}
	}
	public void setBtnListener(OnClickListener listener) {
		mBtnListener = listener;
	}
 	private PlaceholderFragment(int position) {
		mPosition = position;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_base, container,
				false);
		TextView tv = (TextView)rootView.findViewById(R.id.fg_label);
		tv.setText("fragment"+(mPosition));
		Button btn = (Button)rootView.findViewById(R.id.btn);
		setBtnLabel(btn, mPosition);
		String tag = new StringBuilder().append(mPosition).toString();
		btn.setTag(tag);
		btn.setId(mPosition);
		if(mBtnListener != null) {
			btn.setOnClickListener(mBtnListener);
		}
		rootView.setTag(tag);
		return rootView;
	}
	public void setBtnLabel(Button btn, int pos) {		
		
		switch(pos) {
		case 1:
			btn.setText("goto"+11);
			break;
		case 2:
			btn.setText("goto"+21);
			break;
		case 3:
			btn.setText("Quit");
			break;
		case 11:
			btn.setText("goto"+111);
			break;
		case 21:
			btn.setText("goto"+11);
			break;
		case 111:
			btn.setText("goto"+3);
			break;
		}
	}
}

