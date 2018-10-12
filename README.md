
# react-native-nepali-date-picker

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


## Usage
```javascript
import RNNepaliDate from 'react-native-nepali-date';

RNNepaliDate;
```
  