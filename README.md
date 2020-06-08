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

### Log

**AS 4.0 build (not crash)**
```
2020-06-08 21:46:01.193 3085-3085/com.example.desugerbug E/MainActivity: Parse error
    java.time.format.DateTimeParseException: Text '' could not be parsed at index 0
        at java.time.format.DateTimeFormatter.parseResolved0(DateTimeFormatter.java:1949)
        at java.time.format.DateTimeFormatter.parse(DateTimeFormatter.java:1851)
        at java.time.LocalDate.parse(LocalDate.java:394)
        at java.time.LocalDate.parse(LocalDate.java:379)
        at com.example.desugerbug.MainActivity.onCreate(:15)
        at android.app.Activity.performCreate(Activity.java:7136)
        at android.app.Activity.performCreate(Activity.java:7127)
        at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1271)
        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2893)
        at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3048)
        at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:78)
        at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:108)
        at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:68)
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1808)
        at android.os.Handler.dispatchMessage(Handler.java:106)
        at android.os.Looper.loop(Looper.java:193)
        at android.app.ActivityThread.main(ActivityThread.java:6669)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:493)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:858)
```

**gradle command build (crash)**
```
2020-06-08 21:51:36.224 3466-3466/com.example.desugerbug E/AndroidRuntime: FATAL EXCEPTION: main
    Process: com.example.desugerbug, PID: 3466
    java.lang.RuntimeException: Unable to start activity ComponentInfo{com.example.desugerbug/com.example.desugerbug.MainActivity}: j$.time.s.x: Text '' could not be parsed at index 0
        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2913)
        at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3048)
        at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:78)
        at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:108)
        at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:68)
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1808)
        at android.os.Handler.dispatchMessage(Handler.java:106)
        at android.os.Looper.loop(Looper.java:193)
        at android.app.ActivityThread.main(ActivityThread.java:6669)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:493)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:858)
     Caused by: j$.time.s.x: Text '' could not be parsed at index 0
        at j$.time.s.d.k(:1949)
        at j$.time.s.d.j(:1851)
        at j$.time.LocalDate.L(:400)
        at j$.time.LocalDate.parse(:385)
        at com.example.desugerbug.MainActivity.onCreate(:15)
        at android.app.Activity.performCreate(Activity.java:7136)
        at android.app.Activity.performCreate(Activity.java:7127)
        at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1271)
        at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2893)
        at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3048) 
        at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:78) 
        at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:108) 
        at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:68) 
        at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1808) 
        at android.os.Handler.dispatchMessage(Handler.java:106) 
        at android.os.Looper.loop(Looper.java:193) 
        at android.app.ActivityThread.main(ActivityThread.java:6669) 
        at java.lang.reflect.Method.invoke(Native Method) 
        at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:493) 
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:858) 
```
