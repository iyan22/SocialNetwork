package packSocialNetworkGraph;

import packSocialNetwork.Person;
import packSocialNetworkExceptions.RelationAlreadyAtSocialNetwork;

import java.util.*;

/**
 * Graph for storing friendships.
 */
public class FriendshipGraph {

    private Map<Vertex, List<Vertex>> adjVertices;

    //For longest Path

    public Stack<Vertex> longestPath( Person p1, Person p2){
        Stack<Vertex> path = new Stack<>();
        Set<Vertex> onPath = new LinkedHashSet<>();
        Stack<Vertex> maxStack;
        Stack<Vertex> finalStack = new Stack();
       maxStack= enumerate(p1, p2, path, onPath, new Stack<Vertex>());

        while(!maxStack.isEmpty()){

            finalStack.push(maxStack.pop());

        }

        return finalStack;
    }

    private Stack<Vertex> enumerate(Person p1, Person p2, Stack<Vertex> path, Set<Vertex> onPath, Stack<Vertex> maxStack){
        Vertex v = new Vertex(p1);

        path.push(v);
        onPath.add(v);

        if (v.getP().equals(p2)){
            if(maxStack.size()<path.size()){
                maxStack= (Stack<Vertex>) path.clone();
            }
            onPath.remove(v);
            path.pop();


        }
        else {
            for (Vertex k : getAdjList(v.getP())) {
                if(!onPath.contains(k)) {
                    maxStack=enumerate(k.getP(), p2, path, onPath, maxStack);
                }
            }
            path.pop();
            onPath.remove(v);
        }

        return maxStack;

    }





    /**
     * Constructor. Makes a HashMap of Vertexes(Person) and asociates a List of Vertexes(Person).
     */
    public FriendshipGraph(){

        this.adjVertices = new HashMap<>();

    }

    /**
     * Getter for the friend of a person.
     * @param p1 person
     * @return list of friends (vertexes)
     */
    public List<Vertex> getAdjList(Person p1){
        return this.adjVertices.get(new Vertex(p1));
    }

    /**
     * AddEdge method.
     * @param p1 person
     * @param p2 person
     */
    public void addFriendship(Person p1, Person p2){


        Vertex v1 = new Vertex(p1);
        Vertex v2 = new Vertex(p2);
        if (!adjVertices.get(v1).contains(v2)) {
            adjVertices.get(v1).add(v2);
            adjVertices.get(v2).add(v1);
        }
        else{
            throw new RelationAlreadyAtSocialNetwork();
        }

    }

    /**
     * AddVertex method
     * @param p person
     */
    public void addPerson(Person p){

        adjVertices.putIfAbsent(new Vertex(p), new ArrayList<>());
    }

    /**
     * RemoveVertex method
     * @param p person
     */
    public void removePerson(Person p){

        Vertex v = new Vertex(p);
        adjVertices.values().stream().forEach(e -> e.remove(v));
        adjVertices.remove(new Vertex(p));
    }

    /**
     * RemoveEdge method
     * @param p1 person
     * @param p2 person
     */
    public void removeFriendship(Person p1, Person p2){

        Vertex v1 = new Vertex(p1);
        Vertex v2 = new Vertex(p2);

        List<Vertex> eV1 = adjVertices.get(v1);
        List<Vertex> eV2 = adjVertices.get(v2);

        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV2.remove(v1);
    }

    /**
     * DFS
     * @param root person
     * @return Linked hash set of Vertexes in graph.
     */
    public Set<Vertex> DFS (Person root){

        Set<Vertex> visited = new LinkedHashSet<>();
        Stack<Vertex> stack = new Stack<>();

        stack.push(new Vertex(root));
        while(!stack.isEmpty()){
            Vertex vertex = stack.pop();
            if (!visited.contains(vertex)){
                visited.add(vertex);
                for (Vertex v : adjVertices.get(vertex)){
                    stack.push(v);


                }
            }
        }
        return visited;
    }

    /**
     * BFS
     * @param root root person
     * @return set of vertexes in BFS order
     */
    public Set<Vertex> BFS (Person root){
        Set<Vertex> visited = new LinkedHashSet<>();
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(new Vertex(root));
        visited.add(new Vertex(root));

        while(!queue.isEmpty()){
            Vertex vertex = queue.poll();
            for (Vertex v : adjVertices.get(vertex)) {
                if(!visited.contains(v)){
                    visited.add(v);
                    queue.add(v);

                }
            }
        }
        return visited;
    }

    /**
     * For reversing the shortesPath.
     * @param l shortest path.
     * @return any list in reversed order.
     */
    public List<Vertex> reverse(List<Vertex> l){

        List<Vertex> reverse = new LinkedList<>();
        for (int i = l.size()-1; i>-1; i--){

            reverse.add(l.remove(i));

        }

        return reverse;
    }

    /**
     * Method that returns the shortest path between two people, if they are friends.
     * @param destination Destination friend
     * @param source Source Friend
     * @return LinkedList representing the path betwen source and destination. If source and destination not connected:
     * it returns a message.
     */
    public List<Vertex> shortestPathBFS(Person destination, Person source){

        boolean stop = false;

        List<Vertex> shortestPath = new LinkedList<>();
        Set<Vertex> visited = new LinkedHashSet<>();
        Map<Vertex,Vertex> prev = new HashMap<>();
        Queue<Vertex> queue = new LinkedList<>();
        Vertex vertex = new Vertex(source);
        visited.add(vertex);
        queue.add(vertex);

        while(!queue.isEmpty()){
            vertex = queue.poll();
            if (vertex.equals(new Vertex(destination))){
                break;
            }
            else {
                for (Vertex v : adjVertices.get(vertex)) {
                    if (!visited.contains(v)) {

                        visited.add(v);
                        prev.put(v,vertex);
                        queue.add(v);

                    }
                }
            }
        }

        if(!vertex.equals(new Vertex(destination))){

            stop = true;

            System.out.println("Error. "+source.getIdentifier()+" and "+destination.getIdentifier()+" are not friends!");

        }
        if (!stop == true) {
            for (Vertex v = new Vertex(destination); v != null; v = prev.get(v)) {
                shortestPath.add(v);
                //hashmap.get() returns null if no entry for a element was made. In our case,
                // vertex source has no predecesor, and therefore we return null;
                //TODO: Maybe better to return a list of people or of people.identifier instead of vertex?
            }
        }
        else{

        }

        return shortestPath; //Or return reverse(shortestPath);

    }




}
