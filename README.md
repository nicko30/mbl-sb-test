# mbl-sb-test

## Overview
This project is an automated test suite for an Android application using Appium, Selenium, and Cucumber. 
It automates end-to-end scenarios for the APK located in `app/mda-1.0.13-15.apk`.

## Prerequisites
- **Java JDK** (17 or higher)
- **Android SDK** (with emulator or real device)
- **Appium** (server running at http://127.0.0.1:4723)
- - **Appium UIAutomator2 Driver** (required for Android automation)
- **Node.js** (for Appium installation)
- (Optional) **IntelliJ IDEA** for development

> **Note:** You do NOT need to install Gradle manually. The project includes the Gradle Wrapper (`gradlew`/`gradlew.bat`).
> **Note:** You must install the Appium UIAutomator2 driver. See below for instructions.

## Setup Instructions

1. **Install Java JDK**
   - Download and install from [Oracle](https://www.oracle.com/java/technologies/downloads/).
   - Set `JAVA_HOME` and ensure `java` is in your `PATH`.

2. **Install Android SDK**
   - Download and install [Android Studio](https://developer.android.com/studio) or [Command Line Tools](https://developer.android.com/studio#command-tools).
   - Set up an emulator (default: `emulator-5554`) or connect a real device.

3. **Install Node.js and Appium**
   - Download and install [Node.js](https://nodejs.org/).
   - Install Appium globally:
     ```sh
     npm install -g appium
     ```
   - Start Appium server:
     ```sh
     appium
     ```
   - Ensure Appium is running at `http://127.0.0.1:4723`.

4. **Install Appium UIAutomator2 Driver**
   - After installing Appium, run:
     ```sh
     appium driver install uiautomator2
     ```
   - This is required for Android automation with Appium.

5. **Clone the Repository**
   ```sh
   git clone <repo-url>
   cd mbl-sb-test
   ```

6. **Build the Project**
   ```sh
   ./gradlew build
   ```

7. **Run the Tests**
   - Make sure the emulator/device is running and Appium server is started.
   - Run:
     ```sh
     ./gradlew clean runTest 
     ```

## APK Location
- The APK under test is located at `app/mda-1.0.13-15.apk`.
- If you wish to test a different APK, update the path in `AndroidDriverInstance.java`.

## Sample Test Run Video
You can find a sample video of a running test in the `record` folder:

- `record/test_result.mov`

## Project Structure
- `src/main/java/demo/` - Main Java source code (driver, pages, utils)
- `src/test/java/demo/` - Test step definitions and hooks
- `src/test/resources/features/` - Cucumber feature files
- `app/` - APK(s) for testing

## Notes
- The default device name is `emulator-5554`. Update in `AndroidDriverInstance.java` if needed.
- Ensure all dependencies are downloaded via Gradle on first build.
