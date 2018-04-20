package com.example.sourabh.model.listeners;

/**
 * This file is
 * Created by Sourabh kaushik on March 14, 2018.
 */
public interface NavigatorListener {
    /**
     * the change on navigator: navigation is used or not
     *
     * @param on
     */
    void onStatusChanged(boolean on);
    
    void onNaviStart(boolean on);
}
