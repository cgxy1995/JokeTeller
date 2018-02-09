package com.kamijou.services;

import org.springframework.http.HttpHeaders;

/**
 * Created by njiang on 2/9/2018.
 */
public interface HttpService {
    HttpHeaders createHeaders(String username, String password);
}
