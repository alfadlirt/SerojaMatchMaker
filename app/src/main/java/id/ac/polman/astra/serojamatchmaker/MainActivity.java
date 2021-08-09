package id.ac.polman.astra.serojamatchmaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Dashboard.Callbacks{

    SharedPreferences sharedPreferences;
    private final static String APP_NAME = "serojamatchmaker";
    private final static String UNAME = "username";
    private final static String NAMA = "name";
    private final static String ID = "id";

    private LinearLayout start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (LinearLayout) findViewById(R.id.btnStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartDashboard();
            }
        });

    }

    public void callFragment2() {
        BlankFragment fragment2 = new BlankFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainActivity, fragment2)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onStartDashboard() {
        Fragment fragment = Dashboard.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainActivity, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void callFragmentProfil(){
        Fragment fragment = UserProfile.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainActivity, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void callFragmentChangePassword(){
        Fragment fragment = UserPassword.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainActivity, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void callFragmentAddEvent(){
        Fragment fragment = EventActivity.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainActivity, fragment)
                .addToBackStack(null)
                .commit();
    }

}