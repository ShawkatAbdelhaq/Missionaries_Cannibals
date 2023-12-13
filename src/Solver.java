import java.util.*;

public class Solver {

    //BFS Method
    public static void BFSolver(State start, State goal) {
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        Map<State, State> parentMap = new HashMap<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (current.equals(goal)) {
                printSolution(parentMap, current);
                return;
            }

            for (State next : getValidMoves(current)) {
                if (!visited.contains(next)) {
                    queue.add(next);
                    visited.add(next);
                    parentMap.put(next, current);
                }
            }
        }
    }

    //DFS Method
    public static void DFSolver(State start, State goal) {
        Set<State> visited = new HashSet<>();
        Map<State, State> parentMap = new HashMap<>();

        Helper.dfsHelper(start, goal, visited, parentMap);

    }

    public static List<State> getValidMoves(State current) {
        List<State> validMoves = new ArrayList<>();

        int mL = current.missionaries;
        int cL = current.cannibals;

        // اذا كان القارب على اليسار
        if (current.boat) {
            addValidMove(validMoves, new State(mL - 1, cL, false));
            addValidMove(validMoves, new State(mL, cL - 1, false));
            addValidMove(validMoves, new State(mL - 1, cL - 1, false));
            addValidMove(validMoves, new State(mL - 2, cL, false));
            addValidMove(validMoves, new State(mL, cL - 2, false));
            //اذا كان القارب على اليمين
        } else {
            addValidMove(validMoves, new State(mL + 1, cL, true));
            addValidMove(validMoves, new State(mL, cL + 1, true));
            addValidMove(validMoves, new State(mL + 1, cL + 1, true));
            addValidMove(validMoves, new State(mL + 2, cL, true));
            addValidMove(validMoves, new State(mL, cL + 2, true));
        }

        return validMoves;
    }


    private static void addValidMove(List<State> moves, State nextState) {
        if (isValid(nextState)) {
            moves.add(nextState);
        }
    }

    private static boolean isValid(State state) {
        int mL = state.missionaries;
        int cL = state.cannibals;

        return (mL >= 0 && mL <= 3 && cL >= 0 && cL <= 3 &&
                (mL == 0 || mL >= cL) && (3 - mL == 0 || (3 - mL) >= (3 - cL)));
    }

    public static void printSolution(Map<State, State> parentMap, State current) {
        List<State> path = new ArrayList<>();

        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }

        Collections.reverse(path);

        System.out.println(path.size() + " steps:");

        for (int i = 0; i < path.size(); i++) {
            State state = path.get(i);
            System.out.println("Step " + i + ": (" +
                    state.missionaries + ", " + state.cannibals + ", " +
                    (state.boat ? "1" : "0") + ", " +
                    (3 - state.missionaries) + ", " + (3 - state.cannibals) + ")");
        }
    }
}
