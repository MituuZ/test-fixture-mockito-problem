# Stack
- Java 21
- Gradle 8.10
- Mockito 5.15.2

## Problem with mockito
When running tests with mockito, the following warning is displayed:
```
Mockito is currently self-attaching to enable the inline-mock-maker. This will no longer work in future releases of the JDK. Please add Mockito as an agent to your build as described in Mockito's documentation: https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#0.3
WARNING: A Java agent has been loaded dynamically (/home/mitja/.gradle/caches/modules-2/files-2.1/net.bytebuddy/byte-buddy-agent/1.15.11/a38b16385e867f59a641330f0362ebe742788ed8/byte-buddy-agent-1.15.11.jar)
WARNING: If a serviceability tool is in use, please run with -XX:+EnableDynamicAgentLoading to hide this warning
WARNING: If a serviceability tool is not in use, please run with -Djdk.instrument.traceUsage for more information
WARNING: Dynamic loading of agents will be disallowed by default in a future release
```

To fix the warning, you must create a [`gradle/libs.versions.toml`](gradle/libs.versions.toml) file as instructed in the link.

[https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#0.3](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#0.3)

And add the following lines to the `build.gradle` file:
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
