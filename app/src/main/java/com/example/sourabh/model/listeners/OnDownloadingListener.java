package com.example.sourabh.model.listeners;

import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * This file is
 * Created by Sourabh kaushik on March 14, 2018.
 */
public interface OnDownloadingListener {
    void progressbarReady(TextView downloadStatus, ProgressBar progressBar, int pos);
}
