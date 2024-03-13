import java.util.*;

class Ticket{
    String destination;
    boolean visited;
    
    public Ticket(String destination, boolean visited){
        this.destination = destination;
        this.visited = visited;
    }
}
class Solution {
    List<String> list = new ArrayList<>();
    boolean finished = false;
    public String[] solution(String[][] tickets) {
        Map<String, List<Ticket>> map = new HashMap<>();
        
        for (String[] ticket : tickets){
            String depature = ticket[0];
            String destination = ticket[1];
            
            List<Ticket> nodes = map.getOrDefault(depature, new ArrayList<>());
            
            nodes.add(new Ticket(destination, false));
            
            map.put(depature, nodes);
        }
        
        for (String key : map.keySet()){
            Collections.sort(map.get(key), new Comparator<Ticket>(){
                
                @Override
                public int compare(Ticket t1, Ticket t2){
                    return t1.destination.compareTo(t2.destination);
                }
            });
        }
        
        DFS("ICN", "ICN", map, tickets.length, 0);
        
        String[] answer = list.get(0).split(" ");
        
        

        
        return answer;
    }
    
    public void DFS(String depature, String path, Map<String, List<Ticket>> map, int limit, int depth){       
        if (depth == limit){
            list.add(path);
            return;
        }
        
        for (Ticket ticket : map.getOrDefault(depature, new ArrayList<>())){
            if (ticket.visited) continue;
            
            ticket.visited = true;
            
            DFS(ticket.destination, path + " " + ticket.destination, map, limit, depth + 1);
            
            ticket.visited = false;
           }
    }
}