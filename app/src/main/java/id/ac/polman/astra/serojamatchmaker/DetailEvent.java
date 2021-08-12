package id.ac.polman.astra.serojamatchmaker;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.ac.polman.astra.serojamatchmaker.entity.Event;

public class DetailEvent extends AppCompatActivity {

    RecyclerView mRecyclerView;

    TextView mName, mNOT, mType, mStatus, mLastM;

    private Event mEvent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_event);

        mName = findViewById(R.id.txtDtEventName);
        mNOT = findViewById(R.id.txtDtEventName);
        mType = findViewById(R.id.txtDtEventName);
        mStatus =  findViewById(R.id.txtDtEventName);
        mLastM = findViewById(R.id.txtDtEventName);

        String name = getIntent().getStringExtra("event_name");
        mName.setText(name);

        String team = getIntent().getStringExtra("number_of_team");
        mNOT.setText(team);

        String status = getIntent().getStringExtra("status");
        mStatus.setText(status);

        String modif = getIntent().getStringExtra("last_modified");
        mLastM.setText(modif);

        /*Intent intent = getIntent();
        if(intent.getExtras() != null) {
            mEvent = (Event) intent.getSerializableExtra("data");
            mName.setText(mEvent.getEventName());
            mNOT.setText(mEvent.getNumberOfTeam());
            mType.setText("Knockout");
            mStatus.setText(mEvent.getStatus());
            mLastM.setText(mEvent.getLastModified().toString());
            *//*mName.setText("name");
            mNOT.setText("Number team");
            mType.setText("Knockout");
            mStatus.setText("status");
            mLastM.setText("17-17-17");*//*
        }*/
    }


   /* public interface Callbacks{
        public void onStartDetailEvent();
    }

    private DetailEventFragment.Callbacks mCallbacks = null;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mCallbacks = (DetailEventFragment.Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }*/
}