package com.mituuz.package2;

import com.mituuz.testfixture.SharedTestClass;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExampleClass2Test {
    @Test
    void exampleTest() {
        ExampleClass2 mock = mock(ExampleClass2.class);
        when(mock.getName()).thenReturn("MockedExampleClass2");

        // Test sources in the same subproject have access to test fixtures by default
        SharedTestClass sharedTestClass = new SharedTestClass();
        sharedTestClass.sayHello();

    }
}
