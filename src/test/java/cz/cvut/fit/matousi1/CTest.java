package cz.cvut.fit.matousi1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CTest {



    @BeforeAll
    static void setup(){
        System.out.println("===TESTEST===");
    }


    @Test
    void equalInt() {
        assertEquals("HELLO GRAxcDLE","HELLO GRADLE");
    }
}