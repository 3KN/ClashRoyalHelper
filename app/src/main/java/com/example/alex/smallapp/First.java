package com.example.alex.smallapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class First extends AppCompatActivity implements View.OnClickListener {


    Button deck;
    Button arena;
    Button tact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        deck = (Button) findViewById(R.id.picker_btn);
        arena = (Button) findViewById(R.id.arena_btn);
        tact= (Button) findViewById(R.id.strategy_btn);

        deck.setOnClickListener(this);
        arena.setOnClickListener(this);
        tact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        if(v.getId()==R.id.picker_btn){
            intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}
