package id.ac.polman.astra.serojamatchmaker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import id.ac.polman.astra.serojamatchmaker.entity.ResponseLogin;
import id.ac.polman.astra.serojamatchmaker.entity.User;
import id.ac.polman.astra.serojamatchmaker.remote.APIService;
import id.ac.polman.astra.serojamatchmaker.utils.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class UserProfile extends Fragment {

    SharedPreferences sharedPreferences;
    private APIService mAPIService;
    private TextView mId;
    private EditText mName;
    private EditText mUsername;

    TextView txtChangePassword;

    private final static String APP_NAME = "serojamatchmaker";
    private final static String UNAME = "username";
    private final static String NAMA = "name";
    private final static String ID = "id";
    private final static String PASSWORD = "password";


    public static UserProfile newInstance(){
        return new UserProfile();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.user_activity, viewGroup, false);

        sharedPreferences = this.getActivity().getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        mId = (TextView) view.findViewById(R.id.txtEditIdUser);
        mName= (EditText) view.findViewById(R.id.txtEditName);
        mUsername = (EditText) view.findViewById(R.id.txtEditUname);

        //Cek SharedPreferences
        String unamesp = sharedPreferences.getString(UNAME, null);
        String namesp = sharedPreferences.getString(NAMA, null);
        String idsp = sharedPreferences.getString(ID, null);
        String passsp = sharedPreferences.getString(PASSWORD, null);

        mId.setText(idsp);
        mName.setText(namesp);
        mUsername.setText(unamesp);

        view.findViewById(R.id.btnEditUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(idsp);
            }
        });

        txtChangePassword = (TextView) view.findViewById(R.id.btnEditPassword);
        txtChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "old password : " + passsp, Toast.LENGTH_LONG).show();
                ((MainActivity) getActivity()).callFragmentChangePassword();
            }
        });

        //initialize and assign variable
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottomNavigation);

        //set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        SharedPreferences preferences = this.getActivity().getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        //perform itemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.dashboard:
                        ((MainActivity) getActivity()).onStartDashboard();
                        return true;
                    case R.id.event:
                        ((MainActivity) getActivity()).callFragmentProfil();
                        return true;
                    case R.id.logout:
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        return true;
                }
                return false;
            }
        });

        return view;
    }

    private void save(String id){
        String name = String.valueOf(mName.getText());
        String username = String.valueOf(mUsername.getText());

        if (name.isEmpty()) {
            mName.setError("Harap isi Field Name!");
            mName.requestFocus();
            return;
        } else if (username.isEmpty()) {
            mUsername.setError("Harap isi Field Username!");
            mUsername.requestFocus();
            return;
        }else{
            mAPIService = APIUtils.getAPIService();
            Call<ResponseLogin> call = mAPIService.updateUser(id, name, username);
            call.enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    if(response.body() != null){
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        Log.e(TAG, "onResponse: " + new Gson().toJson(response.body().getData()));
                        edit.putString(UNAME, response.body().getData().getUsername());
                        edit.putString(NAMA, response.body().getData().getName());
                        edit.putString(ID, response.body().getData().getId());
                        edit.putString(PASSWORD, response.body().getData().getPassword());
                        edit.apply();
                        ((MainActivity) getActivity()).onStartDashboard();
                        Toast.makeText(getActivity(), "Data saved successfully", Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    Log.e("Update Error : ", t.getMessage());
                    Toast.makeText(getActivity(), "Data gagal tersimpan!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void refresh(String id){
        mAPIService = APIUtils.getAPIService();
        Call<User> call = mAPIService.getUserById(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body() != null){
                    User user = response.body();
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString(UNAME, user.getUsername());
                    edit.putString(NAMA, user.getName());
                    edit.putString(ID, user.getId());
                    edit.apply();
                    ((MainActivity) getActivity()).onStartDashboard();
                }else{
                    Toast.makeText(getActivity(), "Failed To Save Data !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Log In Error : ", t.getMessage());
                Toast.makeText(getActivity(), "Failed To Get Data !", Toast.LENGTH_LONG).show();
            }
        });
    }
}
