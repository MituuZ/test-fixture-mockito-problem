# Stack
- Java 21
- Gradle 8.11.1
- Mockito 5.15.2

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
