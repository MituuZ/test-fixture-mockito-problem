package com.mituuz.testfixture;

import com.mituuz.package2.ExampleClass2;

public class SharedTestClass {
    // Test fixtures have access to the main sources in the same project by default
    private final ExampleClass2 exampleClass2;

    public SharedTestClass(ExampleClass2 exampleClass2) {
        this.exampleClass2 = exampleClass2;
    }

    public String sayHello() {
        return exampleClass2.getName();
    }
}
