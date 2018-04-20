package com.example.sourabh.model.listeners;

/**
 * This file is
 * Created by Sourabh kaushik on March 14, 2018.
 */
public interface MapDownloadListener {
    /**
     * a download is started
     */
    void downloadStart();

    /**
     * a download activity is finished
     */
    void downloadFinished(String mapName);
    
    void onStartUnpacking();

    void progressUpdate(Integer value);
}
