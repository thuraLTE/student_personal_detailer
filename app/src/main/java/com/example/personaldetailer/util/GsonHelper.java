package com.example.personaldetailer.util;

import androidx.annotation.NonNull;

import com.example.personaldetailer.model.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class GsonHelper {

    public static String toJsonString(@NonNull Gson gson, ArrayList<Student> list) {
        return gson.toJson(list);
    }

    public static ArrayList<Student> fromJsonString(@NonNull Gson gson, String jsonString) {
        return gson.fromJson(jsonString, new TypeToken<List<Student>>() {}.getType());
    }
}
