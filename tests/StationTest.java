package com.cs308gui.CS308AssignmentBoston;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Station Class Tests")
class StationTest {

    private List<List<Node>> rl1,rl2,rl3;
    private Station s1,s2,s3;

    @BeforeEach
    void setUp(){

        rl1 = new ArrayList<>();
        rl2 = new ArrayList<>();
        rl3 = new ArrayList<>();
    }

    @Test
    @DisplayName("Constructor Test")
    public void constTest() {
        s1 = new Station(1, "station 1",rl1);
        s2 = new Station(-22, "sbdsg23t34", rl2);
        s3 = new Station(2356, "13@#$%^$#@^%@&a asd#^$%", rl3);
        assertEquals(1, s1.getId());
        assertEquals("station 1", s1.getName());
        assertEquals(rl1, s1.getRoute_list());
        assertEquals(-22, s2.getId());
        assertEquals("sbdsg23t34", s2.getName());
        assertEquals(rl2, s2.getRoute_list());
        assertEquals(2356, s3.getId());
        assertEquals("13@#$%^$#@^%@&a asd#^$%", s3.getName());
        assertEquals(rl3, s3.getRoute_list());
    }

    @Nested
    @DisplayName("SetName() & Setid() Tests")
    class setTests {

        ArrayList<Node> nametoid = new ArrayList<Node>();;
        Node n1 = new Node(1,"station 1");
        Node n2 = new Node(-22,"sbdsg23t34");
        Node n3 = new Node(2356,"13@#$%^$#@^%@&a asd#^$%");

        @BeforeEach
        void setup(){
            s1 = new Station(1, "asdsa 1", rl1);
            s2 = new Station(-22, "asd", rl2);
            s3 = new Station(2356, "13@#$asdasd", rl3);
            nametoid.add(0, n1);
            nametoid.add(1, n2);
            nametoid.add(2, n3);
        }


        @Test
        @DisplayName("SetName() Test")
        public void SetNameTest() {

            s1.SetName(1, nametoid);
            s2.SetName(-22, nametoid);
            s3.SetName(2356, nametoid);
//            assertEquals("station 1", s1.getName());
            assertEquals("sbdsg23t34", s2.getName());
            assertEquals("13@#$%^$#@^%@&a asd#^$%", s3.getName());
        }

        @Test
        @DisplayName("Setid() Test")
        public void SetidTest() {
            s1.Setid("green", nametoid);
            s2.Setid("sadfgsgdgsdg", nametoid);
            s3.Setid("greeasfsdfn", nametoid);
            assertEquals(1, s1.getId());
            assertEquals(-22, s2.getId());
            assertEquals(2356, s3.getId());
        }

    }

}