package com.example.alex.smallapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class NewDeck extends AppCompatActivity implements View.OnClickListener {
    Button newaverage;

    ImageView im1;
    ImageView im2;
    ImageView im3;
    ImageView im4;
    ImageView im5;
    ImageView im6;
    ImageView im7;
    ImageView im8;

    ImageView op1;
    ImageView op2;
    ImageView op3;

    Button makeanother;
    Button savedeck;

    ArrayList<Card>  arrayofcards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_deck);

        newaverage = (Button) findViewById(R.id.newaverage);

        makeanother = (Button) findViewById(R.id.makeanother_btn);
        makeanother.setOnClickListener(this);
        savedeck = (Button) findViewById(R.id.savedeck_btn);
        savedeck.setOnClickListener(this);


        Intent intent =getIntent();
        //Taking info about elixir from previous activity
        newaverage.setText(intent.getStringExtra("Avarage elixir") + "   ");
        //Taking List form previos activity
        arrayofcards = intent.getParcelableArrayListExtra("Array of cards");

      //  newaverage.setText(arrayofcards.get(0).badcards.get(0) + " "+ arrayofcards.get(7).elixircost);
        //setting Images of deck
        setImages();

        ArrayList<Card> opponents = Card.biggestOpponents(arrayofcards);

        //newaverage.setText(opponents.get(0).name+" " + opponents.get(1).name+" "+opponents.get(2).name);

        op1.setImageResource(getResources().getIdentifier(opponents.get(0).drawablename,"drawable",getPackageName()));
        op2.setImageResource(getResources().getIdentifier(opponents.get(1).drawablename,"drawable",getPackageName()));
        op3.setImageResource(getResources().getIdentifier(opponents.get(2).drawablename,"drawable",getPackageName()));






    }

    private void setImages(){

        im1 = (ImageView) findViewById(R.id.image1);
        im2 = (ImageView) findViewById(R.id.image2);
        im3 = (ImageView) findViewById(R.id.image3);
        im4 = (ImageView) findViewById(R.id.image4);
        im5 = (ImageView) findViewById(R.id.image5);
        im6 = (ImageView) findViewById(R.id.image6);
        im7 = (ImageView) findViewById(R.id.image7);
        im8 = (ImageView) findViewById(R.id.image8);

        op1 = (ImageView) findViewById(R.id.opponent1);
        op2 = (ImageView) findViewById(R.id.opponent2);
        op3 = (ImageView) findViewById(R.id.opponent3);

        im1.setImageResource(getResources().getIdentifier(arrayofcards.get(0).drawablename,"drawable",getPackageName()));
        im2.setImageResource(getResources().getIdentifier(arrayofcards.get(1).drawablename,"drawable",getPackageName()));
        im3.setImageResource(getResources().getIdentifier(arrayofcards.get(2).drawablename,"drawable",getPackageName()));
        im4.setImageResource(getResources().getIdentifier(arrayofcards.get(3).drawablename,"drawable",getPackageName()));
        im5.setImageResource(getResources().getIdentifier(arrayofcards.get(4).drawablename,"drawable",getPackageName()));
        im6.setImageResource(getResources().getIdentifier(arrayofcards.get(5).drawablename,"drawable",getPackageName()));
        im7.setImageResource(getResources().getIdentifier(arrayofcards.get(6).drawablename,"drawable",getPackageName()));
        im8.setImageResource(getResources().getIdentifier(arrayofcards.get(7).drawablename, "drawable", getPackageName()));

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.makeanother_btn){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else if(v.getId()==R.id.savedeck_btn)
        {
            Toast.makeText(this, "Your Deck saved successfully", Toast.LENGTH_SHORT).show();
        }

    }
}
