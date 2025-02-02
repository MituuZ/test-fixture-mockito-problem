package com.mituuz.package2;

import com.mituuz.testfixture.SharedTestClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExampleClass2Test {
    @Test
    void exampleTest() {
        ExampleClass2 exampleClass2Mock = mock(ExampleClass2.class);
        when(exampleClass2Mock.getName()).thenReturn("MockedExampleClass2");

        // Test sources in the same subproject have access to test fixtures by default
        SharedTestClass sharedTestClass = new SharedTestClass(exampleClass2Mock);
        assertEquals("MockedExampleClass2", sharedTestClass.sayHello());
    }
}
