package com.njy.seeking.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.njy.seeking.R;
import com.njy.seeking.company.ApplicantDetailActivity;
import com.njy.seeking.data.KEY;
import com.njy.seeking.model.Applicant;

import java.util.ArrayList;

/**
 * Created by naufalprakoso on 13/05/18.
 */

public class ApplicantAdapter extends RecyclerView.Adapter<ApplicantAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Applicant> applicants;

    public ArrayList<Applicant> getApplicants() {
        return applicants;
    }

    public void setApplicants(ArrayList<Applicant> applicants) {
        this.applicants = applicants;
    }

    public ApplicantAdapter(Context context, ArrayList<Applicant> applicants) {
        this.context = context;
        this.applicants = applicants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_applicant_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.txtName.setText(getApplicants().get(position).getName());
        holder.txtPhone.setText(getApplicants().get(position).getPhone());
        holder.txtInterested.setText(getApplicants().get(position).getInterested());
        holder.txtEmail.setText(getApplicants().get(position).getEmail());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ApplicantDetailActivity.class);

                final String getName, getVacancyId;

                getName = i.getStringExtra(KEY.NAME_GET_COMPANY_KEY);
                getVacancyId = i.getStringExtra(KEY.VACANCY_ID_KEY);

                i.putExtra(KEY.CERTIFICATION_ID_SEEKER_KEY, getApplicants().get(position).getCertificationid());
                i.putExtra(KEY.CERTIFICATION_COMPANY_SEEKER_KEY, getApplicants().get(position).getCertificationcompany());
                i.putExtra(KEY.EMAIL_SEEKER_KEY, getApplicants().get(position).getEmail());
                i.putExtra(KEY.GITHUB_SEEKER_KEY, getApplicants().get(position).getGithub());
                i.putExtra(KEY.INTERESTED_SEEKER_KEY, getApplicants().get(position).getInterested());
                i.putExtra(KEY.LAST_COMPANY_SEEKER_KEY, getApplicants().get(position).getLastcompany());
                i.putExtra(KEY.LAST_EDUCATION_SEEKER_KEY, getApplicants().get(position).getLasteducation());
                i.putExtra(KEY.LINKEDIN_SEEKER_KEY, getApplicants().get(position).getLinkedin());
                i.putExtra(KEY.NAME_SEEKER_KEY, getApplicants().get(position).getName());
                i.putExtra(KEY.PHONE_SEEKER_KEY, getApplicants().get(position).getPhone());
                i.putExtra(KEY.PROJECT_SEEKER_KEY, getApplicants().get(position).getProject());
                i.putExtra(KEY.WEBSITE_SEEKER_KEY, getApplicants().get(position).getWebsite());
                i.putExtra(KEY.WORK_PERIOD_SEEKER_KEY, getApplicants().get(position).getWorkperiod());
                i.putExtra(KEY.NAME_GET_COMPANY_KEY, getName);
                i.putExtra(KEY.VACANCY_ID_KEY, getVacancyId);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getApplicants().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView txtName, txtPhone, txtEmail, txtInterested;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtPhone = (TextView) itemView.findViewById(R.id.txt_phone);
            txtEmail = (TextView) itemView.findViewById(R.id.txt_email);
            txtInterested = (TextView) itemView.findViewById(R.id.txt_interested);
            imageView = (ImageView) itemView.findViewById(R.id.img_user);
            cardView = (CardView) itemView.findViewById(R.id.cv_item);
        }
    }
}
