package id.ac.polman.astra.serojamatchmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import id.ac.polman.astra.serojamatchmaker.entity.ResponseLogin;
import id.ac.polman.astra.serojamatchmaker.entity.User;
import id.ac.polman.astra.serojamatchmaker.remote.APIService;
import id.ac.polman.astra.serojamatchmaker.utils.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class SignUp extends AppCompatActivity {
    private APIService mAPIService;
    private EditText mName;
    private EditText mUsername;
    private EditText mPassword;
    private EditText mPassword2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mName = findViewById(R.id.txtName);
        mUsername = findViewById(R.id.txtUsername);
        mPassword = findViewById(R.id.txtPassword);
        mPassword2 = findViewById(R.id.txtRePassword);
        mAPIService = APIUtils.getAPIService();
        findViewById(R.id.btnCreateUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });


    }

    private void createUser() {
        String name = mName.getText().toString().trim();
        String username = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String password2 = mPassword2.getText().toString().trim();

        if (name.isEmpty()) {
            mName.setError("Name Cannot Be Empty!");
            mName.requestFocus();
            return;
        } else if (username.isEmpty()) {
            mUsername.setError("Username Cannot Be Empty!");
            mUsername.requestFocus();
            return;
        }else if (password.isEmpty()) {
            mPassword.setError("Password Cannot Be Empty!");
            mPassword.requestFocus();
            return;
        }else if (password2.isEmpty()) {
            mPassword2.setError("Confirm Password Cannot Be Empty!");
            mPassword2.requestFocus();
            return;
        }

        if(!password.equals(password2)){
            mPassword2.setError("Password Doesn't Match!");
            mPassword2.requestFocus();
            return;
        }else{
            mAPIService.addUser(new User(name, username, password)).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.i(TAG, response.toString());
                    if(response.isSuccessful()) {
                        String msg = "Account Created! Please Login!";
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e(TAG, "Unable to submit post to API.");
                }
            });

        }
    }
}