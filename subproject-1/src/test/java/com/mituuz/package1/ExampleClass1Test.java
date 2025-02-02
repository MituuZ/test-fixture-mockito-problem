package com.mituuz.package1;

import com.mituuz.package2.ExampleClass2;
import com.mituuz.testfixture.SharedTestClass;
import org.junit.jupiter.api.Test;

public class ExampleClass1Test {
    @Test
    void exampleTest() {
        // In another package the test fixture must be imported to be used
        // @see subproject-1/build.gradle
        // Because the test fixture has access to the main code, it can be used in this subproject too

        // Mock is not a dependency of the test fixture, so it is required as a separate dependency in the build file
        ExampleClass2 exampleClass2 = mock(ExampleClass2.class);
        when(mock.getName()).thenReturn("MockedExampleClass2");


        SharedTestClass sharedTestClass = new SharedTestClass();
        sharedTestClass.sayHello();
    }
}
