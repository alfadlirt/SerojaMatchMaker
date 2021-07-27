package id.ac.polman.astra.serojamatchmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import id.ac.polman.astra.serojamatchmaker.entity.ResponseLogin;
import id.ac.polman.astra.serojamatchmaker.entity.User;
import id.ac.polman.astra.serojamatchmaker.remote.APIService;
import id.ac.polman.astra.serojamatchmaker.utils.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText textInputEditTextUsername,textInputEditTextPassword;
    Button btnLogin;
    ProgressBar progressBar;
    TextView textViewSignup;
    private APIService mAPIService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seroja_login);

        textInputEditTextUsername = findViewById(R.id.txtUsername);
        textInputEditTextPassword = findViewById(R.id.txtPassword);
        progressBar = findViewById(R.id.progress);
        btnLogin = findViewById(R.id.btnLogin);
        textViewSignup = findViewById(R.id.textSignup);

        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
                finish();
            }
        });
        mAPIService = APIUtils.getAPIService();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username,password;
                username = String.valueOf(textInputEditTextUsername.getText());
                password = String.valueOf(textInputEditTextPassword.getText());
                //getUser();
                authlogin(username,password);
            }
        });
    }


    public void authlogin(String username, String password) {
        mAPIService.authlogin(username, password).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                Log.i(TAG, response.toString());
                if(response.isSuccessful()) {
                    if(response.body().getSuccess()==true){
                        String msg = "Welcome " + response.body().getData().getName() + " !";
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Username or Password Not Match", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    public void getUser() {
        mAPIService.getUser().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i(TAG, response.body().toString());
                if(response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),response.body().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }


}