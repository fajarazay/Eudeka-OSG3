package com.fajarazay.github.bolaapp.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.fajarazay.github.bolaapp.model.TeamDetail;

/**
 * Created by Fajar Septian on 2019-02-27.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
@Database(entities = {TeamDetail.class}, version = 1)
public abstract class TeamDataBase extends RoomDatabase {
    private static final Object sLock = new Object();
    private static TeamDataBase INSTANCE;

    public static TeamDataBase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        TeamDataBase.class, "Team.db")
                        .build();
            }
            return INSTANCE;
        }
    }

    public abstract TeamDao teamDao();
}
