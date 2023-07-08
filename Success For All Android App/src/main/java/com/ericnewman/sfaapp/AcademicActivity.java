package com.ericnewman.sfaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.ericnewman.sfaapp.databinding.ActivityAcademicBinding;
import com.ericnewman.sfaapp.databinding.ActivityMainBinding;
import com.ericnewman.sfaapp.dto.ConcernsDTO;

import java.util.ArrayList;

public class AcademicActivity extends AppCompatActivity {

    public static ArrayList<ConcernsDTO> shapeList = new ArrayList<ConcernsDTO>();

    private ListView listView;
    ActivityAcademicBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAcademicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.main.setOnClickListener(v -> {
            Intent intent = new Intent(AcademicActivity.this, MainActivity.class);
            startActivity(intent);
        });
        binding.laws.setOnClickListener(v -> {
            Intent intent = new Intent(AcademicActivity.this, LawsActivity.class);
            startActivity(intent);
        });
        binding.behavior.setOnClickListener(v -> {
            Intent intent = new Intent(AcademicActivity.this, BehaviorActivity.class);
            startActivity(intent);
        });
        binding.academic.setOnClickListener(v -> {
            Intent intent = new Intent(AcademicActivity.this, AcademicActivity.class);
            startActivity(intent);
        });


    }

}