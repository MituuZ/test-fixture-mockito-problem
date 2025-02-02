package com.mituuz.package1;

import com.mituuz.package2.ExampleClass2;
import com.mituuz.testfixture.SharedTestClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExampleClass1Test {
    @Test
    void exampleTest() {
        // In another package the test fixture must be imported to be used
        // @see subproject-1/build.gradle
        // Because the test fixture has access to its project's main, it can be used in this subproject too (ExampleClass2)

        // Mock is not a dependency of the test fixture, so it is required as a separate dependency in the build file
        ExampleClass2 exampleClass2Mock = mock(ExampleClass2.class);
        when(exampleClass2Mock.getName()).thenReturn("MockedExampleClass2");

        SharedTestClass sharedTestClass = new SharedTestClass(exampleClass2Mock);
        assertEquals("MockedExampleClass2", sharedTestClass.sayHello());
    }
}
