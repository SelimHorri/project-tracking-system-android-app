package com.selimhorri.pack.pattern.adapter.employee;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selimhorri.pack.R;
import com.selimhorri.pack.activity.employee.EmployeeAddCommitActivity;
import com.selimhorri.pack.activity.employee.EmployeeShowAllCommitsActivity;
import com.selimhorri.pack.activity.employee.EmployeeShowCommitsActivity;
import com.selimhorri.pack.model.dto.custom.EmployeeProjectData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EmployeeIndexCustomAdapter extends RecyclerView.Adapter<EmployeeIndexCustomAdapter.ViewHolder> {

    private List<EmployeeProjectData> employeeProjectDataList;
    private Context context;

    public EmployeeIndexCustomAdapter(List<EmployeeProjectData> employeeProjectDataList, Context context) {
        this.employeeProjectDataList = employeeProjectDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.activity_employee_project_data_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeIndexCustomAdapter.ViewHolder holder, int position) {

        EmployeeProjectData epd = this.employeeProjectDataList.get(position);
        holder.textViewTitle.setText(epd.getTitle());
        holder.textViewStartDate.setText("Start date: " + LocalDate.parse(epd.getStartDate()).format(DateTimeFormatter.ofPattern("dd-M-yyyy")));
        holder.textViewEndDate.setText("End date: " + LocalDate.parse(epd.getEndDate()).format(DateTimeFormatter.ofPattern("dd-M-yyyy")));
        holder.textViewStatus.setText("Status: "+ epd.getStatus());
        holder.btnMyCommits.setOnClickListener(v -> {
            this.context.startActivity(
                    new Intent(this.context, EmployeeShowCommitsActivity.class)
                            .putExtra("projectId", epd.getProjectId())
            );
        });
        holder.btnAllCommits.setOnClickListener(v -> {
            this.context.startActivity(
                    new Intent(this.context, EmployeeShowAllCommitsActivity.class)
                            .putExtra("projectId", epd.getProjectId())
            );
        });
        holder.btnNewCommit.setOnClickListener(v -> {
            this.context.startActivity(
                    new Intent(this.context, EmployeeAddCommitActivity.class)
                            .putExtra("projectId", epd.getProjectId())
            );
        });
    }

    @Override
    public int getItemCount() {
        return this.employeeProjectDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewStartDate;
        private TextView textViewEndDate;
        private TextView textViewStatus;
        private Button btnMyCommits;
        private Button btnAllCommits;
        private Button btnNewCommit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewTitle = itemView.findViewById(R.id.textViewTitle);
            this.textViewStartDate = itemView.findViewById(R.id.textViewStartDate);
            this.textViewEndDate = itemView.findViewById(R.id.textViewEndDate);
            this.textViewStatus = itemView.findViewById(R.id.textViewStatus);
            this.btnMyCommits = itemView.findViewById(R.id.buttonMyCommits);
            this.btnAllCommits = itemView.findViewById(R.id.buttonAllCommits);
            this.btnNewCommit = itemView.findViewById(R.id.buttonNewCommit);
        }



    }



}
