# Family Movie Database (FMDb)
## About
FMDb is an app that helps a family keep a record of movies watched. It allows you to see the top rated movies, average ratings, and stats of each movie, acording to your family's ratings.

It was created as the final assignment for VLN Computer Programming 11.

## Running
1. Clone the repo.
2. This project uses JavaFx. Download and install [JavaFX](https://openjfx.io/).
3. A jar file is located at `out/artifacts/fmdb_jar/fmdb.jar`.
4. A sample database is at the root of the project `.fmdb`

To run the program with the sample database from the root directory:
``` bash
java --module-path <Path to JavaFx Library> --add-modules javafx.controls,javafx.fxml -jar <Path to jar>
```
For example on a Mac:
```
java --module-path /Users/liamilan/Projects/Libs/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml -jar  out/artifacts/fmdb_jar/fmdb.jar
```
To clear sample data - delete the file `.fmdb` file.

### Development
In Intellij, open VM options (dropdown > Edit Configuration) and add the flollowing:
```
--module-path <Path to JavaFx Library> --add-modules javafx.controls,javafx.fxml
```
For example on a Mac:
```
--module-path /Users/liamilan/Projects/Libs/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml
```
