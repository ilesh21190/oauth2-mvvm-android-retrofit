package com.connectlifes.admin.ui.dashboard;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.connectlifes.admin.data.DashboardRepository;

public class DashboardViewModelFactory implements ViewModelProvider.Factory {

    Context context;

    public DashboardViewModelFactory(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DashboardViewModel.class)) {
            return (T) new DashboardViewModel(DashboardRepository.getInstance(context));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}

