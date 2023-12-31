import static org.junit.Assert.*;

import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> studentDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solutionDeque = new ArrayDequeSolution<>();

        String message = "\n";
        Integer stdRm = -1;
        Integer solRm = -1;

        for (int i = 0; i < 1000; ++i) {
            /* Generates a random integer in [0, 1000). */
            int randomNum = StdRandom.uniform(1000);

            /*  Randomly call { StudentArrayDeque } and { ArrayDequeSolution } methods. */
            int caseOfMethods = StdRandom.uniform(4);

            switch (caseOfMethods) {
                /* { addFirst } case */
                case 0:
                    message += "addFirst(" + randomNum + ")\n";
                    studentDeque.addFirst(randomNum);
                    solutionDeque.addFirst(randomNum);
                    break;
                /* { addLast } case */
                case 1:
                    message += "addLast(" + randomNum + ")\n";
                    studentDeque.addLast(randomNum);
                    solutionDeque.addLast(randomNum);
                    break;
                /* { removeFirst } case */
                case 2:
                    if (!studentDeque.isEmpty() && !solutionDeque.isEmpty()) {
                        message += "removeFirst()\n";
                        stdRm = studentDeque.removeFirst();
                        solRm = solutionDeque.removeFirst();
                        break;
                    }
                    /* { removeLast } case */
                case 3:
                    if (!studentDeque.isEmpty() && !solutionDeque.isEmpty()) {
                        message += "removeLast()\n";
                        stdRm = studentDeque.removeLast();
                        solRm = solutionDeque.removeLast();
                    }
                    break;
                default:
            }

            /* Check out whether the */
            assertEquals(message, solRm, stdRm);
        }
    }
}
