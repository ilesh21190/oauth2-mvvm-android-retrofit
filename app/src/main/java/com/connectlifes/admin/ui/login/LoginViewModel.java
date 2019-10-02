package com.connectlifes.admin.ui.login;

import android.util.Log;
import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.connectlifes.admin.R;
import com.connectlifes.admin.data.LoginRepository;
import com.connectlifes.admin.oauth2.response.AccessTokenResponse;

public class LoginViewModel extends ViewModel {

    private final static String TAG = "Admin-LoginViewModel";
    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<AccessTokenResponse> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<AccessTokenResponse> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        Log.d(TAG,"reach into LoginViewModel "+username+" "+password);
        loginRepository.login(username, password, new LoginListener() {
            @Override
            public void onSuccess(AccessTokenResponse accessTokenResponse) {
                loginResult.setValue(accessTokenResponse);
            }

            @Override
            public void onError(AccessTokenResponse accessTokenResponse) {
                loginResult.setValue(accessTokenResponse);
            }
        });
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 4;
    }
}
