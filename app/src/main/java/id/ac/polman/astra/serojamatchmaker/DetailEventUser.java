package id.ac.polman.astra.serojamatchmaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import id.ac.polman.astra.serojamatchmaker.entity.Event;

public class DetailEventUser extends AppCompatActivity {

    RecyclerView mRecyclerView;

    TextView mName, mNOT, mType, mStatus, mLastM;

    private Event mEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event_user);

        mName = findViewById(R.id.txtDtUEventName);
        mNOT = findViewById(R.id.txtDtUEventName);
        mType = findViewById(R.id.txtDtUEventName);
        mStatus =  findViewById(R.id.txtDtUEventName);
        mLastM = findViewById(R.id.txtDtUEventName);

        String name = getIntent().getStringExtra("event_name");
        mName.setText(name);

        String team = getIntent().getStringExtra("number_of_team");
        mNOT.setText(team);

        String status = getIntent().getStringExtra("status");
        mStatus.setText(status);

        String modif = getIntent().getStringExtra("last_modified");
        mLastM.setText(modif);
    }
}