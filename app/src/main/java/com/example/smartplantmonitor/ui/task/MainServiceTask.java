package com.example.smartplantmonitor.ui.task;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartplantmonitor.databinding.FragmentHomeBinding;
import com.example.smartplantmonitor.ui.adapters.CustomExpandableListAdapter;
import com.example.smartplantmonitor.ui.datalist.ExpandableListDataPump;
import com.example.smartplantmonitor.ui.dto.UserDto;
import com.example.smartplantmonitor.ui.exceptions.ServerSyncFailedException;
import com.example.smartplantmonitor.ui.webservice.WSClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainServiceTask extends AsyncTask<Void, Void, UserDto> {

    WSClient wsClient;
    private FragmentHomeBinding binding;

    Context context;
    public MainServiceTask(Context context,FragmentHomeBinding binding) {

        wsClient = new WSClient(context);
        this.context = context;
        this.binding = binding;

    }

    @Override
    protected UserDto doInBackground(Void... voids) {

        try {
            UserDto userDto = wsClient.getUserDataByID();
            return userDto;
        } catch (ServerSyncFailedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void onPostExecute(UserDto userDto) {
        super.onPostExecute(userDto);

        final TextView textView = binding.textHome;
        final ExpandableListView elvDevices = binding.elvDevices;
        final TextView tvUserName = binding.tvUserName;
        tvUserName.setText(userDto.getName());

        List<String> expandableListTitle;

        expandableListTitle = new ArrayList<String>(userDto.getDevices().stream().map(a->a.getName()).collect(Collectors.toList()));

        ExpandableListAdapter expandableListAdapter = new CustomExpandableListAdapter(context, expandableListTitle, userDto);
        elvDevices.setAdapter(expandableListAdapter);
        elvDevices.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(context,
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        elvDevices.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(context,
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        UserDto finalUserDto = userDto;
        elvDevices.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        context,
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + finalUserDto.getDevices().get(childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });


    }
}
