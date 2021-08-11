package id.ac.polman.astra.serojamatchmaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import id.ac.polman.astra.serojamatchmaker.entity.EventInput;

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterHolder> implements Filterable {

    private Context mContext;
    private List<EventInput> mEventInputs;
    private List<EventInput> mEventInputAll;

    public Adapter(Context context, List<EventInput> dataList){
        this.mContext = context;
        this.mEventInputs = dataList;

        this.mEventInputAll = new ArrayList<>(dataList);
    }

    @NonNull
    @Override
    public Adapter.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.event_list, parent, false);
        AdapterHolder holder = new AdapterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.AdapterHolder holder, int position) {
        final EventInput EventInput = mEventInputs.get(position);
        String name = EventInput.getEvent_name();
        Integer team = EventInput.getNumber_of_team();
        holder.name.setText(name);
        holder.team.setText(team);
    }

    @Override
    public int getItemCount() {
        return mEventInputs.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        //run on background
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<EventInput> filteredList = new ArrayList<>();
            if(constraint.toString().isEmpty()){
                filteredList.addAll(mEventInputAll);
            }else{
                for (EventInput r : mEventInputAll){
                    if(r.getEvent_name().toLowerCase().contains(constraint.toString().toLowerCase())){
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
            mEventInputs.addAll((Collection<? extends EventInput>) results.values);
            notifyDataSetChanged();
        }
    };

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView name, team;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.eventName);
            team = itemView.findViewById(R.id.eventTeam);
        }
    }
}
