package com.ynwa.kdl.hosein.exrecyclerexpandable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    List<Carpet> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setData();

        RcvExpandableAdapter adapter = new RcvExpandableAdapter(items);
        recyclerView.setAdapter(adapter);
    }

    private void setData() {

            items.add(new Carpet("title 1", null, null, false));
            items.add(new Carpet("title 2", "a ",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSvRta1vGH7E7IQGzlqzn-R0ojwB_Gr5mN0RQlP0D8u_gcNBdck",
                    true));
            items.add(new Carpet("title 3", "b",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQlZCxE4o2ulVYNFSIyRHxS9Cm4gZcHv856clgbJYh7K4j2xBbB",
                    true));
    }
}
