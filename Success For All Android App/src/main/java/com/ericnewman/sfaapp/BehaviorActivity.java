package com.ericnewman.sfaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ericnewman.sfaapp.databinding.ActivityAcademicBinding;
import com.ericnewman.sfaapp.databinding.ActivityBehaviorBinding;

public class BehaviorActivity extends AppCompatActivity {

    ActivityBehaviorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBehaviorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.main.setOnClickListener(v -> {
            Intent intent = new Intent(BehaviorActivity.this, MainActivity.class);
            startActivity(intent);
        });
        binding.laws.setOnClickListener(v -> {
            Intent intent = new Intent(BehaviorActivity.this, LawsActivity.class);
            startActivity(intent);
        });
        binding.behavior.setOnClickListener(v -> {
            Intent intent = new Intent(BehaviorActivity.this, BehaviorActivity.class);
            startActivity(intent);
        });
        binding.academic.setOnClickListener(v -> {
            Intent intent = new Intent(BehaviorActivity.this, AcademicActivity.class);
            startActivity(intent);
        });

    }
}