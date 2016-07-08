package com.xie.day10_listviewinternet;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.xie.day10_listviewinternet.internet.MyAsyncTask;
import com.xie.day10_listviewinternet.internet.URLs;

public class MainActivity extends Activity {

	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = (ListView) findViewById(R.id.newsShow);
		View view = LayoutInflater.from(this).inflate(
				R.layout.news_header_layout, null);
		mListView.addHeaderView(view);
		new MyAsyncTask(this, mListView).execute(URLs.PATH);
	}

}
