# deephaven-server-customization

This is a work-in-progress that shows how one may customize the [Deephaven Server](https://github.com/deephaven/deephaven-core).

It is aimed at enterprises and advanced users who are familiar with Java.

## Local development

```shell
./gradlew server:run -Pgroovy
```

## Build

```shell
./gradlew server:build
```

This produces `server/build/distributions/server.tar` / `server/build/distributions/server.zip`.
