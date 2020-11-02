package com.javamasterclass.trainingbootcamp.withoutgenericDemo;

public class TestClass {

    public static void main(String[] args) {
        BaseballPlayer joe=new BaseballPlayer("Joe");
        BasketballPlayer jhon=new BasketballPlayer("Jhon");

        Team baseBallTeam=new Team();
        Team basketballTeam=new Team();

        baseBallTeam.addMembers(joe);
        baseBallTeam.addMembers(jhon);

        basketballTeam.addMembers(joe);
        basketballTeam.addMembers(jhon);

        System.out.println("BaseBall Team "+baseBallTeam.getPlayers().toString());
        System.out.println("Basketball Team"+basketballTeam.getPlayers().toString());
    }



}
