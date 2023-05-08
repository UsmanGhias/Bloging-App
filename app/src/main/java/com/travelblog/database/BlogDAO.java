package com.travelblog.database;

import androidx.room.*;

import com.travelblog.http.*;

import java.util.*;

@Dao
public interface BlogDAO {

    @Query("SELECT * FROM blog")
    List<Blog> getAll();

    @Insert
    void insertAll(List<Blog> blogList);

    @Query("DELETE FROM blog")
    void deleteAll();
}
