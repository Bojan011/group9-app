package com.project.group9;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class PlayerArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
 
	public PlayerArrayAdapter(Context context, String[] values) {
		super(context, R.layout.activity_view_players, values);
		this.context = context;
		this.values = values;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.activity_view_players, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.player_name);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.player_icon);
		textView.setText(values[position]);
 
		// Change icon based on name
		String s = values[position];
 
		System.out.println(s);
 
		if (s.equals("Andrew")) {
			imageView.setImageResource(R.drawable.ch11);
		} 
		
		else if (s.equals("Dave")) {
			imageView.setImageResource(R.drawable.ch15);
		} 
		else if (s.equals("Jamie")) {
			imageView.setImageResource(R.drawable.ch14);
		} 
		
		else {
			imageView.setImageResource(R.drawable.ch18);
		}
 
		return rowView;
	}
}
