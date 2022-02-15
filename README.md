### Prerequisites
<ul>
        <li>JDK 8</li>
        <li>Gradle 7+</li>
        <li>IntelliJ (Recommended)</li>
  </ul>



### Test running
<ul>

  <li>run all tests

  ```
  $./gradlew clean test
  ```

  </li>

  <li>run smoke tests

  ```
  $gradle smokeTest
  ```
  </li>

  </ul>

### Multiple browser support
In <b>config.properties</b> file you can choose the browser for test run. By changing <i>"UITesting/src/test/resources/config.properties"</i> file <b>BROWSER_NAME</b> variable to "CHROME", "FIREFOX" or "SAFARI" you can enable tests to be run in the choosen browser.

### Extent test report
Extent test report can be found after UI test execution in "test-output" folder. I didn't have time to implement it for the API tests.