package com.nibdev.otrtav2.view.adapters;

import java.util.ArrayList;
import java.util.List;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nibdev.otrtav2.R;

public class DrawerAdapter extends BaseAdapter {

	public static final int ACTION_DATABASE = 1;
	public static final int ACTION_LAYOUTS = 2;
	public static final int ACTION_SETTINGS = 3;
	public static final int ACTION_SCRIPTS = 4;
	public static final int ACTION_LEARN = 5;
	public static final int ACTION_ALLOFF = 6;
	public static final int ACTION_DB2BROWSER = 7;
	
	private static final int KEY_ACTION = 101;
	private static final int KEY_NAME = 102;
	private static final int KEY_ICON = 104;
	
    private List<SparseArray<Object>> mData;
	private int mSelectedPos;	
	
	
	public DrawerAdapter(){
        mData = new ArrayList<SparseArray<Object>>();
		
		//db
        SparseArray<Object> mapdb = new SparseArray<Object>();
		mapdb.put(KEY_ACTION, ACTION_DATABASE);
		mapdb.put(KEY_NAME, "Database");
		mapdb.put(KEY_ICON, R.drawable.ic_action_database_light);
		
		//scripts
        SparseArray<Object> mapscripts = new SparseArray<Object>();
		mapscripts.put(KEY_ACTION, ACTION_SCRIPTS);
		mapscripts.put(KEY_NAME, "Scripts");
		mapscripts.put(KEY_ICON, R.drawable.ic_action_scripts_light);
		
		//learn
        SparseArray<Object> maplearn = new SparseArray<Object>();
		maplearn.put(KEY_ACTION, ACTION_LEARN);
		maplearn.put(KEY_NAME, "Learn codes");
		maplearn.put(KEY_ICON, R.drawable.ic_action_learn_light);
		
		//settings
        SparseArray<Object> mapsett = new SparseArray<Object>();
		mapsett.put(KEY_ACTION, ACTION_SETTINGS);
		mapsett.put(KEY_NAME, "Settings");
		mapsett.put(KEY_ICON, R.drawable.ic_action_settings_light);
		
		mData.add(mapdb);
        // mData.add(maplayout);
		mData.add(mapscripts);
		mData.add(maplearn);
		mData.add(mapsett);
	}
	
	public void disableLearn(){
		mData.remove(3);
		notifyDataSetChanged();
	}
	
	public void setSelectedPosition(int pos){
		mSelectedPos = pos;
		notifyDataSetChanged();
	}
	
	public int getSelectedPosition(){
		return mSelectedPos;
	}
	
	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return ((Integer)mData.get(position).get(KEY_ACTION)).longValue();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null){
			v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lvitem_drawer, null);
		}
		
		TextView tvAction = (TextView)v.findViewById(R.id.tv_text);
		View vIndicator = v.findViewById(R.id.v_indicator);
		ImageView ivIcon = (ImageView)v.findViewById(R.id.iv_icon);
		
        SparseArray<Object> data = mData.get(position);
		tvAction.setText((String)data.get(KEY_NAME));
		ivIcon.setImageResource((Integer)data.get(KEY_ICON));
		
		if (position == mSelectedPos){
			vIndicator.setVisibility(View.VISIBLE);
		}else{
			vIndicator.setVisibility(View.INVISIBLE);
		}
		
		return v;
	}

}
