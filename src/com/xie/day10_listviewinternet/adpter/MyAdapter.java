package com.xie.day10_listviewinternet.adpter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xie.day10_listviewinternet.R;
import com.xie.day10_listviewinternet.utils.NormalLoadPictrue;

public class MyAdapter extends BaseAdapter {

	private Context context;
	private List<Map<String, Object>> datas;

	public MyAdapter(Context context, List<Map<String, Object>> datas) {
		this.context = context;
		this.datas = datas;
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder mHolder;
		if (convertView == null) {
			mHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_news_layout, parent, false);
			mHolder.mImageView = (ImageView) convertView.findViewById(R.id.img);
			mHolder.mTextView = (TextView) convertView
					.findViewById(R.id.subject);
			convertView.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) convertView.getTag();
		}

		mHolder.mTextView.setText((CharSequence) datas.get(position).get(
				"subject"));
		String url = (String) datas.get(position).get("img");
		new NormalLoadPictrue().getPicture(url, mHolder.mImageView);

		return convertView;
	}

	class ViewHolder {

		ImageView mImageView;
		TextView mTextView;

	}
}
