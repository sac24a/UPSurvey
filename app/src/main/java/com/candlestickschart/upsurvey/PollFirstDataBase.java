package com.candlestickschart.upsurvey;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PollFirstData.class, SocialData.class,SocialMediaData.class,ReligionData.class,PoliticalWorkerData.class,ImpPersonData.class, EmployeeData.class,BoothAgent.class}, exportSchema = false, version = 1)
public abstract class PollFirstDataBase extends RoomDatabase {
    private static final String DB_Name = "upsurvey_db";
    private static PollFirstDataBase instance;
    public static synchronized PollFirstDataBase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), PollFirstDataBase.class,DB_Name)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    public abstract PollFirstDao pollFirstDao();
}
