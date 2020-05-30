package com.fdev.mvvm_example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{
    private List<Note> notes = new ArrayList<>();


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currentNote = notes.get(position);

        holder.mTextViewPriority.setText(String.valueOf(currentNote.getPriority()));
        holder.mTextViewTitle.setText(currentNote.getTitle());
        holder.mTextViewDesc.setText(currentNote.getDescription());


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNote(int position){
        return notes.get(position);
    }

    class NoteViewHolder extends  RecyclerView.ViewHolder{

        private TextView mTextViewTitle;
        private TextView mTextViewDesc;
        private TextView mTextViewPriority;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewDesc = itemView.findViewById(R.id.tv_description);
            mTextViewTitle = itemView.findViewById(R.id.tv_title);
            mTextViewPriority = itemView.findViewById(R.id.tv_priority);
        }
    }
}

