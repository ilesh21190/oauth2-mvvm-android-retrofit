package com.connectlifes.admin.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.connectlifes.admin.data.DashboardRepository;
import com.connectlifes.admin.oauth2.response.DashboardResponse;
import com.connectlifes.admin.oauth2.service.Listener;

public class DashboardViewModel extends ViewModel {

    private final static String TAG = "Admin-DashboardViewMode";
    private MutableLiveData<DashboardResponse> dashboardResult = new MutableLiveData<>();
    private DashboardRepository dashboardRepository;

    DashboardViewModel(DashboardRepository dashboardRepository) {
        this.dashboardRepository= dashboardRepository;
    }


    LiveData<DashboardResponse> getDashboardCountResult() {
        return dashboardResult;
    }

    public void getDashboardCount() {
        dashboardRepository.getDashboardCount(new Listener<DashboardResponse>() {
            @Override
            public void onSuccess(DashboardResponse dashboardResponse) {
                dashboardResult.setValue(dashboardResponse);
            }

            @Override
            public void onError(DashboardResponse dashboardResponse) {
                dashboardResult.setValue(dashboardResponse);
            }
        });
    }

}
