package com.ma.modernmotivealarm;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView fadeit;
   ImageView fadde;
    private RecyclerView mrecu;
  int count=1;
    private exampleadapter madapter;
    private RecyclerView.LayoutManager mlayoutmanager;
    ArrayList<example_item> examplelist;
AdView mAdView;
    SharedPreferences adt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        MobileAds.initialize(this,
                "ca-app-pub-2605690072930758~2468887768");

        mAdView = findViewById(R.id.adView1111);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        fadde=findViewById(R.id.tofadeimage);
        fadeit=findViewById(R.id.tofadeone);
         adt1 =getSharedPreferences("additional data",MODE_PRIVATE);
        loadData();
        edit_list_data();
         add_additional_data();
        saveData();
        loadData();

        fade();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent in= new Intent(MainActivity.this,setalarm.class);
                startActivity(in);






            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);




        mrecu= findViewById(R.id.recyclerview);

        mrecu.setHasFixedSize(true);
        mlayoutmanager =new LinearLayoutManager(this);
        madapter = new exampleadapter(examplelist);

        mrecu.setLayoutManager(mlayoutmanager);
        mrecu.setAdapter(madapter);
        madapter.setonitemclickedlistner(new exampleadapter.OnitemClickListner() {
            @Override
            public void onItemClick(int position) {
                ///////perform item click operation here//////



                int mode = examplelist.get(position).gettext2();
                if(mode==0)
                {
                Intent in =new Intent(MainActivity.this,showmyalarmoffline.class);
                in.putExtra("name",examplelist.get(position).gettext1());
                in.putExtra("hour",examplelist.get(position).gethour());
                in.putExtra("minute",examplelist.get(position).getminute());
                in.putExtra("position",position);
                in.putExtra("videopath",examplelist.get(position).getPath_offline());
                startActivity(in);}
                else
                {
                    Intent in =new Intent(MainActivity.this,showmealarmonline.class);
                    in.putExtra("name",examplelist.get(position).gettext1());
                    in.putExtra("hour",examplelist.get(position).gethour());
                    in.putExtra("minute",examplelist.get(position).getminute());
                    in.putExtra("position",position);
                    in.putExtra("videopath",examplelist.get(position).getPath_online());
                    startActivity(in);
                }


            }

            @Override
            public void ondeleteclick(int position) {

                examplelist.remove(position);
                madapter.notifyItemRemoved(position);
                fade();
            }


        });



    }


    public void edit_list_data()
    {


        SharedPreferences hg =getSharedPreferences("edit_alarm",MODE_PRIVATE);
           int flag = hg.getInt("edit_alarm",0);
           if(flag==1) {
               SharedPreferences.Editor ed = adt1.edit();
               ed.putInt("additional_data", 1);
               ed.apply();
               int id = getIntent().getIntExtra("id", 0);
               examplelist.remove(id);

               SharedPreferences.Editor dfg = hg.edit();
               dfg.putInt("edit_alarm",0);
               dfg.commit();
           }

    }
     public void add_additional_data()
     {

         if(adt1.getInt("additional_data",0)!=0)

         {

             examplelist.add(new example_item(getIntent().getIntExtra("hour_add",0),
              getIntent().getIntExtra("minute_add",0)
             , getIntent().getStringExtra("name_add"),getIntent().getIntExtra("mode_add",1),examplelist.size(),
                getIntent().getStringExtra("path_online_add"),getIntent().getStringExtra("path_offline_add"),0));

             SharedPreferences.Editor ed = adt1.edit();
             ed.putInt("additional_data",0);
             ed.apply();
         }


     }
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(examplelist);
        editor.putString("task list", json);
        editor.apply();
    }
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<example_item>>() {}.getType();
        examplelist = gson.fromJson(json, type);

        if (examplelist == null) {
            examplelist = new ArrayList<>();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {  saveData();
        if(count==2)
        {   Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }
        else
        {
            count++;
            Toast.makeText(MainActivity.this,"Press again to exit",Toast.LENGTH_LONG).show();
        }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       int id = item.getItemId();


        if (id == R.id.action_settings) {

                   Intent inte = new Intent(this,settings.class);
                   startActivity(inte);
                   return true;
        }else
        if (id == R.id.share_this) {

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Try MOTIVE ALARM! a new way of waking. Wake up with favorite motivational video along with a math challenge. Download the app now :- https://play.google.com/store/apps/details?id=com.ma.modernmotivealarm");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent inte= new Intent(this,Main2Activity.class);
            startActivity(inte);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
           Toast.makeText(MainActivity.this,"Directing you to goole play.",Toast.LENGTH_LONG).show();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/developer?id=ABHISHEK+AMRUTE"));
            startActivity(browserIntent);
        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(MainActivity.this,video_tutorial.class));
        } else if (id == R.id.nav_tools) {

            startActivity(new Intent(MainActivity.this,settings.class));
        }  else if (id == R.id.nav_send) {
            startActivity(new Intent(MainActivity.this,contactus.class));
        }else if (id == R.id.share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Try MOTIVE ALARM! a new way of waking. Wake up with favorite motivational video along with a math challenge. Download the app now :- https://play.google.com/store/apps/details?id=com.ma.modernmotivealarm");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        else if (id == R.id.exit) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain); }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onRestart() {
        saveData();
        super.onRestart();
    }

    @Override
    protected void onPause() {
        saveData();
        super.onPause();
    }

    @Override
    protected void onStop() {
        saveData();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        saveData();
        super.onDestroy();
    }
    public void fade()
    {
        if(examplelist.size()!=0)
        {
            fadeit.setVisibility(View.INVISIBLE);
            fadde.setVisibility(View.INVISIBLE);
            fadeit.setVisibility(View.GONE);
            fadde.setVisibility(View.GONE);
        }
        else
        {
            fadeit.setVisibility(View.VISIBLE);
            fadde.setVisibility(View.VISIBLE);

        }
    }
}
