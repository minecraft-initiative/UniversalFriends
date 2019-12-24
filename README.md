# UniversalFriends
A library that aims at having friends that are the same for each utility mod, such as [ForgeHax](https://github.com/fr1kin/ForgeHax) and [KAMI](https://github.com/S-B99/KAMI). 

Critism and discussion is welcome. Join the [discord](https://discord.gg/JNjnpBu) for discussion.

Read the standard [here](STANDARD.md).

## Including
```gradle
repositories {
    maven {
        name 'JitPack'
        url 'https://jitpack.io'
    }
}

dependencies {
    ext {
        universalFriendsVersion = '-SNAPSHOT'
    }

    implementation "com.github.IronException:UniversalFriends:$universalFriendsVersion"
}
```

## Building
```
./gradlew build
```
Will produce a `.jar` file in `build/libs`.

> Note: this jar file does not contain gson. It must be included seperately in your classpath. If you are using UniversalFriends in minecraft, gson is already in the classpath.

## Contributing
Before making a pull request for changes you make, please confirm that all tests succeed, and that you follow the standard.
```
./gradlew test
```
