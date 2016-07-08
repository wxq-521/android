package com.xie.day10_listviewinternet.internet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.xie.day10_listviewinternet.NewsShowActivity;
import com.xie.day10_listviewinternet.adpter.MyAdapter;
import com.xie.day10_listviewinternet.utils.HttpUtils;

public class MyAsyncTask extends
		AsyncTask<String, Void, List<Map<String, Object>>> {

	private Context context;
	private ListView mListView;

	public MyAsyncTask(Context context, ListView mListView) {
		this.context = context;
		this.mListView = mListView;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected List<Map<String, Object>> doInBackground(String... params) {
		// TODO Auto-generated method stub

		String path = params[0];
		String jsonString = HttpUtils.getHttpResult(path);
		List<Map<String, Object>> result = null;
		result = getResultJson(jsonString);
		return result;
	}

	private List<Map<String, Object>> getResultJson(String jsonString) {
		List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();

		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			for (int i = 1; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("subject", jsonObject2.getString("subject"));
				data.put("img", jsonObject2.getString("img"));
				data.put("memo", jsonObject2.getString("memo"));
				data.put("date", jsonObject2.getString("date"));
				data.put("newstype", jsonObject2.getString("newstype"));

				listData.add(data);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listData;
	}

	@Override
	protected void onPostExecute(final List<Map<String, Object>> result) {
		super.onPostExecute(result);
		MyAdapter adapter = new MyAdapter(context, result);
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			// 实现跳转显示新闻内容
			// 加了头视图的话，position的值从1开始
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(context, NewsShowActivity.class);
				intent.putExtra("memo", result.get(position - 1).get("memo")
						+ "");
				intent.putExtra("date", result.get(position - 1).get("date")
						+ "");
				intent.putExtra("newstype",
						result.get(position - 1).get("newstype") + "");
				context.startActivity(intent);
			}
		});
	}

}
