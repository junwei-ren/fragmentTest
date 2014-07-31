package com.test.jerry.fragmenttest;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioGroup;

public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener, OnClickListener{

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */
	public static final int TAB_NUM = 3;
	public static String TAG = "FragmentTest";
	SectionsPagerAdapter mSectionsPagerAdapter;
//	private FragmentTabHost mTabHost;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	RadioGroup mRadioGroup;
	ArrayList<Integer> mTabId;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		mTabId = new ArrayList<Integer>(TAB_NUM);
		mTabId.add(0, R.id.tab_rb_a);
		mTabId.add(1, R.id.tab_rb_b);
		mTabId.add(2, R.id.tab_rb_c);
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mRadioGroup = (RadioGroup) findViewById(R.id.tabs_rg);
		mRadioGroup.setOnCheckedChangeListener(this);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
//						actionBar.setSelectedNavigationItem(position);
						Log.d(TAG, "position:" + position);
						Log.d(TAG, "now selected:" + mRadioGroup.getCheckedRadioButtonId());
						mRadioGroup.check(findRadioButtonIdByPosition(position));
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
//		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
//			actionBar.addTab(actionBar.newTab()
//					.setText(mSectionsPagerAdapter.getPageTitle(i))
//					.setTabListener(this));
			
//		}
	}
	
	private int findRadioButtonIdByPosition(int position) {
		int id = R.id.tab_rb_a;
		id = mTabId.get(position);
		return id;
	}
	private int findPositionByRadioButtonId(int id) {
		int position = 0;
		position = mTabId.indexOf(id);
		return position;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

//	@Override
//	public void onTabSelected(ActionBar.Tab tab,
//			FragmentTransaction fragmentTransaction) {
//		// When the given tab is selected, switch to the corresponding page in
//		// the ViewPager.
//		mViewPager.setCurrentItem(tab.getPosition());
//	}
//
//	@Override
//	public void onTabUnselected(ActionBar.Tab tab,
//			FragmentTransaction fragmentTransaction) {
//	}
//
//	@Override
//	public void onTabReselected(ActionBar.Tab tab,
//			FragmentTransaction fragmentTransaction) {
//	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			PlaceholderFragment view = PlaceholderFragment.newInstance(position+1);
			view.setBtnListener(MainActivity.this);
			return view;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return TAB_NUM;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getString(R.string.my_shop);
			case 1:
				return getString(R.string.my_task);
			case 2:
				return getString(R.string.settings);
			}
			return null;
		}
	}

	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		mViewPager.setCurrentItem(findPositionByRadioButtonId(checkedId));
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		switch(id) {
		case 1:
			gotoFragment(11);
			break;
		case 2:
			gotoFragment(21);
			break;
		case 3:
			
			break;
		case 11:
			gotoFragment(111);
			break;
		case 21:
			gotoFragment(11);
			break;
		case 111:
			gotoFragment(3);
			break;
		}
	}
	public void gotoFragment(int position) {
	    FragmentManager fm = getFragmentManager();
	    FragmentTransaction ft = fm.beginTransaction();
	    String tag = new StringBuilder().append(position).toString();
	    Fragment target = fm.findFragmentByTag(tag);
	    if(target == null) {
	    	PlaceholderFragment view = PlaceholderFragment.newInstance(position);
			view.setBtnListener(MainActivity.this);
			target = view;
	    }
	    ft.replace(R.id.pager, target);
	    ft.addToBackStack(null);
	    ft.commit();
	}
}
