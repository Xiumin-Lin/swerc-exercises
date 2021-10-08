package queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

class QueueTest {
    private final InputStream STDIN = System.in;
    private final PrintStream STDOUT = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setIn(STDIN);
        System.setOut(STDOUT);
    }

    @Test
    void runAlgo() throws Exception {
        String inputData = "3 6 N N E 1 N N N 0 0";
        System.setIn(new ByteArrayInputStream(inputData.getBytes()));
        Queue.runAlgo();
        String anwser = "Case 1:\n1\n2\n1\n3\n2\n1";
        // replace all \n by the user's system line separator : "\n" or "\r\n"
        String expected = anwser.replaceAll("\n", System.getProperty("line.separator"));
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void runAlgoWithException() throws Exception {
        // 'Z' will throw an exception
        String inputData = "3 6 N Z E 1 N N N 0 0";
        System.setIn(new ByteArrayInputStream(inputData.getBytes()));
        // Queue::runAlgo is a method references for "() -> Queue.runAlgo()"
        Assertions.assertThrows(Exception.class, Queue::runAlgo);
    }
}