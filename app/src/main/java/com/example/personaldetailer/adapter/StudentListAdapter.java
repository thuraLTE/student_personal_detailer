package com.example.personaldetailer.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personaldetailer.R;
import com.example.personaldetailer.model.Student;
import com.example.personaldetailer.ui.StudentDetailActivity;
import com.example.personaldetailer.util.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {

    Context mContext;
    List<Student> studentList;

    public StudentListAdapter(Context mContext, List<Student> studentList) {
        this.mContext = mContext;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.student_detail_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student currentStudent = studentList.get(position);

        holder.tvName.setText(currentStudent.getName());
        holder.tvEmail.setText(currentStudent.getEmail());
        holder.tvGender.setText(currentStudent.getGender());
        holder.tvPhNum.setText(currentStudent.getPhNum());
        Picasso.get().load(Uri.parse(currentStudent.getProfileImgUrl())).placeholder(R.drawable.ic_image).into(holder.ivProfile);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, StudentDetailActivity.class);
            intent.putExtra(Constants.STUDENT_DETAIL_KEY, currentStudent);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvEmail, tvGender, tvPhNum;
        ImageView ivProfile;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvGender = itemView.findViewById(R.id.tvGender);
            tvPhNum = itemView.findViewById(R.id.tvPhNum);
            ivProfile = itemView.findViewById(R.id.ivProfile);
        }

    }
}
