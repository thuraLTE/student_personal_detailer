package com.example.personaldetailer.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.personaldetailer.R;
import com.example.personaldetailer.model.Student;
import com.example.personaldetailer.util.Constants;
import com.squareup.picasso.Picasso;

public class StudentDetailActivity extends AppCompatActivity {

    TextView tvName, tvEmail, tvNrc, tvGender, tvPhNum;
    ImageView ivProfile, ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        initViews();
        initEvents();
        populateData();
    }

    private void initViews() {
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvNrc = findViewById(R.id.tvNrc);
        tvGender = findViewById(R.id.tvGender);
        tvPhNum = findViewById(R.id.tvPhNum);

        ivProfile = findViewById(R.id.ivProfile);
        ivBack = findViewById(R.id.ivBack);
    }

    private void initEvents() {
        ivBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void populateData() {
        Student currentStudent = (Student) getIntent().getSerializableExtra(Constants.STUDENT_DETAIL_KEY);

        tvName.setText(currentStudent.getName());
        tvEmail.setText(currentStudent.getEmail());
        tvNrc.setText(currentStudent.getNrc());
        tvGender.setText(currentStudent.getGender());
        tvPhNum.setText(currentStudent.getPhNum());

        Picasso.get().load(Uri.parse(currentStudent.getProfileImgUrl())).placeholder(R.drawable.ic_image).into(ivProfile);
    }
}