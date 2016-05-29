package com.example.alex.smallapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 27.03.2016.
 */
public class Card implements Parcelable {

    String name;
    String drawablename;
    int rarity;
    String selecteddrawablename;
    boolean selected;
    static ImageView im;
    int elixircost;
    ArrayList<Card> badcards;
    ArrayList<Card> goodcards;

    Card(String name, String drawablename,String selecteddrawablename, int rarity,int elixircost )
    {
        this.name=name;
        this.drawablename=drawablename;
        this.rarity=rarity;
        this.selecteddrawablename =selecteddrawablename;
        this.selected = false;
        this.elixircost = elixircost;
        this.badcards=null;
        this.goodcards=null;
    }



    Card(String name, String drawablename,String selecteddrawablename, int rarity, int elixircost, ArrayList<Card> emenies ,ArrayList<Card> allies){

        this(name, drawablename,selecteddrawablename,rarity,elixircost);
        this.badcards=emenies;
        this.goodcards=allies;

    }

    public void setall(ArrayList<Card> badcards,ArrayList<Card> goodcards){
        this.badcards = badcards;
        this.goodcards  = goodcards;
    }

    //Is Any Card already selected? true - yes, false - no
    public static boolean isAnySelected(Card[] cards){

        for (int i = 0; i < cards.length; i++) {
            if(cards[i].selected) return true;
        }

        return false;
    }


    //3 Strongest Enemies (alpha)
    public static ArrayList<Card> biggestOpponents(ArrayList<Card> cards){

        HashMap<Card,Integer>  opponentshash = new HashMap<>();
        for (Card card: cards)
        {
           // Log.d("Tag","In this shit" + card.badcards.toString());
            if(card.badcards==null) continue;
            else {


                if(card.badcards.size()>0) {
                    Card c  = card.badcards.get(0);
                    if(ifContainsCard(opponentshash,card)){
                        Log.d("Tag" ,"not new  " + card.badcards.get(0).name);
                        opponentshash.put(c,1);
                    }
                    else {
                            Log.d("Tag", "NEW " + card.badcards.get(0).name);
                            opponentshash.put(c, 0);
                            //Log.d("Tag", " " + opponentshash.containsKey(c));
                    }

                }

                /*Log.d("Tag"," In there " + card.name);
                for (int i = 0; i < card.badcards.size(); i++) {
                    if (Card.ifContainsCard(opponentshash,card)) {
                        Log.d("Tag","In alredy exsit" + card.badcards.get(i).name);
                        //if card already exist +1 to total sum
                        //opponentshash.put(card.badcards.get(i), opponentshash.get(card.badcards.get(i)) + 1);
                        Log.d("Tag", "Added " + card.badcards.get(i).name);
                    } else {
                        Log.d("Tag","New one " + card.badcards.get(i).name);
                        //if not , just add this card to hash and give it 1 point
                        opponentshash.put(card.badcards.get(i), 1);
                        Log.d("Tag ",Card.ifContainsCard(opponentshash,card.badcards.get(i))+"");
                    }*/


            /*for (int i = 0; i < card.goodcards.size(); i++) {
                if(opponentshash.containsKey(card.goodcards.get(i))){
                    //if good card exist, it sub 1 to sum (if it's overlapping the bad card)) imho
                    opponentshash.put(card.goodcards.get(i),opponentshash.get(card.goodcards.get(i))-1);
                }
            }*/
            }
        }
        Log.d("Tag",opponentshash.size()+"");
        ArrayList<Card> worthcards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int max = -8;
            for (Map.Entry<Card,Integer> card: opponentshash.entrySet()) {


                if(card.getValue()>max) {
                    max = card.getValue();
                    worthcards.add(i, card.getKey());
                }
            }
            opponentshash.remove(worthcards.get(i));
        }

        Log.d("Tag", "worthcards "+ worthcards.size());
        return worthcards;
    }

    private static boolean ifContainsCard(HashMap<Card,Integer> hashMap,Card card){

        for (Card hash: hashMap.keySet()) {

            if(hash.name.equals(card.name)) return true;
        }

        return false;
    }




// Parcelabele methods to provide Intent ArrayList<Card? extends Parcelable>
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(drawablename);
            dest.writeString(selecteddrawablename);

            dest.writeInt(rarity);
            dest.writeInt(elixircost);
            dest.writeList(badcards);
          //  dest.writeList(goodcards);
    }

    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
        // распаковываем объект из Parcel
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    private Card(Parcel pl){

        name = pl.readString();
        drawablename = pl.readString();
        selecteddrawablename = pl.readString();
        rarity = pl.readInt();
        elixircost = pl.readInt();
        selected =false;
        badcards = pl.readArrayList(Card.class.getClassLoader());

    }

}
