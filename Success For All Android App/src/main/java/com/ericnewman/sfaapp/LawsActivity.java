package com.ericnewman.sfaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ericnewman.sfaapp.databinding.ActivityAcademicBinding;
import com.ericnewman.sfaapp.databinding.ActivityLawsBinding;

public class LawsActivity extends AppCompatActivity {

    ActivityLawsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLawsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.main.setOnClickListener(v -> {
            Intent intent = new Intent(LawsActivity.this, MainActivity.class);
            startActivity(intent);
        });
        binding.laws.setOnClickListener(v -> {
            Intent intent = new Intent(LawsActivity.this, LawsActivity.class);
            startActivity(intent);
        });
        binding.behavior.setOnClickListener(v -> {
            Intent intent = new Intent(LawsActivity.this, BehaviorActivity.class);
            startActivity(intent);
        });
        binding.academic.setOnClickListener(v -> {
            Intent intent = new Intent(LawsActivity.this, AcademicActivity.class);
            startActivity(intent);
        });

    }
}