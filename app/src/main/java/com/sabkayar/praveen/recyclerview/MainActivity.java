package com.sabkayar.praveen.recyclerview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sabkayar.praveen.recyclerview.databinding.ActivityMainBinding;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements WordListAdapter.OnClickListener {
    private WordListAdapter mWordListAdapter;
    private final LinkedList<String> mWordList = new LinkedList<>();
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wordListSize = mWordList.size();
                mWordList.addLast("+ Word " + wordListSize);
                mWordListAdapter.setWordList(mWordList);
                mBinding.layoutContentMain.recyclerView.smoothScrollToPosition(wordListSize);
            }
        });


        // Put initial data into the word list.
        for (int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + i);
        }

        mWordListAdapter = new WordListAdapter(this, mWordList);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mBinding.layoutContentMain.recyclerView.setLayoutManager(layoutManager);
        mBinding.layoutContentMain.recyclerView.setAdapter(mWordListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            mWordList.clear();
            // Put initial data into the word list.
            for (int i = 0; i < 20; i++) {
                mWordList.addLast("Word " + i);
            }
            mWordListAdapter.setWordList(mWordList);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int itemPosition = mBinding.layoutContentMain.recyclerView.getChildAdapterPosition(view);
        String item = mWordList.get(itemPosition);
        mWordList.set(itemPosition, String.format("%s %s", "Clicked", item));
        mWordListAdapter.setWordList(mWordList);
    }
}