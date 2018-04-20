package com.example.sourabh.model.listeners;

import android.location.Address;

/**
 * This file is
 * Created by Sourabh kaushik on March 14, 2018.
 */
public interface OnClickAddressListener {
    /**
     * tell Activity what to do when address is clicked
     *
     * @param view
     */
    void onClick(Address addr);
}
