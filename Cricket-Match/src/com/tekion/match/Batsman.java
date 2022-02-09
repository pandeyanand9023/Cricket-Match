package com.tekion.match;
import com.tekion.match.Player;
public class Batsman extends Player{
private int runs=1000;
private int out=1;
public float averageRuns()
{
    return (float)(runs/out);
}

}
