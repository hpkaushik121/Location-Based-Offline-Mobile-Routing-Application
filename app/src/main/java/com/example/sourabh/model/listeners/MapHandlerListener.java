package com.example.sourabh.model.listeners;

import org.oscim.core.GeoPoint;

/**
 * This file is
 * Created by Sourabh kaushik on March 14, 2018.
 */
public interface MapHandlerListener {
    /**
     * when use press on the screen to get a location form map
     *
     * @param latLong
     */
    void onPressLocation(GeoPoint latLong);

    /**
     * calculate path calculating (running) true NOT running or finished false
     *
     * @param shortestPathRunning
     */
    void pathCalculating(boolean shortestPathRunning);
}
