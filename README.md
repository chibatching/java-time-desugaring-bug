# java-time-desugaring-bug

## Bug description

Desugaring `java.time` future with minify and command line build causes missing class of `DateTimeParseException`.  
This does not occur when building with Android Studio 4.0, but only when using the gradle command.

### Test pattern with this code

||Build with AS 4.0|Build with gradle command|
|:--:|:--:|:--:|
|minify disabled|:o:|:o:|
|minify enabled|:o:|:x:|

### gradle command build

```
./gradlew app:assembleDebug
```
