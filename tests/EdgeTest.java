package com.cs308gui.CS308AssignmentBoston;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Edge Class Tests")
class EdgeTest {

    @Test
    @DisplayName("Constructor Test")
    void constTest() {
        Edge e1 = new Edge(1, 2, "green");
        Edge e2 = new Edge(21, 3644, " ");
        Edge e3 = new Edge(-23, 12451, "21312txc b zx dS");
        assertEquals(1, e1.getSrc());
        assertEquals(2, e1.getDest());
        assertEquals("green", e1.getRoute());
        assertEquals(21, e2.getSrc());
        assertEquals(3644, e2.getDest());
        assertEquals(" ", e2.getRoute());
        assertEquals(-23, e3.getSrc());
        assertEquals(12451, e3.getDest());
        assertEquals("21312txc b zx dS", e3.getRoute());
    }

    @Nested
    @DisplayName("isEqual() Tests")
    class equalityTests{

        private Edge e1 = new Edge(1, 2, "green");
        private Edge e2 = new Edge(5423, 12252346, "sadfgsgdgsdg");
        private Edge e3 = new Edge(-112321321, -2123221321, "greeasfsdfn");
        private Edge e4 = new Edge(1, 2, "green");
        private Edge e5 = new Edge(5423, 12252346, "sadfgsgdgsdg");
        private Edge e6 = new Edge(-112321321, -2123221321, "greeasfsdfn");

        @Test
        @DisplayName("Test 1: comparing edges with themselves")
        void equalityTest1() {
            assertTrue(e1.isEqual(e1));
            assertTrue(e2.isEqual(e2));
            assertTrue(e3.isEqual(e3));
        }

        @Test
        @DisplayName("Test 2: comparing edges with same attributes")
        void equalityTest2() {
            assertTrue(e1.isEqual(e4));
            assertTrue(e2.isEqual(e5));
            assertTrue(e3.isEqual(e6));
        }

        @Test
        @DisplayName("Test 3: comparing unequal edges with themselves")
        void equalityTest3() {
            assertFalse(e1.isEqual(e2));
            assertFalse(e1.isEqual(e3));
            assertFalse(e1.isEqual(e5));
            assertFalse(e1.isEqual(e6));
            assertFalse(e2.isEqual(e1));
            assertFalse(e2.isEqual(e3));
            assertFalse(e2.isEqual(e4));
            assertFalse(e2.isEqual(e6));
            assertFalse(e3.isEqual(e1));
            assertFalse(e3.isEqual(e2));
            assertFalse(e3.isEqual(e4));
            assertFalse(e3.isEqual(e5));
        }

    }

}