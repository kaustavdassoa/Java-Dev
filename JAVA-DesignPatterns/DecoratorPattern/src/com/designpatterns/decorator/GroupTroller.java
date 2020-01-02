package com.designpatterns.decorator;

public class GroupTroller implements Troller {

    private Troller attacker;

    public GroupTroller(Troller attacker) {
        this.attacker = attacker;
    }

    @Override
    public int attack() {
        return this.attacker.attack()*2;
    }
}
