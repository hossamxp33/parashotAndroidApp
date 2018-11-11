package com.example.hossam.parashotApp.presentation.screens.home.loginFragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hossam.parashotApp.R;
import com.example.hossam.parashotApp.entities.User;
import com.example.hossam.parashotApp.presentation.screens.home.HomeActivity;
import com.example.hossam.parashotApp.presentation.screens.home.registerFragment.RegisterFragment;
import com.example.hossam.parashotApp.presentation.screens.home.storesFragment.AllStoresViewModelFactory;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;
import java.util.Objects;


public class LoginFragment extends Fragment implements View.OnClickListener {



    TextView login,register,loginWithLogin;
    EditText username,password;
    LoginViewModel loginViewModel;
    private FrameLayout progress;
    private static final String EMAIL = "email";
    CallbackManager callbackManager;
    LoginButton loginButton;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login, container, false);
        ((HomeActivity)Objects.requireNonNull(getActivity())).title.setText(getText(R.string.logintitle));
        loginViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(LoginViewModel.class);

        username= view.findViewById(R.id.username);
        password= view.findViewById(R.id.password);
        login= view.findViewById(R.id.login);
        progress = view.findViewById(R.id.progress);
        register = view.findViewById(R.id.register);
     //   loginWithLogin = view.findViewById(R.id.loginWithFB);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

        callbackManager = CallbackManager.Factory.create();


        loginButton = view.findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("fb",loginResult.toString());
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        loginViewModel.loading.observe(getActivity(),loading ->
                progress.setVisibility(loading ? View.VISIBLE : View.GONE));


        loginViewModel.coderesponse.observe(getActivity(),code ->
                {
                    if (code==401)
                        Toast.makeText(getActivity(), getString(R.string.usererror), Toast.LENGTH_LONG).show();

                }
        );

        loginViewModel.loginLiveData.observe(getActivity(),model ->
                {
                    if (model.isSuccess()) {
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                            fm.popBackStack();
                        }
                        Toast.makeText(getActivity(), getString(R.string.loginsuccess), Toast.LENGTH_LONG).show();

                     //   getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new CategoryFragment()).addToBackStack(null).commit();

                    }
                    else
                    {
                        Toast.makeText(getActivity(), getString(R.string.usererror), Toast.LENGTH_LONG).show();

                    }
                }
        );


        return view;
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.login:
                if (validate()) {
                    User user = new User(username.getText().toString(), password.getText().toString());
                    loginViewModel.login(user);
                }

                break;

            case R.id.register :
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new RegisterFragment()).addToBackStack(null).commit();
                break;

//            case R.id.loginWithFB:
//
//                callbackManager = CallbackManager.Factory.create();
//
//                LoginManager.getInstance().registerCallback(callbackManager,
//                        new FacebookCallback<LoginResult>() {
//                            @Override
//                            public void onSuccess(LoginResult loginResult) {
//                                Log.d("login",loginResult.toString());
//                                // App code
//                            }
//
//                            @Override
//                            public void onCancel() {
//                                // App code
//                            }
//
//                            @Override
//                            public void onError(FacebookException exception) {
//                                // App code
//                            }
//                        });
//
//                break;

        }
    }

    private boolean validate() {

        if (!(username.getText().toString().matches(""))&&!(password.getText().toString().matches("")))
        {
            return  true;
        }
        else {
            Toast.makeText(getActivity(), getString(R.string.commpletefileds), Toast.LENGTH_LONG).show();
            return false;
        }
    }


}
