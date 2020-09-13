package com.sabkayar.praveen.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sabkayar.praveen.recyclerview.databinding.WordlistItemBinding;

import java.util.LinkedList;

class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordListViewHolder> {
    private LinkedList<String> mWordList;

    public void setWordList(LinkedList<String> wordList) {
        mWordList = wordList;
        notifyDataSetChanged();
    }

    private LayoutInflater mLayoutInflater;


    public WordListAdapter(Context context, LinkedList<String> wordList) {
        mLayoutInflater = LayoutInflater.from(context);
        mWordList = wordList;
    }

    @NonNull
    @Override
    public WordListAdapter.WordListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordListViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordListViewHolder holder, int position) {
        holder.bind(mWordList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mWordList != null) {
            return mWordList.size();
        }
        return 0;
    }

    class WordListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private WordListAdapter mAdapter;
        private WordlistItemBinding mBinding;
        public WordListViewHolder(@NonNull View itemView, WordListAdapter adapter) {
            super(itemView);
            mBinding=WordlistItemBinding.bind(itemView);
            mAdapter = adapter;
            mBinding.word.setOnClickListener(this);
        }

        public void bind(String word) {
            mBinding.word.setText(word);
        }

        @Override
        public void onClick(View v) {
            int currentPosition = getLayoutPosition();
            mWordList.set(currentPosition, String.format("%s %s", "Clicked", mWordList.get(currentPosition)));
            mAdapter.notifyDataSetChanged();
        }
    }
}
