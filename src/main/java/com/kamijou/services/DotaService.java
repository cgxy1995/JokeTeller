package com.kamijou.services;

import com.kamijou.entities.DotaMatch;

/**
 * Created by njiang on 3/16/2018.
 */
public interface DotaService {
    public DotaMatch getDotaMatch(String gameId);
}
