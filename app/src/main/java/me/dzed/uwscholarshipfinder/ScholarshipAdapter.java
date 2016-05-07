package me.dzed.uwscholarshipfinder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dzklavier on 2016-02-14.
 */
public class ScholarshipAdapter extends RecyclerView.Adapter
        <ScholarshipAdapter.ScholarshipViewHolder> {

    private List<Scholarship> scholarshipList;

    public ScholarshipAdapter(List<Scholarship> scholarshipList) {
        this.scholarshipList = scholarshipList;
    }

    @Override
    public int getItemCount() {
        return scholarshipList.size();
    }

    @Override
    public ScholarshipViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_layout, parent, false);
        return new ScholarshipViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ScholarshipViewHolder holder, int position) {
        Scholarship sch = scholarshipList.get(position);
        holder.vTitle.setText(sch.getTitle());
        holder.vValue.setText(sch.getValue());
        holder.vDeadline.setText(sch.getDeadlines());
    }

    public static class ScholarshipViewHolder extends RecyclerView.ViewHolder {
        protected TextView vTitle;
        protected TextView vValue;
        protected TextView vDeadline;

        public ScholarshipViewHolder(View v) {
            super(v);
            vTitle = (TextView) v.findViewById(R.id.title);
            vValue =  (TextView) v.findViewById(R.id.value);
            vDeadline = (TextView)  v.findViewById(R.id.deadline);

        }
    }
}
