package id.ac.polman.astra.serojamatchmaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import id.ac.polman.astra.serojamatchmaker.fragment.BracketsFragment;
import id.ac.polman.astra.serojamatchmaker.model.BracketCard;
import id.ac.polman.astra.serojamatchmaker.utils.UpdateScoreModal;

public class MainActivity extends AppCompatActivity implements Dashboard.Callbacks, UpdateScoreModal.OnInputScore {

    SharedPreferences sharedPreferences;
    private final static String APP_NAME = "serojamatchmaker";
    private final static String UNAME = "username";
    private final static String NAMA = "name";
    private final static String ID = "id";
    InputScoreFragment inptScoreFrg;
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

    public void callFragmentEvent() {
        EventFragment fragment2 = new EventFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainActivity, fragment2)
                .addToBackStack(null)
                .commit();
    }

    public void callFragmentTeam(Bundle bundle) {
        TeamFragment fragment2 = new TeamFragment();
        fragment2.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainActivity, fragment2)
                .addToBackStack(null)
                .commit();
    }

    public void callFragmentEventCreated(Bundle bundle) {
        EventCreatedFragment fragment2 = new EventCreatedFragment();
        fragment2.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainActivity, fragment2)
                .addToBackStack(null)
                .commit();
    }

    public void callDetailFragment() {
        BracketsFragment fragment2 = new BracketsFragment();
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
        /*Fragment fragment = UserProfile.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainActivity, fragment)
                .addToBackStack(null)
                .commit();*/
        inptScoreFrg = new InputScoreFragment();
        //fragment2.setTargetFragment(,1);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainActivity, inptScoreFrg)
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

    public void refreshInputScore(){
        inptScoreFrg = new InputScoreFragment();
        //fragment2.setTargetFragment(,1);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainActivity, inptScoreFrg)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendInput(BracketCard brckt) {
        inptScoreFrg.InputScore(brckt);
    }

}