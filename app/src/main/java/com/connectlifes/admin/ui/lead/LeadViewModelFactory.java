package com.connectlifes.admin.ui.lead;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.connectlifes.admin.data.LeadRepository;


public class LeadViewModelFactory implements ViewModelProvider.Factory {

    Context context;

    public LeadViewModelFactory(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LeadViewModel.class)) {
            return (T) new LeadViewModel(LeadRepository.getInstance(context));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}

