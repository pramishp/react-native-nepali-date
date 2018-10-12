
# react-native-nepali-date-picker

Nepali-English Date conversion and Date Picker for Nepali React Native Developers.

**Note**: This project uses `https://github.com/keyrunHORNET/date_picker_converter/`.
This is available only for android.

## Getting started

`$ npm install react-native-nepali-date --save`

### Mostly automatic installation

`$ react-native link react-native-nepali-date`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNNepaliDatePickerPackage;` to the imports at the top of the file
  - Add `new RNNepaliDatePickerPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-nepali-date-picker'
  	project(':react-native-nepali-date-picker').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-nepali-date-picker/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-nepali-date-picker')
  	```

## Before you start

 If you want to use Date Picker,

 In MainActivity.java inside `./android/app/src/main/java/<package-name>/` you must extend MainActivity
 to ReactFragmentActivity.

 ```
 public class MainActivity extends ReactFragmentActivity
 ```

## Usage
 ### Get Today's Nepali Date
```javascript
import RNNepaliDate from 'react-native-nepali-date';

RNNepaliDate.getTodayNepaliDate(
        (year, month, day, day_of_week)=>{
                // use as you like
        }
);
```

 ### Convert English To Nepali and Nepali To English

 ```javascript

  RNNepaliDate.englishToNepali(
                 2075, 12, 25,
                 (yy, mm, dd, day_of_week) => {
                     console.log(`Nepali Date : ${yy}/${mm}/${dd} BS`)
                 },
                 (error) => {
                     console.log("Error : ", error)
                 }
             )

  RNNepaliDate.nepaliToEnglish(
                   2018, 08, 15,
                   (yy, mm, dd, day_of_week) => {
                       console.log(`English Date : ${yy}/${mm}/${dd} AD`)
                   },
                   (error) => {
                       console.log("Error : ", error)
                   }
               )

 ```
### Invoke date picker

   #### First subscribe and unsubscribe listeners.

 ```javascript

   componentWillMount() {
         DeviceEventEmitter.addListener("ON_DATE_SET", this.datePicked);
     }

   componentWillUnmount() {
         DeviceEventEmitter.removeListener("ON_DATE_SET", this.datePicked);

     }

   datePicked(data) {
             const {day, month, year} = data;
             console.log(`${year}/${month}/${day} B.S.`)
         }

      ```

    #### Use this method to invoke date picker
    ```javascript
   RNTNepaliCalender.showDatePicker({
              title: "", // title to display on top of date picker
              setDarkTheme: true, // default false
              dismissOnPause: true, // default false
              showYearPickerFirst: true, // default false
              setAccentColor: "#9C27B0",
              newVersion: true  // default false
          });

 ```


## Future Work

- [ ] Support iOS

## Contributing and License

### Issues

Feel free to submit issues and enhancement requests.

### Contributing

1. Fork the repo on GitHub
2. Clone the project to your own machine
3. Commit changes to your own branch
4. Push your work back up to your fork
5. Submit a Pull request so that we can review your changes

### License

Released under the MIT License. Check `LICENSE.md` for more info.

