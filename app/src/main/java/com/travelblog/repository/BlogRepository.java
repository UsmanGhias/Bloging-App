package com.travelblog.repository;

import android.content.*;

import com.travelblog.database.*;
import com.travelblog.http.*;

import java.util.*;
import java.util.concurrent.*;

public class BlogRepository {

    private BlogHttpClient httpClient;
    private AppDatabase database;
    private Executor executor;

    public BlogRepository(Context context) {
        httpClient = BlogHttpClient.INSTANCE;
        database = DatabaseProvider.getInstance(context.getApplicationContext());
        executor = Executors.newSingleThreadExecutor();
    }

    public void loadDataFromDatabase(DataFromDatabaseCallback callback) {
        executor.execute(() -> callback.onSuccess(database.blogDao().getAll()));

    }

    public void loadDataFromNetwork(DataFromNetworkCallback callback) {
        executor.execute(() -> {
            List<Blog> blogList = httpClient.loadBlogArticles();
            if (blogList == null) {
                callback.onError();
            } else {
                BlogDAO blogDAO = database.blogDao();
                blogDAO.deleteAll();
                blogDAO.insertAll(blogList);
                callback.onSuccess(blogList);
            }
        });
    }
}
