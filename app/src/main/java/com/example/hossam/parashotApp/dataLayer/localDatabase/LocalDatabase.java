package com.example.hossam.parashotApp.dataLayer.localDatabase;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.hossam.parashotApp.dataLayer.localDatabase.homePage.dao.BodyDao;
import com.example.hossam.parashotApp.dataLayer.localDatabase.homePage.dao.DesignDao;
import com.example.hossam.parashotApp.dataLayer.localDatabase.homePage.dao.FooterDao;
import com.example.hossam.parashotApp.dataLayer.localDatabase.homePage.dao.HeaderDao;
import com.example.hossam.parashotApp.dataLayer.localDatabase.homePage.dao.StoreDao;
import com.example.hossam.parashotApp.dataLayer.localDatabase.homePage.entities.BodyTable;
import com.example.hossam.parashotApp.dataLayer.localDatabase.homePage.entities.DesignTable;
import com.example.hossam.parashotApp.dataLayer.localDatabase.homePage.entities.FooterEntity;
import com.example.hossam.parashotApp.dataLayer.localDatabase.homePage.entities.HeaderTable;
import com.example.hossam.parashotApp.dataLayer.localDatabase.homePage.entities.StoreTable;

@Database(entities = {BodyTable.class, DesignTable.class, FooterEntity.class, HeaderTable.class, StoreTable.class},version = 1,exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase{

    public abstract BodyDao bodyDao ();
    public abstract DesignDao designDao ();
    public abstract FooterDao footerDao ();
    public abstract StoreDao storeDao ();
    public abstract HeaderDao headerDao ();


    public static LocalDatabase instance;

    public static LocalDatabase getInstance (Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),LocalDatabase.class,
                    "store_database").addCallback(roomcallback)
            .fallbackToDestructiveMigration().build();
        }

        return instance;
    }

    public static RoomDatabase.Callback roomcallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

           new  populateAsynctask(instance).execute();
        }
    };


    public static class populateAsynctask extends AsyncTask<Void,Void,Void>
    {

        private HeaderDao headerDao;

        public populateAsynctask(LocalDatabase db) {
            this.headerDao = db.headerDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            headerDao.insertHeader(new HeaderTable("","http://parashote.codesroots.com/library/default/1538818661177352465.png"
                    ,"http://parashote.codesroots.com/library/default/1538818661177352465.png"));
            return null;
        }
    }
}
