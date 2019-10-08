package com.connectlifes.admin.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.connectlifes.admin.R;
import com.connectlifes.admin.oauth2.response.DashboardResponse;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this, new DashboardViewModelFactory(getActivity().getApplicationContext()))
                        .get(DashboardViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        getDashboardCounts();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void getDashboardCounts() {
        final TextView textView = root.findViewById(R.id.text_home);
        dashboardViewModel.getDashboardCount();
        dashboardViewModel.getDashboardCountResult().observe(this, new Observer<DashboardResponse>() {
            @Override
            public void onChanged(@Nullable DashboardResponse dashboardResponse) {
                if (dashboardResponse == null) {
                    return;
                } else if( dashboardResponse.getError() != null){
                    textView.setText(dashboardResponse.getErrorDescription());
                } else if (dashboardResponse.getT() != null) {
                    textView.setText(dashboardResponse.getT().getLocalizedMessage());
                }else {
                    textView.setText(dashboardResponse.getMessage()); //TODO  :  later replace with actual UI update code
                }
            }
        });
    }
}