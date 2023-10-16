package com.example.smartplantmonitor.ui.task;

import android.app.ProgressDialog;
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
    ProgressDialog progressDialog;
String url;
    Context context;
    public MainServiceTask(Context context,FragmentHomeBinding binding, String url, Boolean isLive) {

        wsClient = new WSClient(context);
        this.context = context;
        this.binding = binding;
        this.url = url;
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Data Loader");
        if(isLive) {
            progressDialog.setMessage("Live Data Fetch Started. Please Wait");
        }else {
            progressDialog.setMessage("Average Data Fetch Started. Please Wait");
        }
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    @Override
    protected UserDto doInBackground(Void... voids) {

        try {
            UserDto userDto = wsClient.getUserDataByID(url);
            return userDto;
        } catch (ServerSyncFailedException e) {
            return null;
        }

    }

    @Override
    protected void onPostExecute(UserDto userDto) {
        super.onPostExecute(userDto);

      if(userDto != null){
            final TextView textView = binding.textHome;
            final ExpandableListView elvDevices = binding.elvDevices;
            final TextView tvUserName = binding.tvUserName;
            tvUserName.setText(userDto.getName());

            List<String> expandableListTitle;

            expandableListTitle = new ArrayList<String>(userDto.getDevices().stream().map(a -> a.getName()).collect(Collectors.toList()));

            ExpandableListAdapter expandableListAdapter = new CustomExpandableListAdapter(context, expandableListTitle, userDto);
            elvDevices.setAdapter(expandableListAdapter);


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

        progressDialog.dismiss();

    }
}
