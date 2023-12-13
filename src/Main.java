public class Main {
    public static void main(String[] args) {
        State initialState = new State(3, 3, true);
        State goalState = new State(0, 0, false);

        System.out.println("Solution using BFS");
        Solver.BFSolver(initialState, goalState);

        System.out.println("\nSolution using DFS");
        Solver.DFSolver(initialState, goalState);
    }
}