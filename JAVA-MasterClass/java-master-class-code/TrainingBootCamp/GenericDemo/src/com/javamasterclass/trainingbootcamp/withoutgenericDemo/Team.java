package com.javamasterclass.trainingbootcamp.withoutgenericDemo;

import java.util.ArrayList;

public class Team {

    private ArrayList players;

    public Team() {
        this.players = new ArrayList();
    }

    public boolean addMembers(Player member)
    {
        this.players.add(member);
        return true;
    }

    public ArrayList getPlayers() {
        return players;
    }
}
