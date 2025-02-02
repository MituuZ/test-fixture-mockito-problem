package com.mituuz.testfixture;

import com.mituuz.package2.ExampleClass2;

public class SharedTestClass {
    public void sayHello() {
        // Test fixtures have access to the main sources in the same project by default
        ExampleClass2 exampleClass2 = new ExampleClass2();

        System.out.println(exampleClass2.getName());
    }
}
