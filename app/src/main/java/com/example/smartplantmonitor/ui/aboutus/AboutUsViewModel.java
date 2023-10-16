package com.example.smartplantmonitor.ui.aboutus;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutUsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AboutUsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Our Smart Plant Monitor mobile app empowers plant enthusiasts to nurture their green companions with ease. Monitor soil moisture, sunlight, and temperature, receive real-time alerts, and access care tips. Keep your plants thriving and vibrant, all from the convenience of your smartphone.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}