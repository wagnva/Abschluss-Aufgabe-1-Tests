#### Public Tests for "Abschluss Aufgabe 1"

##### Installation
Add the following libraries (using gradle for example)

    testCompile "org.mockito:mockito-core:2.4.0"
    testCompile group: 'org.powermock', name: 'powermock-api-mockito2', version: '2.0.0-beta.5'
    testCompile group: 'org.powermock', name: 'powermock-module-junit4', version: '2.0.0-beta.5'
    testCompile "com.google.truth:truth:0.39"
    
Clone this Repository into your project repo and run the tests using gradle

### Important!
* If your Main class is not called Main, you need to change the name in `java/base/Start.java$game`
* I (foolishly) thought that everything passed to Terminal.printLine is a String,
  so if you printed objects, the tests will fail. Just add a .toString() behind the object in the code and it works
  Replace `Terminal.printLine(object)` to `Terminal.printLine(object.toString())`
#### I cannot guarantee that the tests are fully correct.  