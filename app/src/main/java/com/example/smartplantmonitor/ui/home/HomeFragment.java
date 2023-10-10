package com.example.smartplantmonitor.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartplantmonitor.databinding.FragmentHomeBinding;
import com.example.smartplantmonitor.ui.adapters.CustomExpandableListAdapter;
import com.example.smartplantmonitor.ui.datalist.ExpandableListDataPump;
import com.example.smartplantmonitor.ui.dto.UserDto;
import com.example.smartplantmonitor.ui.task.MainServiceTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    List<String> expandableListTitle;
    UserDto userDto;
    ExpandableListAdapter expandableListAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        MainServiceTask mainServiceTask = new MainServiceTask(getContext(),binding);
        mainServiceTask.execute();



//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}