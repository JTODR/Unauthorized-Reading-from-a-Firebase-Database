# Unauthorized-Reading-from-a-Firebase-Database
Android app that allows the user to read from a Firebase Database without being authorized. Info from the database is then put into a list for the user to see.

This Android activity will read from a connected Firebase database. The user does not have to be authorized prior to reading from the database.

I used Android Studio 2.2.3 and was therefore able to sign into Android Studio using my Google account. Click the small icon on the top right corner to do this. Then go to the Tools tab and select Firebase. When the Firebase window opens, select Realtime Database and connect your app to Firebase. Then select Add the Realtime Database to your App, this will set up the dependencies for the app.

Log into Firebase with the same Google account as in Android Studio and find your new project in the Console. Go to the Database tab on the left and change the rules of the database to:

{ "rules": { ".read": "auth == null", ".write": "auth == null" } }

These rules will allow for unauthorized reading from the database
