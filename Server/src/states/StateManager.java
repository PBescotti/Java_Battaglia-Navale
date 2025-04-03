package states;

//  Gestisce i vari stati che può assumere il programma
public class StateManager {
    
    private static State currentState = null;

    public static State getState() {
        return currentState;
    }

    public static void setState(State state) {
        currentState = state;
    }

}
