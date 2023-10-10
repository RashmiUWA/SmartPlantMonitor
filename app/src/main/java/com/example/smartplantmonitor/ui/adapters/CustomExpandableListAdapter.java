package com.example.smartplantmonitor.ui.adapters;

import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.smartplantmonitor.R;
import com.example.smartplantmonitor.ui.dto.DevicesDto;
import com.example.smartplantmonitor.ui.dto.UserDto;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private UserDto userDto;

    public CustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                       UserDto userDto) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.userDto = userDto;
    }

    @Override
    public DevicesDto getChild(int listPosition, int expandedListPosition) {
        return this.userDto.getDevices().get(listPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final DevicesDto devicesDto = getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView tvDescription = (TextView) convertView
                .findViewById(R.id.tvDescription);
        TextView tvCondition = (TextView) convertView
                .findViewById(R.id.tvCondition);
        TextView tvAvgTemp = (TextView) convertView
                .findViewById(R.id.tvAvgTemp);
        TextView tvAvgHumidity = (TextView) convertView
                .findViewById(R.id.tvAvgHumidity);
        TextView tvAvgLight = (TextView) convertView
                .findViewById(R.id.tvAvgLight);
        TextView tvAvgSoilMoisture = (TextView) convertView
                .findViewById(R.id.tvAvgSoilMoisture);

        tvDescription.setText(devicesDto.getDescription());
        tvCondition.setText(String.valueOf(devicesDto.getCondition()));
        tvAvgTemp.setText(String.valueOf(devicesDto.getAvgTemp()));
        tvAvgHumidity.setText(String.valueOf(devicesDto.getAvgHumidity()));
        tvAvgLight.setText(String.valueOf(devicesDto.getAvgLight()));
        tvAvgSoilMoisture.setText(String.valueOf(devicesDto.getAvgSoilMoisture()));

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
