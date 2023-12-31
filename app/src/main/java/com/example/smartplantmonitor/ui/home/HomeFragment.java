package com.example.smartplantmonitor.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartplantmonitor.R;
import com.example.smartplantmonitor.databinding.FragmentHomeBinding;
import com.example.smartplantmonitor.ui.adapters.CustomExpandableListAdapter;
import com.example.smartplantmonitor.ui.datalist.ExpandableListDataPump;
import com.example.smartplantmonitor.ui.dto.UserDto;
import com.example.smartplantmonitor.ui.task.MainServiceTask;
import com.example.smartplantmonitor.ui.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    List<String> expandableListTitle;
    UserDto userDto;
    ExpandableListAdapter expandableListAdapter;
    Button btnFetchData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btnFetchData = binding.btnFetchData;
        btnFetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainServiceTask mainServiceTask;
                SharedPreferences sh = getContext().getSharedPreferences(Constants.sharedPref, Context.MODE_PRIVATE);
                String baseURL = sh.getString("server-url", "");

                if(btnFetchData.getText().toString().equals("Show Live Data")) {
                    mainServiceTask = new MainServiceTask(getContext(), binding, Constants.LIVE_DATA.replace("BASE_URL",baseURL), false);
                    btnFetchData.setText("Show Average Data");
                }else {
                    mainServiceTask = new MainServiceTask(getContext(), binding, Constants.USER_DATA_BY_ID.replace("BASE_URL",baseURL), true);
                    btnFetchData.setText("Show Live Data");
                }

                mainServiceTask.execute();
            }
        });

        SharedPreferences sh = getContext().getSharedPreferences(Constants.sharedPref, Context.MODE_PRIVATE);
        String baseURL = sh.getString("server-url", "");

        MainServiceTask mainServiceTask = new MainServiceTask(getContext(), binding, Constants.USER_DATA_BY_ID.replace("BASE_URL",baseURL), false);
        mainServiceTask.execute();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}