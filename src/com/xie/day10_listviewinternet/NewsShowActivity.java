package com.xie.day10_listviewinternet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NewsShowActivity extends Activity {

	private TextView mTextView1, mTextView2, mTextView3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_show);
		Intent intent = getIntent();
		String memo = intent.getStringExtra("memo");
		String date = intent.getStringExtra("date");
		String newstype = intent.getStringExtra("newstype");

		mTextView1 = (TextView) findViewById(R.id.textView1);
		mTextView2 = (TextView) findViewById(R.id.textView2);
		mTextView3 = (TextView) findViewById(R.id.textView3);

		mTextView1.setText(memo);
		mTextView2.setText(date);
		mTextView3.setText(newstype);
	}
}
