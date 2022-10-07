# Simple Example app for android.os.FileObserver

This is android app example to test android.os.FileObserver API in Java.<BR>
As official API reference document says, it requires android API 29(android OS version 10).

refer: https://developer.android.com/reference/android/os/FileObserver

## Requirements

- android studio to build apk from source, tested version: 2021.3.1.16.
- android terminal, OS version 10 and later to test, tested terminal: Google Pixel 3a
- tested java compiler version: openjdk-11-jdk

## basic use:

1. build apk from source in android-studio
2. run app from android-studio
3. play app with camera app on your terminal.<BR>
   press Start button on the app, then take photo, then press Stop button on the app.


## about this app and source

- This app is a minimalist. :-)
- This app observes DCIM/Camera folder, as defined in source (MainActivity)
- This app has just simplest UI (AppCompatActivity) created from 'Empty Activity'
- This app has just two source file, MainActivity and MyFileObserver.
- MyFileObserver shows observed events in TextView area on the app.
