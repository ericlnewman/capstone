package com.ericnewman.sfaapp.dto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ericnewman.sfaapp.MainActivity;
import com.ericnewman.sfaapp.R;

public class DetailActivity extends AppCompatActivity
{
    ConcernsDTO concernsDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSelectedConcern();
        setValues();

    }

    private void getSelectedConcern()
    {
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");
        concernsDTO = MainActivity.concernsList.get(Integer.valueOf(parsedStringID));
    }

    private void setValues()
    {
        TextView tv = findViewById(R.id.concernName);
        TextView iv = findViewById(R.id.concernDescription);

        tv.setText(concernsDTO.getConcernsName());
        iv.setText(concernsDTO.getConcernDescription());
    }
}