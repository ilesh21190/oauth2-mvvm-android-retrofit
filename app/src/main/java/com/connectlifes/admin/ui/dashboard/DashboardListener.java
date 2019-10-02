package com.connectlifes.admin.ui.dashboard;

import com.connectlifes.admin.oauth2.response.DashboardResponse;

public interface DashboardListener {
    void onSuccess(DashboardResponse dashboardResponse);
    void onError(DashboardResponse dashboardResponse);
}
