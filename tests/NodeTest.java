package com.cs308gui.CS308AssignmentBoston;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Node Class Tests")
class NodeTest {

    @Test
    @DisplayName("Constructor Test")
    void constTest() {
        Node n1 = new Node(2, "green");
        Node n2 = new Node(6435456, " ");
        Node n3 = new Node(-435456, "21312txc b zx dS ");
        assertEquals(2, n1.getDest());
        assertEquals("green", n1.getRoute());
        assertEquals(6435456, n2.getDest());
        assertEquals(" ", n2.getRoute());
        assertEquals(-435456, n3.getDest());
        assertEquals("21312txc b zx dS ", n3.getRoute());
    }

    @Nested
    @DisplayName("isEqual() Tests")
    class equalityTests{

        private Node n1 = new Node(1,"green");
        private Node n2 = new Node(5423,"sadfgsgdgsdg");
        private Node n3 = new Node(-112321321,"greeasfsdfn");
        private Node n4 = new Node(1,"green");
        private Node n5 = new Node(5423,"sadfgsgdgsdg");
        private Node n6 = new Node(-112321321,"greeasfsdfn");

        @Test
        @DisplayName("Test 1: comparing nodes with themselves")
        void equalityTest1() {
            assertTrue(n1.isEqual(n1));
            assertTrue(n2.isEqual(n2));
            assertTrue(n3.isEqual(n3));
        }

        @Test
        @DisplayName("Test 2: comparing nodes with same attributes")
        void equalityTest2() {
            assertTrue(n1.isEqual(n4));
            assertTrue(n2.isEqual(n5));
            assertTrue(n3.isEqual(n6));
        }

        @Test
        @DisplayName("Test 3: comparing unequal nodes with themselves")
        void equalityTest3() {
            assertFalse(n1.isEqual(n2));
            assertFalse(n1.isEqual(n3));
            assertFalse(n1.isEqual(n5));
            assertFalse(n1.isEqual(n6));
            assertFalse(n2.isEqual(n1));
            assertFalse(n2.isEqual(n3));
            assertFalse(n2.isEqual(n4));
            assertFalse(n2.isEqual(n6));
            assertFalse(n3.isEqual(n1));
            assertFalse(n3.isEqual(n2));
            assertFalse(n3.isEqual(n4));
            assertFalse(n3.isEqual(n5));
        }

    }

}
