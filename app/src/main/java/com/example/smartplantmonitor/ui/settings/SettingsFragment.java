package com.example.smartplantmonitor.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartplantmonitor.databinding.FragmentSettingsBinding;
import com.example.smartplantmonitor.ui.util.Constants;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel galleryViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button btnSaveSettings = binding.btnSaveSettings;
        EditText etURL = binding.etURL;
        btnSaveSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getContext().getSharedPreferences(Constants.sharedPref, Context.MODE_PRIVATE);

                SharedPreferences.Editor sharedPref = sharedPreferences.edit();

                sharedPref.putString("server-url", etURL.getText().toString());

                sharedPref.commit();

                Toast.makeText(getActivity(), "Settings saved successfully",
                        Toast.LENGTH_SHORT).show();
            }
        });


        SharedPreferences sh = getContext().getSharedPreferences(Constants.sharedPref, Context.MODE_PRIVATE);
        String baseURL = sh.getString("server-url", "");

        etURL.setText(baseURL);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}