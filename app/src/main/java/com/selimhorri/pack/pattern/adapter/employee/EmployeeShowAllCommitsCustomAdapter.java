package com.selimhorri.pack.pattern.adapter.employee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selimhorri.pack.R;
import com.selimhorri.pack.model.dto.custom.ProjectCommit;
import com.selimhorri.pack.service.ProjectService;
import com.selimhorri.pack.service.impl.ProjectServiceImpl;

import java.util.List;

public class EmployeeShowAllCommitsCustomAdapter extends RecyclerView.Adapter<EmployeeShowAllCommitsCustomAdapter.ViewHolder> {

    private final ProjectService projectService;
    private List<ProjectCommit> projectCommitList;
    private Context context;

    public EmployeeShowAllCommitsCustomAdapter(Context context, List<ProjectCommit> projectCommitList) {
        this.context = context;
        this.projectCommitList = projectCommitList;
        this.projectService = new ProjectServiceImpl(context);
    }

    @NonNull
    @Override
    public EmployeeShowAllCommitsCustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_project_commit_item, parent, false);

        return new EmployeeShowAllCommitsCustomAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeShowAllCommitsCustomAdapter.ViewHolder holder, int position) {

        this.projectService.findById(
                this.projectCommitList.get(position).getProjectId(),
                response -> {
                    ProjectCommit pc = this.projectCommitList.get(position);
                    holder.textViewTitle.setText(response.getTitle());
                    holder.textViewCommitDate.setText(pc.getCommitDate().toString());
                    holder.textViewFullName.setText(pc.getFirstName() + " " + pc.getLastName());
                    holder.textViewCommitEmpDesc.setText(pc.getCommitEmpDesc());
                    holder.textViewCommitMgrDesc.setText(pc.getCommitMgrDesc());
                },
                error -> Toast.makeText(context, "Problem", Toast.LENGTH_SHORT).show()
        );

    }

    @Override
    public int getItemCount() {
        return this.projectCommitList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private TextView textViewCommitDate;
        private TextView textViewFullName;
        private TextView textViewCommitEmpDesc;
        private TextView textViewCommitMgrDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewTitle = itemView.findViewById(R.id.textViewEmployeeShowCommitsTitle);
            this.textViewCommitDate = itemView.findViewById(R.id.textViewEmployeeShowCommitsCommitDate);
            this.textViewFullName = itemView.findViewById(R.id.textViewEmployeeShowCommitsFullName);
            this.textViewCommitEmpDesc = itemView.findViewById(R.id.textViewEmployeeShowCommitsCommitEmpDesc);
            this.textViewCommitMgrDesc = itemView.findViewById(R.id.textViewEmployeeShowCommitsCommitMgrDesc);
        }


    }



}