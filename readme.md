# Stack
- Java 21
- Gradle 8.11.1
- Mockito 5.15.2

## Motivation for this example
I tried to replicate this issue that I had with a similar setup using mockito and test fixtures.

I had a multi-module project in which I added the `java-test-fixtures` plugin and was greeted with this error: 
(manually written and modified to match this example project)
```
Could not determine the dependencies of task ':subproject-2:test'.
> Could not resolve all dependencies for configuration ':subproject-2:testRuntimeClasspath'.
    > Could not resolve project :subproject-2
        Required by:
            project :subproject-2
            > Unable to find a variant providing the requested capability 'test-fixture-mockito-problem:subproject-2-test-fixtures':
                - Variant 'apiElements' provides 'test-fixture-mockito-problem:subproject-2:unspecified'
                - Variant 'coverageDataElementsForTest' provides 'test-fixture-mockito-problem:subproject-2:unspecified'
                - Variant 'mainSourceElements' provides 'test-fixture-mockito-problem:subproject-2:unspecified'
                - Variant 'runtimeElements' provides 'test-fixture-mockito-problem:subproject-2:unspecified'
                - Variant 'testFixturesApiElements' provides 'com.mituuz:subproject-2-test-fixtures:1.0-SNAPSHOT'
                - Variant 'testFixturesRuntimeElements' provides 'com.mituuz:subproject-2-test-fixtures:1.0-SNAPSHOT'
                - Variant 'testResultsElementsForTest' provides 'test-fixture-mockito-problem:subproject-2:unspecified'
```

It seems that there was something more, 
because I was not able to replicate the issue by creating a new project with the same setup.
Hopefully, this example will help someone else with the same issue, even if it does not replicate the exact problem.

### Solution
I was able to solve the issue by removing the following line from the main [build.gradle](build.gradle) file:
```groovy
jvmArgs += "-javaagent:${configurations.mockitoAgent.asPath}"
```

By adding this to the subprojects, and after the `java-test-fixtures` plugin declaration, the issue was resolved.


## Problem with mockito
Adding the following dependency to the `build.gradle` file causes a warning when running tests:
```groovy
dependencies {
    testImplementation group: 'org.mockito', name: 'mockito-core', version: '5.15.2'
}
```

The warning looks like this:
```
Mockito is currently self-attaching to enable the inline-mock-maker. This will no longer work in future releases of the JDK. Please add Mockito as an agent to your build as described in Mockito's documentation: https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#0.3
WARNING: A Java agent has been loaded dynamically (/home/mitja/.gradle/caches/modules-2/files-2.1/net.bytebuddy/byte-buddy-agent/1.15.11/a38b16385e867f59a641330f0362ebe742788ed8/byte-buddy-agent-1.15.11.jar)
WARNING: If a serviceability tool is in use, please run with -XX:+EnableDynamicAgentLoading to hide this warning
WARNING: If a serviceability tool is not in use, please run with -Djdk.instrument.traceUsage for more information
WARNING: Dynamic loading of agents will be disallowed by default in a future release
```

To fix the warning, you must create a [gradle/libs.versions.toml](gradle/libs.versions.toml) file as instructed in the [link](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#0.3).

And add the following lines to the `build.gradle` file: (the order of the lines does matter)
```groovy
configurations {
    mockitoAgent
}

dependencies {
    mockitoAgent(libs.mockito) {
        transitive = false
    }
}

tasks {
    test {
        jvmArgs += "-javaagent:${configurations.mockitoAgent.asPath}"
    }
}
```

To avoid doing this for each project, you might want to do it only in the root project as shown in this example.

- [gradle/libs.versions.toml](gradle/libs.versions.toml)
- [build.gradle](build.gradle)

By doing this, you must only add the following dependency to the subprojects:
```groovy
dependencies {
    testImplementation libs.mockito
}
```
