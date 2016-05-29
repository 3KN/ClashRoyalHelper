package com.example.alex.smallapp;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    LinearLayout linearLayout;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;

    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
    ImageView image6;
    ImageView image7;
    ImageView image8;
    Button but;
    Button elixirbut;

    Card[] activecards = new Card[3];

    ArrayList<Card> usedcards = new ArrayList<Card>();

    Stack<ImageView> imageStack = new Stack<ImageView>();

    HashMap<Integer, Card> cards = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addCharacters(cards);

        addtoStack(imageStack);


        linearLayout = (LinearLayout) findViewById(R.id.layout);
        imageView1 = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        elixirbut = (Button) findViewById(R.id.elixirbutton);


        but = (Button) findViewById(R.id.button);

        setImages(cards);



        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        but.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

       // linearLayout.setPadding(0,10,0,0);
        switch (v.getId()){
            case R.id.imageView:
                if(Card.isAnySelected(activecards)) {
                    unselectall(activecards);
                }
                imageView1.setImageResource(getResources().getIdentifier(activecards[0].selecteddrawablename,"drawable",getPackageName()));
                activecards[0].selected=true;
                break;
            case R.id.imageView2:
                if(Card.isAnySelected(activecards)) {
                    unselectall(activecards);
                }
                imageView2.setImageResource(getResources().getIdentifier(activecards[1].selecteddrawablename,"drawable",getPackageName()));
                activecards[1].selected=true;
                break;
            case R.id.imageView3:
                if(Card.isAnySelected(activecards)) {
                    unselectall(activecards);
                }
                imageView3.setImageResource(getResources().getIdentifier(activecards[2].selecteddrawablename,"drawable",getPackageName()));
                activecards[2].selected=true;
                break;
            case R.id.button:

                if(imageStack.size()>0)
                {
                    if(Card.isAnySelected(activecards)) {
                        for (int i = 0; i < activecards.length; i++) {
                            if (activecards[i].selected) {

                                if(imageStack.size()==1){
                                    ImageView temp = imageStack.pop();
                                    temp.setImageResource(getResources().getIdentifier(activecards[i].drawablename, "drawable", getPackageName()));
                                    usedcards.add(activecards[i]);
                                    elixirbut.setText("Average Elixir cost : " + avaregeElixir(usedcards));
                                    unselectall(activecards);

                                    Intent intent = new Intent(this,NewDeck.class);
                                    intent.putParcelableArrayListExtra("Array of cards",usedcards);
                                    intent.putExtra("Avarage elixir",elixirbut.getText().toString());
                                    startActivity(intent);



                                }
                                else {
                                    ImageView temp = imageStack.pop();
                                    temp.setImageResource(getResources().getIdentifier(activecards[i].drawablename, "drawable", getPackageName()));
                                    usedcards.add(activecards[i]);
                                    elixirbut.setText("Average Elixir cost : " + avaregeElixir(usedcards));
                                    setImages(cards);
                                    unselectall(activecards);
                                    break;
                                }
                            }

                        }
                    }
                    else Toast.makeText(this, "No Card Selected", Toast.LENGTH_SHORT).show();
                }
                else if(imageStack.size()==0){
                    Intent intent = new Intent(this,NewDeck.class);
                    intent.putParcelableArrayListExtra("Array of cards",usedcards);
                    intent.putExtra("Avarage elixir",elixirbut.getText().toString());
                    startActivity(intent);
                    Toast.makeText(this,"Already Full Set",Toast.LENGTH_SHORT).show();
                }



        }

       // imageView1.setImageResource(R.drawable.balloncardselected);

    }

    private void unselectall(Card[] cards){

        imageView1.setImageResource(getResources().getIdentifier(cards[0].drawablename,"drawable",getPackageName()));
        imageView2.setImageResource(getResources().getIdentifier(cards[1].drawablename,"drawable",getPackageName()));
        imageView3.setImageResource(getResources().getIdentifier(cards[2].drawablename,"drawable",getPackageName()));

        cards[0].selected=false;
        cards[1].selected=false;
        cards[2].selected=false;

    }

    private void setImages(HashMap<Integer,Card> cards){



        Random random = new Random();
        int first;
        while(true) {
            first = random.nextInt(cards.size());
            if(!isUsed(first,usedcards)) break;
        }
        int second;
        int third;
        while (true){

            second = random.nextInt(cards.size());
            if(second==first || isUsed(second,usedcards)) continue;
            else{
                third = random.nextInt(cards.size());
                if(third ==second || third == first || isUsed(third,usedcards)) continue;
                else break;
            }

        }


        for (Map.Entry<Integer,Card> hash: cards.entrySet()) {

            Card c = hash.getValue();
            if(hash.getKey()==first){
                activecards[0] = c;
                imageView1.setImageResource(getResources().getIdentifier(c.drawablename,"drawable",getPackageName()));
            }
            else if(hash.getKey()==second){
                activecards[1] = c;
                imageView2.setImageResource(getResources().getIdentifier(c.drawablename,"drawable",getPackageName()));
            }
            else if(hash.getKey() == third){
                activecards[2] = c;
                imageView3.setImageResource(getResources().getIdentifier(c.drawablename, "drawable",getPackageName()));
            }
        }

        //but.setText(" " + first + " " + second + " " + third);

    }

    private void addCharacters(HashMap<Integer,Card>  cards){

        Card Ballom ;// = new Card("Ballon", "ballon", "ballons", 3,5);
        Card Pekka ;//= new Card("Pekka", "pekkabig", "pekkabigs", 3,8);
        Card Minions ;// = new Card("Minions", "minions", "minionss", 1,5);
        Card Zap ;//=new Card("Zap", "zap", "zaps", 1,2);
        Card Wizard ;//= new Card("Wizard","wizard","wizards",2,5);
        Card MiniPekka ;//= new Card("Mini PEKKA","minipekka","minipekkas",2,4);
        Card Witch;// = new Card("Witch","witch","witchs",3,5);
        Card HogRider;// = new Card("Hog Rider","hogrider","hogriders",2,4);
        Card GiantSkeleton ;//= new Card("Giant Skeleton","giantskeleton","giantskeletons",3,6);
        Card BabyDragon ;//=new Card("Baby Dragon", "babydragon", "babydragons", 3,4);
        Card Giant ;//= new Card("Giant", "giant", "giants", 2,5);
        Card Musketeer ;//= new Card("Musketeer", "musketeer", "musketeers", 2, 5);
        Card SkeletonaArmy;//=new Card("Skeleton Army", "skeletonarmy", "skeletonarmys", 3,4);
        Card Valkyrie;// = new Card("Valkyrie", "valkyrie", "valkyries", 2, 4);
        Card Golem;// = new Card("Golem", "golem", "golems", 3,8);
        Card Prince;
        Card Rage;
        Card Rocket;
        Card RoyalGiant;
        Card Skelet;
        Card SmallMinions;

/*
        Ballom.setall(new ArrayList<Card>(Arrays.asList(Giant,Valkyrie,Golem)),null);
        Pekka.setall(new ArrayList<Card>(Arrays.asList(Giant,BabyDragon,Golem)),null);
        Minions.setall(new ArrayList<Card>(Arrays.asList(Giant)),null);
        Zap.setall(new ArrayList<Card>(Arrays.asList(Giant,Valkyrie,Witch)),null);
        Wizard.setall(new ArrayList<Card>(Arrays.asList(Giant,Valkyrie,Golem)),null);
        MiniPekka.setall(new ArrayList<Card>(Arrays.asList(Giant,Valkyrie,Golem)),null);
        Witch.setall(new ArrayList<Card>(Arrays.asList(Wizard,Valkyrie,Golem)),null);
        HogRider.setall(new ArrayList<Card>(Arrays.asList(Giant,Valkyrie,Golem)),null);
        GiantSkeleton.setall(new ArrayList<Card>(Arrays.asList(Giant,Valkyrie,Golem)),null);
        BabyDragon.setall(new ArrayList<Card>(Arrays.asList(HogRider,Valkyrie,Golem)),null);
        Giant.setall(new ArrayList<Card>(Arrays.asList(Giant,Valkyrie,Golem)),null);
        Musketeer.setall(new ArrayList<Card>(Arrays.asList(Giant,Valkyrie,Golem)),null);
        SkeletonaArmy.setall(new ArrayList<Card>(Arrays.asList(Giant,Valkyrie,Golem)),null);
        Valkyrie.setall(new ArrayList<Card>(Arrays.asList(Giant,Valkyrie,Golem)),null);
        Golem.setall(new ArrayList<Card>(Arrays.asList(Giant,Valkyrie,Golem)),null);
*/

        Ballom = new Card("Ballon", "ballon", "ballons", 3,5);
        Valkyrie = new Card("Valkyrie", "valkyrie", "valkyries", 2, 4,new ArrayList<Card>(Arrays.asList(Ballom)),new ArrayList<Card>());
        Giant = new Card("Giant", "giant", "giants", 2,5,new ArrayList<Card>(Arrays.asList(Valkyrie)),new ArrayList<Card>());
        Golem= new Card("Golem", "golem", "golems", 3,8,new ArrayList<Card>(Arrays.asList(Ballom,Valkyrie)),new ArrayList<Card>());
        Pekka= new Card("Pekka", "pekkabig", "pekkabigs", 3,8, new ArrayList<Card>(Arrays.asList(Ballom,Golem)),new ArrayList<Card>());
        Zap = new Card("Zap", "zap", "zaps", 1,2,new ArrayList<Card>(Arrays.asList(Giant,Valkyrie)),new ArrayList<Card>());
        Wizard = new Card("Wizard","wizard","wizards",2,5,new ArrayList<Card>(Arrays.asList(Pekka,Valkyrie)),new ArrayList<Card>());
        MiniPekka = new Card("Mini PEKKA","minipekka","minipekkas",2,4,new ArrayList<Card>(Arrays.asList(Giant,Valkyrie)),new ArrayList<Card>());
        Witch = new Card("Witch","witch","witchs",3,5,new ArrayList<Card>(Arrays.asList(Pekka,MiniPekka)),new ArrayList<Card>());
        HogRider = new Card("Hog Rider","hogrider","hogriders",2,4,new ArrayList<Card>(Arrays.asList(Zap,Wizard)),new ArrayList<Card>());
        GiantSkeleton = new Card("Giant Skeleton","giantskeleton","giantskeletons",3,6,new ArrayList<Card>(Arrays.asList(HogRider,Witch)),new ArrayList<Card>());
        BabyDragon =new Card("Baby Dragon", "babydragon", "babydragons", 3,4,new ArrayList<Card>(Arrays.asList(Witch,HogRider)),new ArrayList<Card>());
        Musketeer = new Card("Musketeer", "musketeer", "musketeers", 2, 5,new ArrayList<Card>(Arrays.asList(Zap,BabyDragon)),new ArrayList<Card>());
        SkeletonaArmy = new Card("Skeleton Army", "skeletonarmy", "skeletonarmys", 3,4,new ArrayList<Card>(Arrays.asList(Golem,Musketeer)),new ArrayList<Card>());
        Minions = new Card("Minions", "minions", "minionss", 1,5,new ArrayList<Card>(Arrays.asList(HogRider,BabyDragon,SkeletonaArmy)),new ArrayList<Card>());
        Prince = new Card("Prince","prince","princes",3,5,new ArrayList<Card>(Arrays.asList(Minions,BabyDragon,SkeletonaArmy)),new ArrayList<Card>());
        Rage = new Card("Rage","rage","rages",2,3,new ArrayList<Card>(Arrays.asList(Prince,Musketeer)),new ArrayList<Card>());
        Rocket =new Card("Rocket","rocket","rockets",2,6,new ArrayList<Card>(Arrays.asList(Rage,BabyDragon)),new ArrayList<Card>());
        RoyalGiant = new Card("RoyalGiant","royalgiant","royalgiants",4,8,new ArrayList<Card>(Arrays.asList(SkeletonaArmy,Ballom)),new ArrayList<Card>());
        Skelet = new Card("Skeleton","skelet","skelets",1,1,new ArrayList<Card>(Arrays.asList(Minions,BabyDragon,Witch,Wizard)),new ArrayList<Card>());
        SmallMinions = new Card("SmallMinions","smallminion","smallminions",1,3,new ArrayList<Card>(Arrays.asList(BabyDragon,Musketeer,Witch,Wizard,Rage)),new ArrayList<Card>());

        cards.put(0, Pekka);
        cards.put(1, Golem);
        cards.put(2, Ballom);
        cards.put(3,Minions);
        cards.put(4,Zap);
        cards.put(5, Wizard);
        cards.put(6, MiniPekka);
        cards.put(7, Witch);
        cards.put(8, HogRider);
        cards.put(9,GiantSkeleton);
        cards.put(10,BabyDragon );
        cards.put(11,Giant);
        cards.put(12, Musketeer);
        cards.put(13,SkeletonaArmy );
        cards.put(14, Valkyrie);
        cards.put(15, Prince);
        cards.put(16, Rage);
        cards.put(17, Rocket);
        cards.put(18, RoyalGiant);
        cards.put(19, Skelet);
        cards.put(20, SmallMinions);


    }

    private void addtoStack(Stack<ImageView> st){

        image1 = (ImageView) findViewById(R.id.FirstView);
        image2 = (ImageView) findViewById(R.id.SecondView);
        image3 = (ImageView) findViewById(R.id.ThirdView);
        image4 = (ImageView) findViewById(R.id.FouthView);
        image5 = (ImageView) findViewById(R.id.FifthView);
        image6 = (ImageView) findViewById(R.id.SixViev);
        image7 = (ImageView) findViewById(R.id.SevensView);
        image8 = (ImageView) findViewById(R.id.EightView);

        st.push(image8);
        st.push(image7);
        st.push(image6);
        st.push(image5);
        st.push(image4);
        st.push(image3);
        st.push(image2);
        st.push(image1);

    }

    private boolean isUsed(int n,ArrayList<Card> usedcards){

        for (Card c: usedcards) {
            if(cards.get(n).name.equals(c.name)) return true;
        }
        return false;
    }

    private String avaregeElixir(ArrayList<Card> used){

        NumberFormat formater = new DecimalFormat("#0.0");

        double sum = 0;
        for (Card c: used) {
            sum+=c.elixircost;
        }
        //but.setText(sum + "");

        return formater.format(sum/used.size());
    }
}
