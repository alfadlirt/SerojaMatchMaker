package id.ac.polman.astra.serojamatchmaker.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.ac.polman.astra.serojamatchmaker.R;
import id.ac.polman.astra.serojamatchmaker.model.TeamCardInput;

public class TeamInputAdapter extends RecyclerView.Adapter<TeamInputAdapter.TeamInputHolder>{
    private Integer team_size;
    private LayoutInflater inflater;
    public static ArrayList<TeamCardInput> editModelArrayList;



    public static class TeamInputHolder extends RecyclerView.ViewHolder {
        protected EditText team_nameTxt;
        protected EditText instance_nameTxt;
        public TeamInputHolder(View view) {
            super(view);
            //mImageView = itemView.findViewById(R.id.imageView);
            //mTextView1 = itemView.findViewById(R.id.textView);
            //mTextView2 = itemView.findViewById(R.id.textView2);
            team_nameTxt = (EditText) view.findViewById(R.id.txtTeamName);

            team_nameTxt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    editModelArrayList.get(getAdapterPosition()).setTeam_name(team_nameTxt.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            instance_nameTxt = (EditText) view.findViewById(R.id.txtInstanceName);

            instance_nameTxt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    editModelArrayList.get(getAdapterPosition()).setInstance_name(instance_nameTxt.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            
        }
    }

    public TeamInputAdapter(Context ctx, ArrayList<TeamCardInput> editModelArrayList) {
        inflater = LayoutInflater.from(ctx);
        this.editModelArrayList = editModelArrayList;
    }
    @Override
    public TeamInputHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.team_input_card, parent, false);
        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_input_card, parent, false);
        TeamInputHolder evh = new TeamInputHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(TeamInputHolder holder, int position) {
        /*
        TeamCardInput currentItem = mExampleList.get(position);
        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
        */
        holder.team_nameTxt.setText(editModelArrayList.get(position).getTeam_name());
        holder.instance_nameTxt.setText(editModelArrayList.get(position).getInstance_name());

    }
    @Override
    public int getItemCount() {
        return editModelArrayList.size();
    }
    public ArrayList<TeamCardInput> getArrayList() {
        return editModelArrayList;
    }
}
