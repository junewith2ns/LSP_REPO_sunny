package org.howard.edu.lsp.midterm.question4;

public interface BatteryPowered {
    int getBatteryPercent();               // 0..100
    void setBatteryPercent(int percent);   // throw if outside 0..100
}
