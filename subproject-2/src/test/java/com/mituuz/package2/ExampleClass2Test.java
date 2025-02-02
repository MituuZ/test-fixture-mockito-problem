package com.mituuz.package2;

import com.mituuz.testfixture.SharedTestClass;
import org.junit.jupiter.api.Test;

public class ExampleClass2Test {
    @Test
    void exampleTest() {
        // Test sources in the same subproject have access to test fixtures by default
        SharedTestClass sharedTestClass = new SharedTestClass();
        sharedTestClass.sayHello();
    }
}
