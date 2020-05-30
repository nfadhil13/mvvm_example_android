package com.fdev.mvvm_example;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        this.noteDao = database.noteDao();
        allNotes = noteDao.getAllNote();
    }

    public void insert(Note note){
        new AsyncTaskInsertData(noteDao).execute(note);
    }

    public void update(Note note){
        new AsyncTaskUpdateData(noteDao).execute(note);
    }

    public void delete(Note note){
        new AsyncTaskDeleteData(noteDao).execute(note);
    }

    public void deleteAllNotes(){
        new AsyncTaskDeleteAllData(noteDao).execute();

    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    private static class AsyncTaskInsertData extends AsyncTask<Note , Void ,Void>{
        private NoteDao noteDao;

        private AsyncTaskInsertData(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }


    private static class AsyncTaskDeleteData extends AsyncTask<Note , Void ,Void>{
        private NoteDao noteDao;

        private AsyncTaskDeleteData(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class AsyncTaskUpdateData extends AsyncTask<Note , Void ,Void>{
        private NoteDao noteDao;

        private AsyncTaskUpdateData(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class AsyncTaskDeleteAllData extends AsyncTask<Void , Void ,Void>{
        private NoteDao noteDao;

        private AsyncTaskDeleteAllData(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    }
}
