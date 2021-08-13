package id.ac.polman.astra.serojamatchmaker;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import id.ac.polman.astra.serojamatchmaker.entity.Event;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.AdapterHolder> implements Filterable {
    private Context mContext;
    private List<Event> mEventInputs;

    public Adapter2(Context context, List<Event> dataList){
        this.mContext = context;
        this.mEventInputs = dataList;
    }

    @NonNull
    @Override
    public Adapter2.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.event_list, parent, false);
        Adapter2.AdapterHolder holder = new Adapter2.AdapterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter2.AdapterHolder holder, int position) {
        final Event event = mEventInputs.get(position);

        String name = event.getEventName();
        Integer team = event.getNumberOfTeam();
        String status = event.getStatus();
        holder.name.setText(name);
        holder.team.setText(Integer.toString(team) + " Teams    -   Knockout");
        holder.status.setText(status);

        holder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EventUserFragment.class);
                intent.putExtra("data", event);
                intent.putExtra("event_name", event.getEventName().toString());
                intent.putExtra("number_of_team", Integer.toString(event.getNumberOfTeam()));
                intent.putExtra("status", event.getStatus().toString());
                intent.putExtra("last_modified", event.getLastModified());
                mContext.startActivity(intent);
                Log.e("Adapter","Berhasil Masuk DeEtail Event");
            }
        });

    }

    @Override
    public int getItemCount() {
        return mEventInputs.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    public void filterList(ArrayList<Event> filteredList) {
        mEventInputs = filteredList;
        notifyDataSetChanged();
    }

    Filter filter = new Filter() {
        //run on background
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Event> filteredList = new ArrayList<>();
            if(constraint.length() == 0){
                filteredList.addAll(mEventInputs);
            }else{
                for (Event r : mEventInputs){
                    if(r.getUserId().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filteredList.add(r);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        //runs on a ui
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mEventInputs.clear();
            mEventInputs.addAll((Collection<? extends Event>) results.values);
            notifyDataSetChanged();
        }
    };

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView name, team, status;

        LinearLayout select;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.eventName);
            team = itemView.findViewById(R.id.eventTeam);
            status = itemView.findViewById(R.id.eventStatus);
            select = itemView.findViewById(R.id.selectEvent);
        }
    }
}
