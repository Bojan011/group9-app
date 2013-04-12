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
import android.widget.ProgressBar;
import android.widget.TextView;
 
 
public class StatArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
 
	public StatArrayAdapter(Context context, String[] values) {
		super(context, R.layout.activity_view_stats, values);
		this.context = context;
		this.values = values;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.activity_view_stats, parent, false);
		TextView tV1 = (TextView) rowView.findViewById(R.id.stat);
		ProgressBar pB = (ProgressBar) rowView.findViewById(R.id.statValue);
		TextView tV2 = (TextView) rowView.findViewById(R.id.statnum);
		
		if (position == 0) {
			pB.setVisibility(View.INVISIBLE);
			tV2.setText("");
		}
		else {
			pB.setProgress(Integer.parseInt(values[position]));
			tV2.setText(values[position]);
		}
		
		switch(position) {
			case 0:
				tV1.setText(values[position]);
				break;
				
			case 1: 
				tV1.setText("Heatlh");
				break;
		
			case 2:
				tV1.setText("Attack");
			 	break;
		
			case 3:
				tV1.setText("Defend");
				break;
		
			case 4:
				tV1.setText("Dodge");
				break;
		
			case 5:
				tV1.setText("Experience");
				break;
		
			case 6:
				tV1.setText("Level");
				break;	
		
		}
		
		return rowView;
	}
}

