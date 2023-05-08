package com.travelblog.repository;

import com.travelblog.http.*;

import java.util.*;

public interface DataFromDatabaseCallback {
    void onSuccess(List<Blog> blogList);
}
