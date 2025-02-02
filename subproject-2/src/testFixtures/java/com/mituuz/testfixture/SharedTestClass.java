package com.mituuz.testfixture;

import com.mituuz.package2.ExampleClass2;

public class SharedTestClass {
    // Test fixtures have access to the main sources in the same project by default
    private ExampleClass2 exampleClass2 = new ExampleClass2();

    public void sayHello() {
        System.out.println(exampleClass2.getClass().getSimpleName());
    }
}
