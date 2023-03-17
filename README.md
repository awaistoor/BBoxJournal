<img src="https://www.bboxx.com/wp-content/uploads/WhatsApp-Image-2022-07-20-at-11.05.38-AM.jpeg"  height=250 alt="BBoxJournal App" />

# BBoxJournal App

An android and koltin based application as a technical challenge from BBox.

![Version v1.0 Badge][version-badge] ![Android 33+ Badge][android-version-badge] ![Kotlin 1.7.41 Badge][kotlin-version-badge]

## Demo



https://user-images.githubusercontent.com/3141921/225912336-c57d7485-be06-4aa4-9e27-566060de946d.mp4




#### Prerequisites

Use the latest Android Studio version. Use Android SDK version 33 to compile the code.

```bash
compileSdkVersion 33
minSdkVersion 24
targetSdkVersion 33
```

#### Getting started
- Get this repo by cloning it through Android Studio's VCS or downloading it through this page and then importing it in Android Studio.
- Select `app` from **Run** dropdown option the toolbar of the IDE.
- Compile this application and let it download the required dependencies.
- If needed, upgrade the gradle to the required version.
- Click on play button to run the app on an emulator or physical device.
- For tests, right click on the `app` module and click on the **Run All Tests** option from the dropdown menu.
- For running individual test files, please go to test package of this module.

#### Other Information
- This application is developed by using **SOLID** principles with **Clean Architecture**.
- This application is also developed while following the **MVVM** architecture as well as using architectural components from `JetPack`.
- App loads all journals from local db.
- The list is grouped into month and date.
- The Month group is colored by the mood of the whole group.
- Journal detail can be view by clicking on any journal from the list.
- User can delete the journal by going into journal's detail.
- User can add a new journal as well.
- Different data models are used to decouple each layer.
- Single Activity multiple fragment is used.
- `Mockito` is used to unit test each layer.
- Dark Mode is supported in the app.

#### Further enhancements

- UI testing can be added for UI flows
- Pagination can be added as a further enhancement.
- UI can be improved.

[version-badge]: https://img.shields.io/badge/Version-v1.0-blue

[android-version-badge]: https://img.shields.io/badge/Android-33+-brightgreen

[kotlin-version-badge]: https://img.shields.io/badge/kotlin-1.7.21-orange
