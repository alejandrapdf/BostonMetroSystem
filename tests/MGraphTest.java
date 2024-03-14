package com.cs308gui.CS308AssignmentBoston;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MGraphTest {

    private MGraph graph;

    @BeforeEach
    void initiateReader() {
        graph = new MGraph();
        assertAll(() -> graph.Graph());
    }

    @Nested
    @DisplayName("Graph() Tests")
    class GraphTests {

        @BeforeEach
        //reinitiate reader so graph.Graph() is undone
        void reinitiateReader() {
            graph = new MGraph();
        }

        @Test
        @DisplayName("Test 1")
        void test1() {
            graph.getFilereader().setTxtSource("test1.txt");
            assertAll(() -> graph.Graph());
            List<List<Node>> route_list = graph.getRoute_list();
            List<List<Node>> test_list = new ArrayList<>(5);
            for( int i=0; i<5; i++){
                test_list.add(i, new ArrayList<>());
            }
            test_list.get(1).add(new Node(0,"Orange"));
            test_list.get(1).add(new Node(2,"Orange"));
            test_list.get(2).add(new Node(1,"Orange"));
            test_list.get(2).add(new Node(5,"Orange"));
            test_list.get(3).add(new Node(0,"Blue"));
            test_list.get(3).add(new Node(4,"Blue"));
            test_list.get(4).add(new Node(3,"Blue"));
            test_list.get(4).add(new Node(6,"Blue"));
            int i=0, j=0;
            for (List<Node> l : test_list) {
                for (Node n : l) {
                    assertTrue(n.isEqual(route_list.get(i).get(j)));
                    j++;
                }
                j=0;
                i++;
            }
        }

        @Test
        @DisplayName("Test 2")
        void test2() {
            graph.getFilereader().setTxtSource("test3.txt");
            assertAll(() -> graph.Graph());
            List<List<Node>> route_list = graph.getRoute_list();
            List<List<Node>> test_list = new ArrayList<>(5);
            for( int i=0; i<5; i++){
                test_list.add(i, new ArrayList<>());
            }
            test_list.get(1).add(new Node(1,"Red"));
            test_list.get(1).add(new Node(2,"Red"));
            test_list.get(2).add(new Node(2,"Green"));
            test_list.get(2).add(new Node(4,"Green"));
            test_list.get(2).add(new Node(2,"Orange"));
            test_list.get(2).add(new Node(3,"Orange"));
            test_list.get(3).add(new Node(2,"Red"));
            test_list.get(3).add(new Node(4,"Red"));
            test_list.get(4).add(new Node(3,"GreenB"));
            test_list.get(4).add(new Node(2,"GreenB"));
            test_list.get(4).add(new Node(3,"GreenC"));
            test_list.get(4).add(new Node(2,"GreenC"));
            test_list.get(4).add(new Node(3,"GreenD"));
            test_list.get(4).add(new Node(2,"GreenD"));
            test_list.get(4).add(new Node(3,"GreenE"));
            test_list.get(4).add(new Node(4,"GreenE"));
            int i=0, j=0;
            for (List<Node> l : test_list) {
                for (Node n : l) {
                    assertTrue(n.isEqual(route_list.get(i).get(j)));
                    j++;
                }
                j=0;
                i++;
            }
        }

    }

    @Nested
    @DisplayName("printPath() Tests")
    class printPathTests{

        List<Node> path = new ArrayList<>();

        @Test
        @DisplayName("94 to 32")
        void printPath1Test(){
            Node n1 = new Node(94,"Red");
            Node n2 = new Node(60,"Red");
            Node n3 = new Node(33,"Red");
            Node n4 = new Node(30,"Red");
            Node n5 = new Node(32,"Orange");
            path.add(n1);
            path.add(n2);
            path.add(n3);
            path.add(n4);
            path.add(n5);
            assertEquals("  => Andrew on line Red => Broadway  => SouthStation   => DowntownCrossing change to Orange line  => Chinatown",
                    graph.printPath(path, (MGraph) graph));
        }

        @Test
        @DisplayName("26 to 31")
        void path2Test(){
            Node n1 = new Node(26,"Blue");
            Node n2 = new Node(28,"Blue");
            Node n3 = new Node(30,"Red");
            Node n4 = new Node(29,"Red");
            Node n5 = new Node(31,"Green");
            path.add(n1);
            path.add(n2);
            path.add(n3);
            path.add(n4);
            path.add(n5);
            assertEquals("  => Aquarium on line Blue  => State change to Red line => DowntownCrossing   => ParkStreet change to Green line  => Boylston",
                    graph.printPath(path, (MGraph) graph));
        }

    }

    @Nested
    @DisplayName("finalPath() Tests")
    class finalPathTests{

        ArrayList<List<Node>> recPath;

        @Test
        @DisplayName("30 to 57")
        void path1Test(){
            recPath = graph.finalPath(30, 57,(MGraph) graph);
            Node n1 = new Node(30, "startroute");
            Node n2 = new Node(29, "Red");
            Node n3 = new Node(31, "Green");
            Node n4 = new Node(34, "Green");
            Node n5 = new Node(41, "Green");
            Node n6 = new Node(51, "GreenB");
            Node n7 = new Node(47, "GreenB");
            Node n8 = new Node(57, "GreenD");
            assertTrue(n1.isEqual(recPath.get(0).get(0)));
            assertTrue(n2.isEqual(recPath.get(0).get(1)));
            assertTrue(n3.isEqual(recPath.get(0).get(2)));
            assertTrue(n4.isEqual(recPath.get(0).get(3)));
            assertTrue(n5.isEqual(recPath.get(0).get(4)));
            assertTrue(n6.isEqual(recPath.get(0).get(5)));
            assertTrue(n7.isEqual(recPath.get(0).get(6)));
            assertTrue(n8.isEqual(recPath.get(0).get(7)));
        }

        @Test
        @DisplayName("30 to 17")
        void path2Test() {
            recPath = graph.finalPath(30, 17, (MGraph) graph);
            Node n1 = new Node(30, "startroute");
            Node n2 = new Node(28, "Orange");
            Node n3 = new Node(22, "Orange");
            Node n4 = new Node(20, "Orange");
            Node n5 = new Node(19, "Green");
            Node n6 = new Node(17, "Green");
            assertTrue(n1.isEqual(recPath.get(0).get(0)));
            assertTrue(n2.isEqual(recPath.get(0).get(1)));
            assertTrue(n3.isEqual(recPath.get(0).get(2)));
            assertTrue(n4.isEqual(recPath.get(0).get(3)));
            assertTrue(n5.isEqual(recPath.get(0).get(4)));
            assertTrue(n6.isEqual(recPath.get(0).get(5)));
        }

        @Test
        @DisplayName("28 to 25")
        void path3Test() {
            recPath = graph.finalPath(28, 25, (MGraph) graph);
            Node n1 = new Node(28, "startroute");
            Node n2 = new Node(30, "Orange");
            Node n3 = new Node(29, "Red");
            Node n4 = new Node(25, "Red");
            assertTrue(n1.isEqual(recPath.get(0).get(0)));
            assertTrue(n2.isEqual(recPath.get(0).get(1)));
            assertTrue(n3.isEqual(recPath.get(0).get(2)));
            assertTrue(n4.isEqual(recPath.get(0).get(3)));
        }

    }

    @Nested
    @DisplayName("getIDTests() Tests")
    class getIDTests{

        @Test
        @DisplayName("St. Paul 1")
        void station1Test() {
            assertEquals(38, graph.getID("St.PaulStreetEast"));
        }

        @Test
        @DisplayName("St. Paul 2")
        void station2Test() {
            assertEquals(61, graph.getID("St.PaulStreetWest"));
        }

        @Test
        @DisplayName("St. Mary's")
        void station3Test() {
            assertEquals(54, graph.getID("St.Mary'sStreet"));
        }

        @Test
        @DisplayName("HeathStreet")
        void station4Test() {
            assertEquals(96,  graph.getID("HeathStreet"));
        }

    }

    @Nested
    @DisplayName("getNameTests() Tests")
    class getNameTests{

        @Test
        @DisplayName("St. Paul 1")
        void station1Test() {
            assertEquals("St.PaulStreet", graph.getName(38));
        }

        @Test
        @DisplayName("St. Paul 2")
        void station2Test() {
            assertEquals("St.PaulStreet", graph.getName(61));
        }

        @Test
        @DisplayName("St. Mary's")
        void station3Test() {
            assertEquals("St.Mary'sStreet", graph.getName(54));
        }

        @Test
        @DisplayName("HeathStreet")
        void station4Test() {
            assertEquals("HeathStreet",  graph.getName(96));
        }

    }

    @Nested
    @DisplayName("getTimeTests() Tests")
    class getTimeTests{

        @Test
        @DisplayName("30 to 57")
        void path1Test() {
            graph.finalPath(30, 57, (MGraph) graph);
            assertEquals(17, graph.getTime());

        }

        @Test
        @DisplayName("30 to 17")
        void path2Test() {
            graph.finalPath(30, 17, (MGraph) graph);
            assertEquals(12, graph.getTime());
        }

        @Test
        @DisplayName("28 to 25")
        void path3Test() {
            graph.finalPath(28, 25, (MGraph) graph);
            assertEquals(6, graph.getTime());
        }

    }

}