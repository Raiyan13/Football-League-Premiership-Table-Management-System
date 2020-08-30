package FootballLeaguePremiershipTableManagementSystem;
import java.util.ArrayList;

public class Table {
    
      public ArrayList<Entry> table = new ArrayList<Entry>(8);
      public int nTeams = 8;
      public Table()
      {
        String teams[] = {"t1", "t2", "t3", "t4", "t5", "t6", "t7", "t8"};
        int wins[] = {3, 3, 2, 1, 1, 1, 1, 0};
        int losses[] = {0, 0, 1, 2, 2, 2, 2, 3};
        int drawn[] = {0, 0, 0, 0, 0, 0, 0, 0};
        int scoreFor[] = {149, 127, 105, 90, 85, 60, 50, 81};
        int scoreAgainst[] = {50, 59, 65, 104, 118, 107, 150, 94};
        
        for(int i=0;i<nTeams;i++)
        {
           Entry x = new Entry();
           x.setPosition(i+1);
           x.setTeams(teams[i]);    
           x.setPlayed(wins[i],losses[i],drawn[i]);
           x.setPoints(wins[i],drawn[i]);
           x.setWon(wins[i]);
           x.setLost(losses[i]);
           x.setDrawn(drawn[i]);
           x.setScoreFor(scoreFor[i]);
           x.setScoreAgainst(scoreAgainst[i]);
           x.processResult(scoreFor[i],scoreAgainst[i]);
           table.add(x);
      }
             
     }
        
      public ArrayList<Entry>getAllEntries()
      {
          return this.table;
      
      }
      public Entry lookupTeam(String t)
      {
          int idx = teamNumber(t);
          Entry now = new Entry();
          now = this.table.get(idx);
          return now;
      }
      public ArrayList<Entry>findTeamsOnSamePoints(String t)
      {   
          ArrayList<Entry>teams = new ArrayList<Entry>();
          int idx = teamNumber(t);
          Entry x = new Entry();
          x = this.table.get(idx);
          int points = x.getPoints();
          for(Entry it:this.table)
          {
             if(it.getPoints() == points)
                 teams.add(it);
          }
          return teams;
      }
      
      public int highestScoreFor()
      {
          int max_point = 0;
          String s = null;
          for(Entry it:this.table)
          {
             if(it.getScoreFor()>max_point)
             {
                s = it.getTeams();
                max_point = it.getScoreFor();
             }
          }
          return this.teamNumber(s);
      }
      
      public int lowestScoreAgainst()
      {
          int min_point = Integer.MAX_VALUE;
          String s = null;
          for(Entry it:this.table)
          {
             if(it.getScoreAgainst()<min_point)
             {
                s = it.getTeams();
                min_point = it.getScoreAgainst();
             }
          }
          return this.teamNumber(s);
      }
      
      public int findAvarageScoreFor()
      {
         int sum = 0;
          for(Entry it:this.table)
          {
            sum+=it.getScoreFor();
          }
          return (sum/nTeams);
      }
      
      public int teamNumber(String t)
      {
         if(t.equals("t1"))
             return 0;
         else if(t.equals("t2"))
             return 1;
         else if(t.equals("t3"))
             return 2;
         else if(t.equals("t4"))
             return 3;
         else if(t.equals("t5"))
             return 4;
        else if(t.equals("t6"))
             return 5;
         else if(t.equals("t7"))
             return 6;
         else if(t.equals("t8"))
             return 7;
         return -1;
      }
      
      public ArrayList<Entry> sortByPoints(ArrayList<Entry>table)
    {
        for (int i = 0; i < nTeams; i++)
        {
            int min_idx = i;
            for (int j = i+1; j < nTeams; j++)
            {  
                Entry x = new Entry();
                
               if(table.get(j).getPoints() > table.get(min_idx).getPoints())
                    min_idx = j;
            
            }
            Entry temp = table.get(min_idx);
            table.set(min_idx, table.get(i));
            table.set(i, temp);
        }
        return table;
    }
    
    public ArrayList<Entry> sortByPercentage(ArrayList<Entry>table)
    {
        for (int i = 0; i < nTeams; i++)
        {
           
            int min_idx = i;
            for (int j = i+1; j < nTeams; j++)
            {  
               Entry x = new Entry();
               if(table.get(j).getPercentage() > table.get(min_idx).getPercentage())
                    min_idx = j;
            }
            Entry temp = table.get(min_idx);
            table.set(min_idx, table.get(i));
            table.set(i, temp);
        }
        return table;
    }
      
      public void processGame(String t1,int g1,int b1,String t2,int g2,int b2)
      {
           int team1 = this.teamNumber(t1);
           Entry x = new Entry();
           x = this.table.get(team1);
           x.setScoreFor((g1*6)+b1);
           x.setScoreAgainst((g2*6)+b2);
           x.processResult(x.getScoreFor(),x.getScoreAgainst());
           Entry y = new Entry();
           int team2 = this.teamNumber(t2);
           y = this.table.get(team2);
           y.setScoreFor((g2*6)+b2);
           y.setScoreAgainst((g1*6)+b1);  
           y.processResult(y.getScoreFor(),y.getScoreAgainst()); 
           int win1 = x.getWon();
           int win2 = y.getWon();
           int loss1 = x.getLost();
           int loss2 = y.getLost();
           int draw1 = x.getDrawn();
           int draw2 = y.getDrawn();
           if(g1>g2)
           {
              win1+=1;
              loss2+=1;
           }
           else if(g1==g2)
           {
             draw1+=1;
             draw2+=1;
           }
           else
           {
              win2+=1;
              loss1+=1;
           }
           x.setWon(win1);
           y.setWon(win2);
           x.setLost(loss1);
           y.setLost(loss2);
           x.setDrawn(draw1);
           y.setDrawn(draw2);
           x.setPlayed(win1, loss1, draw1);
           y.setPlayed(win2, loss2, draw2);
           x.setPoints(win1, draw1);
           y.setPoints(win2, draw2);
           
           table.set(team2, y);
           table.set(team1, x);
           
           this.table = sortByPercentage(table);
           this.table = sortByPoints(table);
           int idx = 1;
           for(Entry it:this.table)
           {
             it.setPosition(idx);
             idx++;
           }
      }
}
