package com.example.personaldetailer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.personaldetailer.R;
import com.example.personaldetailer.model.Student;
import com.example.personaldetailer.util.Constants;
import com.example.personaldetailer.util.GsonHelper;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AddNewStudentActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtNrc, edtPhNum, edtImageUrl;
    RadioButton radioMale, radioFemale, radioOthers;
    AppCompatButton btnConfirm;
    ImageView ivBack;
    ArrayList<Student> studentArrayList = new ArrayList<>();
    Gson gson = new Gson();
    String studentListAsString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);

        initViews();
        initEvents();
    }

    private void initViews() {
        edtName = findViewById(R.id.edtName);
        edtEmail= findViewById(R.id.edtEmail);
        edtNrc = findViewById(R.id.edtNrc);
        edtPhNum = findViewById(R.id.edtPhNum);
        edtImageUrl = findViewById(R.id.edtImageUrl);

        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        radioOthers = findViewById(R.id.radioOthers);

        btnConfirm = findViewById(R.id.btnConfirm);
        ivBack = findViewById(R.id.ivBack);
    }

    private void initEvents() {

        loadDataFromFile();

        btnConfirm.setOnClickListener(view -> {

            String name = edtName.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String nrc = edtNrc.getText().toString().trim();
            String gender = getInputFromGenderSelection();
            String phNum = edtPhNum.getText().toString().trim();
            String profileImgUrl = edtImageUrl.getText().toString().trim();

            studentArrayList.add(new Student(name, email, nrc, gender, phNum, profileImgUrl));

            // Convert to Json String
            studentListAsString = GsonHelper.toJsonString(gson, studentArrayList);

            saveDataToLocalFile();

            startActivity(new Intent(this, UpdateProductActivity.class));
            finishAffinity();
        });

        ivBack.setOnClickListener(view -> {
            onBackPressed();
        });
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
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDataToLocalFile() {
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = openFileOutput(Constants.DATA_STORE_FILE_NAME, MODE_PRIVATE);
            fileOutputStream.write(studentListAsString.getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getInputFromGenderSelection() {
        String userSelection = null;
        if (radioMale.isChecked())
            userSelection = radioMale.getText().toString();
        else if (radioFemale.isChecked())
            userSelection = radioFemale.getText().toString();
        else if (radioOthers.isChecked())
            userSelection = radioOthers.getText().toString();
        return userSelection;
    }
}