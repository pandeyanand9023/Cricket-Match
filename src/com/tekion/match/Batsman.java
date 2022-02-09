package com.tekion.match;
import com.tekion.match.Player;
public class Batsman extends Player{
private int runs;
private int out;
public float averageRuns()
{
    return (float)(runs/out);
}

}
