package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.google.android.material.tabs.TabLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private  Saints athletics;
    private ArrayList<Athletics> parseSaints = new ArrayList<>();
    private ProgressBar progressBar;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Here finding the stuff from XML
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        athletics = new Saints(parseSaints, this);
        recyclerView.setAdapter(athletics);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Men's Soccer"));
        tabLayout.addTab(tabLayout.newTab().setText("Women's Soccer"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // When a tab is selected, re-fetch the data
                Content content = new Content();
                content.execute();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });


        Content content = new Content();
        content.execute();
    }

    private class Content extends AsyncTask<Void,Void,Void>{
        @Override // I am not sure why there's a line on the OnPreExecute. Not sure if this will be showing
        //only on my computer
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in));

        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            progressBar.setVisibility(View.GONE);
            progressBar.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out));

            // Notify the adapter that the dataset has changed
            athletics.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                String url;
                // determines which tab is selected
                int selectedTabIndex = tabLayout.getSelectedTabPosition();
                if (selectedTabIndex == 0) { // Men's Soccer
                    url = "https://csssaints.com/sports/mens-soccer/roster"; //URL
                }
                else{
                   url = "https://csssaints.com/sports/womens-soccer/roster";
                }
                //JSOUP is used after injecting dependencies
                Document doc = Jsoup.connect(url).get();
                //Finding the elements I need
                Elements data = doc.select("li.sidearm-roster-player");
                parseSaints.clear();
                int size = data.size();
                for (int i = 0; i < size; i++) {
                    String imgUrl = data.get(i).select("div.sidearm-roster-player-image img").attr("data-src");
                    if (imgUrl.isEmpty()) {
                        imgUrl = data.get(i).select("div.sidearm-roster-player-image img").attr("src");
                    }

                    String baseUrl = "https://dxbhsrqyrr690.cloudfront.net/sidearm.nextgen.sites/csssaints.com";
                    String fullImgUrl = baseUrl + imgUrl;
                    //here, I was having a problem that the URL code changes when it runs on the internet and that's some security
                    //measures I believe taken by the school, but I found the method below that helped me fix it.
                    String encodedUrl = Uri.encode(fullImgUrl, "UTF-8");
                    String finalUrl = "https://images.sidearmdev.com/resize?url=" + encodedUrl + "&width=240&type=png&quality=100";

                    System.out.println("The Final URL is: "+finalUrl);

                    //Grabbing their name
                    String title = data.select("h3 a").eq(i).text();
                    //Grabbing their number
                    String number = data.get(i).select(".sidearm-roster-player-jersey-number").text();


                    System.out.println("The number is: "+number);// WE have one player that do not have a picture
                    if(title.equals("Akeem Robotham")){
                        System.out.println("passing");
                        finalUrl = "data:image/png;base64,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";

                    }
                    if(title.equals("Ella Backstrom")){
                        System.out.println("passing");
                        finalUrl = "data:image/png;base64,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";

                    }
                    //Adding the elements to the array and creating a new athletics object
                    parseSaints.add(new Athletics(finalUrl, title,number));
                    Log.d("items", "img: " + imgUrl + " Title: " + title);}

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }
}