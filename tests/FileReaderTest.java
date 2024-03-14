package com.cs308gui.CS308AssignmentBoston;

import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("fileReader Class Tests")
class FileReaderTest {

    private FileReader reader;

    @BeforeEach
    void initiateReader() {
        reader = new FileReader();
    }

    @Nested
    @DisplayName("readFile() Tests")
    class readFileTests{

        @Test
        @DisplayName("Read Wrong/Non-existent File")
        void wrongFile() {
            reader.setTxtSource("asdsa.txt");
            assertThrows(FileNotFoundException.class, () -> reader.readFile() );
        }

        @Test
        @DisplayName("Read Empty File")
        void emptyFile() {
            reader.setTxtSource("empty.txt");
            assertAll(() -> reader.readFile());
            assertTrue(reader.getReadList().isEmpty());
        }

        @Test
        @DisplayName("Read out of order/corrupt File")
        void outOfOrder() {
            reader.setTxtSource("out of order.txt");
            List<Edge> testList = new ArrayList<Edge>();
            assertTrue(reader.getReadList().isEmpty());
            assertEquals(testList, reader.getReadList());
        }


        @Test
        @DisplayName("Read Correctly Formatted file (throws no errors)")
        void readFileNoError() {
            assertAll(() -> reader.readFile());
        }

        @Test
        @DisplayName("Correctly Formatted file Gives Correct Output")
        void readFileCorrectly1()  {
            reader.setTxtSource("test1.txt");
            assertAll(() -> reader.readFile());
            List<Edge> getList = reader.getReadList();
            List<Edge> testList = new ArrayList<Edge>();
            Edge e1 = new Edge(1,0, "Orange");
            Edge e2 = new Edge(1,2, "Orange");
            Edge e3 = new Edge(2,1, "Orange");
            Edge e4 = new Edge(2,5, "Orange");
            Edge e5 = new Edge(3,0, "Blue");
            Edge e6 = new Edge(3,4, "Blue");
            Edge e7 = new Edge(4,3, "Blue");
            Edge e8 = new Edge(4,6, "Blue");
            testList.add(e1);
            testList.add(e2);
            testList.add(e3);
            testList.add(e4);
            testList.add(e5);
            testList.add(e6);
            testList.add(e7);
            testList.add(e8);
            for (int i=0; i<testList.size(); i++) {
                Edge temp1 = testList.get(i);
                Edge temp2 = getList.get(i);
                assertEquals(testList.get(i).getSrc(), getList.get(i).getSrc() );
                assertEquals(testList.get(i).getDest(), getList.get(i).getDest() );
                assertEquals(testList.get(i).getRoute(), getList.get(i).getRoute() );
                assertTrue(temp1.isEqual(temp2));
            }
        }

        @Test
        @DisplayName("Correctly Formatted file Gives Correct Output")
        void readFileCorrectly2()  {
            reader.setTxtSource("test2.txt");
            assertAll(() -> reader.readFile());
            List<Edge> getList = reader.getReadList();
            List<Edge> testList = new ArrayList<Edge>();
            Edge e1 = new Edge(21,14, "Red");
            Edge e2 = new Edge(21,23, "Red");
            Edge e3 = new Edge(22,20, "Green");
            Edge e4 = new Edge(22,27, "Green");
            Edge e5 = new Edge(22,20, "Orange");
            Edge e6 = new Edge(22,28, "Orange");
            Edge e7 = new Edge(23,21, "Red");
            Edge e8 = new Edge(23,25, "Red");
            Edge e9 = new Edge(41,51, "GreenB");
            Edge e10 = new Edge(41,34, "GreenB");
            Edge e11 = new Edge(41,51, "GreenC");
            Edge e12 = new Edge(41,34, "GreenC");
            Edge e13 = new Edge(41,41, "GreenD");
            Edge e14 = new Edge(41,34, "GreenD");
            Edge e15 = new Edge(41,53, "GreenE");
            Edge e16 = new Edge(41,34, "GreenE");
            testList.add(e1);
            testList.add(e2);
            testList.add(e3);
            testList.add(e4);
            testList.add(e5);
            testList.add(e6);
            testList.add(e7);
            testList.add(e8);
            for (int i=0; i<testList.size(); i++) {
                Edge temp1 = testList.get(i);
                Edge temp2 = getList.get(i);
                assertEquals(testList.get(i).getSrc(), getList.get(i).getSrc() );
                assertEquals(testList.get(i).getDest(), getList.get(i).getDest() );
                assertEquals(testList.get(i).getRoute(), getList.get(i).getRoute() );
                assertTrue(temp1.isEqual(temp2));
            }
        }

    }

}
