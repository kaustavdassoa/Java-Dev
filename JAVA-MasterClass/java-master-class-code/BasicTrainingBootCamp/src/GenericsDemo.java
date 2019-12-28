import java.util.ArrayList;

abstract class Player{
    private String name;

    Player(String name)
    {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class BaseballPlayer extends Player{
    public BaseballPlayer(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return super.getName()+" [BaseBall Player]";
    }
}

class FootBallPlayer extends Player{
    public FootBallPlayer(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return super.getName()+" [FootBall Player]";
    }
}

class Team <T extends Player> implements Comparable<Team <T>>{
    private String teamName;
    private ArrayList<T> PlayersList;

    public Team(String teamName) {
        this.teamName = teamName;
        PlayersList=new ArrayList<T>();
    }
    public void addPlayer(T player) {
        this.PlayersList.add(player);
    }

    @Override
    public int compareTo(Team<T> team) {
        if(this.PlayersList.size() > team.PlayersList.size())
        {
            return 1;
        }
        else if (this.PlayersList.size() < team.PlayersList.size())
        {
            return -1;
        }else
        {
            return 0;
        }
        }//compareTo
 }

public class GenericsDemo {

    public static void main(String[] args) {
        BaseballPlayer bob = new BaseballPlayer("bob");
        FootBallPlayer mike = new FootBallPlayer("mike");
        Team<FootBallPlayer> footBallTeam=new Team<>("Foot ball team");
        Team<FootBallPlayer> newfootBallTeam=new Team<>("New Foot ball team");
        Team<BaseballPlayer> baseballTeam=new Team<>("Basket ball team");
        footBallTeam.addPlayer(mike);
        //footBallTeam.addPlayer(bob); --> Thorws error as bob is of Type BaseballPlayer
        //footBallTeam.compareTo(baseballTeam) Thorws error as baseballTeam is of Type FootBallTeam
        System.out.println("footBallTeam Compare to newfootBallTeam ="+footBallTeam.compareTo(newfootBallTeam));
    }
}