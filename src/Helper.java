import java.util.Map;
import java.util.Set;

public class Helper
{
    public static void dfsHelper(State current, State goal, Set<State> visited, Map<State, State> parentMap) {
        if (current.equals(goal)) {
            Solver.printSolution(parentMap, current);
            System.exit(0); // Exit the program after finding the solution
        }

        visited.add(current);

        for (State next : Solver.getValidMoves(current)) {
            if (!visited.contains(next)) {
                parentMap.put(next, current);
                dfsHelper(next, goal, visited, parentMap);
            }
        }
    }
}
