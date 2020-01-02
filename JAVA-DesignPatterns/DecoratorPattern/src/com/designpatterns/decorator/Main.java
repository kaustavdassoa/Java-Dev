package com.designpatterns.decorator;

public class Main {

    public static void main(String[] args) {
        Troller singleAttacker = new SingleTroller();
        Troller groupAttacker=new GroupTroller(singleAttacker);
        Troller massAttacker=new GroupTroller(groupAttacker);
        Troller mobAttacker=new GroupTroller(massAttacker);
        System.out.println("Attack Impact :"+singleAttacker.attack());
        System.out.println("Group attacker Impact :"+groupAttacker.attack());
        System.out.println("Mass attacker Impact :"+massAttacker.attack());
        System.out.println("Mob attacker Impact :"+mobAttacker.attack());
    }
}
