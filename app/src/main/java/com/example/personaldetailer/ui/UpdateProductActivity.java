package com.example.personaldetailer.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.personaldetailer.R;
import com.example.personaldetailer.adapter.StudentListAdapter;
import com.example.personaldetailer.model.Student;
import com.example.personaldetailer.util.Constants;
import com.example.personaldetailer.util.GsonHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UpdateProductActivity extends AppCompatActivity {

    RecyclerView rvStudentList;
    LinearLayout linearEmpty;
    FloatingActionButton fabAdd;
    ArrayList<Student> studentArrayList = new ArrayList<>();
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        initViews();
        initEvents();
        loadDataFromFile();
    }

    private void initViews() {
        rvStudentList = findViewById(R.id.rvStudentList);
        linearEmpty = findViewById(R.id.linearEmpty);
        fabAdd = findViewById(R.id.fabAdd);
    }

    private void initEvents() {
        fabAdd.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddNewStudentActivity.class);
            startActivity(intent);
        });
    }

    private void populateStudentList() {
        StudentListAdapter studentListAdapter = new StudentListAdapter(this, studentArrayList);
        rvStudentList.setAdapter(studentListAdapter);
        rvStudentList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadDataFromFile() {
        FileInputStream fileInputStream;

        try {
            fileInputStream = openFileInput(Constants.DATA_STORE_FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder studentListStringBuilder = new StringBuilder();

            String character;
            while ((character = bufferedReader.readLine()) != null) {
                studentListStringBuilder.append(character);

                // Convert back to ArrayList of Students
                studentArrayList = GsonHelper.fromJsonString(gson, studentListStringBuilder.toString());

                if (!studentArrayList.isEmpty())
                    populateStudentList();
                else {
                    linearEmpty.setVisibility(View.VISIBLE);
                    rvStudentList.setVisibility(View.GONE);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            linearEmpty.setVisibility(View.VISIBLE);
            rvStudentList.setVisibility(View.GONE);
        }
    }
}