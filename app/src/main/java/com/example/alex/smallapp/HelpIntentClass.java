package com.example.alex.smallapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Admin on 28.03.2016.
 */
public class HelpIntentClass implements Parcelable{

    public static ArrayList<Card> listofcards;

    public HelpIntentClass(ArrayList<Card> cardlist){

        listofcards = cardlist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override  // упаковываем объект в Parcel
    public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(listofcards);
    }

    public static final Parcelable.Creator<HelpIntentClass> CREATOR = new Parcelable.Creator<HelpIntentClass>(){

        public HelpIntentClass[] newArray(int size) {
            return new HelpIntentClass[size];
        }
        // распаковываем объект из Parcel
        public HelpIntentClass createFromParcel(Parcel in) {

            return new HelpIntentClass(in);
        }
    };

    // конструктор, считывающий данные из Parcel
    private HelpIntentClass(Parcel pl){
        //listofcards = pl.readArrayList();
    }
}
