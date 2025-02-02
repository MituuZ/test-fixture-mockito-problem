package com.mituuz.package1;

import com.mituuz.testfixture.SharedTestClass;
import org.junit.jupiter.api.Test;

public class ExampleClass1Test {
    @Test
    void exampleTest() {
        // In another package the test fixture must be imported to be used
        // @see subproject-1/build.gradle
        SharedTestClass sharedTestClass = new SharedTestClass();
        sharedTestClass.sayHello();
    }
}
