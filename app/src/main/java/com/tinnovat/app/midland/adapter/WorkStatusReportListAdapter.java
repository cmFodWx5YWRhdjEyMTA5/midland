package com.tinnovat.app.midland.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tinnovat.app.midland.Activity.R;
import com.tinnovat.app.midland.WorkStatusReportListView;

import java.util.List;

public class WorkStatusReportListAdapter extends RecyclerView.Adapter<WorkStatusReportListAdapter.MyViewHolder> {
    private List<WorkStatusReportListView> reportList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView project;
        public TextView work;
        public TextView workName;
        public TextView actualRate;
        public TextView estimatedRate;

        MyViewHolder(View view) {
            super(view);
            project = view.findViewById(R.id.project);
            work = view.findViewById(R.id.work);
            workName = view.findViewById(R.id.workName);
            actualRate = view.findViewById(R.id.actualRate);
            estimatedRate = view.findViewById(R.id.estimatedRate);
        }
    }


    public WorkStatusReportListAdapter(List<WorkStatusReportListView> reportList) {
        this.reportList = reportList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workstatus_report_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        WorkStatusReportListView workStatusReportListView = reportList.get(position);
        holder.project.setText(workStatusReportListView.getName());
        holder.work.setText(workStatusReportListView.getWork());
        holder.workName.setText(workStatusReportListView.getWorkName());
        holder.actualRate.setText(workStatusReportListView.getActalRate());
        holder.estimatedRate.setText(workStatusReportListView.getEstimatedRate());
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }
}
