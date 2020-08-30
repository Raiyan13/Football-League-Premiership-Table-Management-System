
package FootballLeaguePremiershipTableManagementSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import static javafx.application.Platform.exit;

public class Controller {
    Scanner in = new Scanner(System.in);
    Table table = new Table(); 
    
    public Controller() {
       
    }
    public Controller(Table t){
    
    }

   
    public void commandLoop()
    {
        while(true)
        {
           int input = in.nextInt();
           switch(input)
           {
               case 0:
                   printCommands(); 
                   break;
               case 1:
                    this.displayCurrentTable();
                    break;
               case 2:
                   this.displaySelectedStatistics();
                   break;
               case 3:
                   this.lookupASpecifiedTeam();
                   break;
               case 4:
                   this.findTeamsWithSamePoints();
                   break;
               case 5:
                   this.scanTeams();
                   break;
               case 9:
                   return;
               default:
                   System.out.println("Invalid Input\n");
                   break;
             
           }
        }
    }
    
    private void printCommands()
    {
      System.out.println("Available commands are:");
      System.out.println("       help                                              0\n"+
                         "       display entries                                   1\n"+
                         "       display selected statistics                       2\n"+
                         "       lookup a specified team                           3 team\n"+
                         "       find teams with same points as a specified team   4 team\n"+
                         "       add a new result                                  5 team1 g1 b1 team2 g2 b2\n"+
                         "       quit                                              9");
    }
    private void scanTeams() {
       String team1 = in.next();
       int g1 = in.nextInt();
       int b1 = in.nextInt();
       
       String team2 = in.next();
       int g2 = in.nextInt();
       int b2 = in.nextInt();
       
       if(table.teamNumber(team1)==-1 || table.teamNumber(team2)==-1 )
            System.out.println("Invalid Input\n");
       else
           table.processGame(team1, g1, b1, team2, g2, b2);
       
    }
    
    private void displayCurrentTable()
    {
       ArrayList<Entry>teams = new ArrayList<Entry>(table.nTeams);
       teams = table.getAllEntries();
       System.out.println("POS       TEAM      PLAYED    WINS      LOSSES    DRAWN     SCORE FOR      SCORE AGAINST  POINTS    PERCENTAGE");
          for(Entry team:teams)
          {
           System.out.printf("%-10d%-10s%-10d%-10d%-10d%-10d%-15d%-15d%-10d%.1f\n",team.getPosition(),team.getTeams(),team.getPlayed(),team.getWon(),team.getLost(),team.getDrawn(),team.getScoreFor(),team.getScoreAgainst(),team.getPoints(),team.getPercentage());
          }
    }
    
    private void displaySelectedStatistics()
    {
        ArrayList<Entry>pr = new ArrayList<Entry>(table.nTeams);
        pr = table.getAllEntries();
        
        int highestScoreFor = table.highestScoreFor();
        int lowestScoreAgains = table.lowestScoreAgainst();
        int avarage = table.findAvarageScoreFor();
        
        System.out.println(pr.get(highestScoreFor).getTeams()+" "+pr.get(lowestScoreAgains).getTeams()+" "+avarage);
    }
    
    private void lookupASpecifiedTeam()
    {
       String t = in.next();
       if(table.teamNumber(t)==-1)
            System.out.println("Invalid Input\n");
       else
       {
          Entry team = new Entry();
          team = table.lookupTeam(t);
          System.out.println("Pos    Team   Played Points %      Won    Lost   Drawn  PF     PA");
          System.out.printf("%-7d%-7s%-7d%-7d%-7.1f%-7d%-7d%-7d%-7d%d\n",team.getPosition(),team.getTeams(),team.getPlayed(),team.getPoints(),team.getPercentage(),team.getWon(),team.getLost(),team.getDrawn(),team.getScoreFor(),team.getScoreAgainst());
       }
    }
    
    private void findTeamsWithSamePoints()
    {
        String t = in.next();
       if(table.teamNumber(t)==-1)
            System.out.println("Invalid Input\n");
       else
       {
          ArrayList<Entry>teams = new ArrayList<Entry>(table.nTeams);
          teams = table.findTeamsOnSamePoints(t);
          System.out.println("POS       TEAM      PLAYED    WINS      LOSSES    DRAWN     SCORE FOR      SCORE AGAINST  POINTS    PERCENTAGE");
          for(Entry team:teams)
          {
           System.out.printf("%-10d%-10s%-10d%-10d%-10d%-10d%-15d%-15d%-10d%.1f\n",team.getPosition(),team.getTeams(),team.getPlayed(),team.getWon(),team.getLost(),team.getDrawn(),team.getScoreFor(),team.getScoreAgainst(),team.getPoints(),team.getPercentage());
          }
       }
    }
}
