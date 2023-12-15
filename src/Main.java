public class Main {
    public static void main(String[] args) {
        State initialState = new State(3, 3, true);
        State goalState = new State(0, 0, false);
        Solver.BFSolver(initialState, goalState);
        System.out.println("");
        Solver.DFSolver(initialState, goalState);
    }
}