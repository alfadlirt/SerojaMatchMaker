package id.ac.polman.astra.serojamatchmaker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import id.ac.polman.astra.serojamatchmaker.entity.ResponseChangePassword;
import id.ac.polman.astra.serojamatchmaker.entity.ResponseLogin;
import id.ac.polman.astra.serojamatchmaker.remote.APIService;
import id.ac.polman.astra.serojamatchmaker.utils.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class UserPassword extends Fragment {

    SharedPreferences sharedPreferences;
    private APIService mAPIService;
    private TextInputEditText mOldPassword;
    private TextInputEditText mNewPassword;
    private TextInputEditText mConfirmPassword;

    private final static String APP_NAME = "serojamatchmaker";
    private final static String UNAME = "username";
    private final static String NAMA = "name";
    private final static String ID = "id";
    private final static String PASSWORD = "password";

    TextView txtBackUser;

    public static UserPassword newInstance(){
        return new UserPassword();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_password, viewGroup, false);

        sharedPreferences = this.getActivity().getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
        mOldPassword = (TextInputEditText) view.findViewById(R.id.txtOldPassword);
        mNewPassword= (TextInputEditText) view.findViewById(R.id.txtNewPassword);
        mConfirmPassword = (TextInputEditText) view.findViewById(R.id.txtConfirmNewPassword);
        txtBackUser = (TextView) view.findViewById(R.id.btnBackEditUser);

        //Cek SharedPreferences
        String oldpasssp = sharedPreferences.getString(PASSWORD, null);
        String idsp = sharedPreferences.getString(ID, null);

        //mOldPassword.setText(oldpasssp);

        txtBackUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "old password : " + passsp, Toast.LENGTH_LONG).show();
                ((MainActivity) getActivity()).callFragmentProfil();
            }
        });

        view.findViewById(R.id.btnSavePassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePass(idsp);
            }
        });

        return view;
    }

    private void savePass(String id){
        String oldpass = String.valueOf(mOldPassword.getText());
        String pass1 = String.valueOf(mNewPassword.getText());
        String pass2 = String.valueOf(mConfirmPassword.getText());

        if (oldpass.isEmpty()) {
            mOldPassword.setError("Old Password cannot be empty!");
            mOldPassword.requestFocus();
            return;
        } else if (pass1.isEmpty()) {
            mNewPassword.setError("New Password cannot be empty!");
            mNewPassword.requestFocus();
            return;
        } else if (pass2.isEmpty()) {
            mConfirmPassword.setError("Confirm Password cannot be empty!");
            mConfirmPassword.requestFocus();
            return;
        }

        if(!pass1.equals(pass2)){
            mConfirmPassword.setError("Password Doesn't Match!");
            mConfirmPassword.requestFocus();
            return;
        }else{
            mAPIService = APIUtils.getAPIService();
            Call<ResponseChangePassword> call = mAPIService.changePassword(id, oldpass, pass1);
            call.enqueue(new Callback<ResponseChangePassword>() {
                @Override
                public void onResponse(Call<ResponseChangePassword> call, Response<ResponseChangePassword> response) {
                    if(response.body() != null){
                        if(response.body().getSuccess()==true){
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            Log.e(TAG, "onResponse: " + new Gson().toJson(response.body().getData()));
                            edit.putString(UNAME, response.body().getData().getUsername());
                            edit.putString(NAMA, response.body().getData().getName());
                            edit.putString(ID, response.body().getData().getId());
                            edit.putString(PASSWORD, response.body().getData().getPassword());
                            edit.apply();
                            ((MainActivity) getActivity()).onStartDashboard();
                            Toast.makeText(getActivity(), "Changed password successfully", Toast.LENGTH_LONG).show();
                        }else if(response.body().getSuccess()==false){
                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                }

                @Override
                public void onFailure(Call<ResponseChangePassword> call, Throwable t) {
                    Log.e("Update Error : ", t.getMessage());
                    Toast.makeText(getActivity(), "Data gagal tersimpan!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
