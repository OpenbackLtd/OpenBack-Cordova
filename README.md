# OpenBack Cordova Plugin

<!-- MarkdownTOC -->

- [Install the plugin](#install-the-plugin)
- [Configure OpenBack](#configure-openback)
- [OpenBack Library API](#openback-library-api)

<!-- /MarkdownTOC -->

## Install the plugin

In your project root folder, type the following:

```bash
cordova plugin add https://github.com/OpenbackLtd/OpenBack-Cordova.git
```

## Configure OpenBack

Edit your application `config.xml` with the following preferences:

Set your application Code (Required)
```xml
<preference name="com.openback.appCode" value="YOUR_APPLICATION_CODE" />
```

### iOS Specific Configurations

Download the latest [OpenBack.framework](https://openbacklive.blob.core.windows.net/temp/OpenBack.framework.zip) for iOS, unzip the file and copy the framework file to `/plugins/cordova-plugin-openback/lib/ios` folder.

```xml
<preference name="com.openback.enableAlertNotifications" value="true|false" />
<preference name="com.openback.enableRemoteNotifications" value="true|false" />
<preference name="com.openback.enableProximity" value="true|false" />
<preference name="com.openback.enableMicrophone" value="true|false" />
<preference name="com.openback.enableMotionCoprocessor" value="true|false" />
<preference name="com.openback.enableLocation" value="true|false" />
<preference name="com.openback.requestLocationAlwaysAuthorization" value="true|false" />
<preference name="com.openback.requestAlertNotificationsAuthorization" value="true|false" />
<preference name="com.openback.requestMotionCoprocessorAuthorization" value="true|false" />
<preference name="com.openback.requestMicrophoneAuthorization" value="true|false" />
<preference name="com.openback.minimumFetchInterval" value="0" />
<preference name="com.openback.notificationSound" value="YOUR_SOUND_FILE_NAME" />
```

| Preference | Description |
| --------- | ----------- |
| enableAlertNotifications | Enable alert notifications (Optional - Default: true) |
| enableRemoteNotifications | Enable remote notifications (Optional - Default: true) |
| enableProximity | Enable proximity sensor (Optional - Default: false) |
| enableMicrophone | Enable microphone (Optional - Default: false) |
| enableMotionCoprocessor | Enable motion coprocessor (Optional - Default: false) |
| enableLocation | Enable location (Optional - Default: false) |
| requestLocationAlwaysAuthorization | Allows OpenBack to prompt for location always authorization (Optional - Default: true) |
| requestAlertNotificationsAuthorization | Allows OpenBack to prompt for system alert notifications authorization (Optional - Default: true) |
| requestMotionCoprocessorAuthorization | Allows OpenBack to prompt for motion coprocessor access authorization (Optional - Default: true) |
| requestMicrophoneAuthorization | Allows OpenBack to prompt for microphone authorization (Optional - Default: true) |
| minimumFetchInterval | Set the minimim fetch interval in seconds (Optional - Default: 0). Use -1 for UIApplicationBackgroundFetchIntervalNever. Use 0 for UIApplicationBackgroundFetchIntervalMinimum |
| notificationSound | Set the notification sound (Optional - Default: UILocalNotificationDefaultSoundName) |


### Android Specific Configurations

#### Edit openback.json

This file is used by the OpenBack library to set the notification icon for android 5+. Make sure the icon is copied to your android platform. You can customize the light, vibration pattern and sound too. For more info, check [Android Integration guide](https://docs.openback.com/?section=initializing-the-openback-library).

The `appCode` should also be set in this file. It is used by the library to setup itself during an application upgrade.

#### Configure Firebase

Add your firebase `google-services.json` at the same level as your application `config.xml`. The google services plugin should package everything for you (e.g. it sets some strings in your application resources).

(Optional) You can also set the FCM Sender ID manually using the following:

```xml
<preference name="com.openback.gcmSenderId" value="G-123456" />
```

For more info, check [OpenBack Android Integration Guide](https://docs.openback.com/?section=android-library-integration).

#### Edit the application Android Manifest

Refer to the [Android Integration guide](https://docs.openback.com/?section=configuring-the-openback-library) to setup the manifest depending on the triggers the application will use. Include the extra permissions and receiver actions in the `config.xml` file of your application.

#### Call `setUserInfo` when the device is ready

Unlike iOS for which we bootstrap automatically, your application needs to call the OpenBack plugin to start things up.
When the device is ready, call `window.openback.setUserInfo({});`. You can pass extra user info as needed too.

## OpenBack Library API

OpenBack library API is accessible in javascript via the `window.openback` interface.

### User Info

```javascript
/**
 * Set the value for a custom trigger
 * Available keys: addressLine1, addressLine2, advertisingId, age,
 *  city, country, countryCode, dateOfBirth, emailAddress, firstName,
 *  gender, phoneNumber, postCode, profession, state, surname, title
 * Note: All values associated with the keys are string
 *
 * @param {Objet} userInfo the user info
 * @param {Function} successCallback The function to call when the configuration succeeds.
 * @param {Function} errorCallback The function to call when there is an error. (OPTIONAL)
 */
 setUserInfo(userInfo, successCallback, errorCallback);

 Example:

 window.openback.setUserInfo({
 	firstName: 'John',
 	lastName: 'Doe',
 	emailAddress: 'john.doe@openback.com'
 }, function() {
 	console.log("Success");
 }); 
 ```

### Custom Trigger

```javascript
/**
 * Available custom triggers
 */
OBKCustomTrigger1: 0, OBKCustomTrigger2: 1, OBKCustomTrigger3: 2, OBKCustomTrigger4: 3, OBKCustomTrigger5: 4,
OBKCustomTrigger6: 5, OBKCustomTrigger7: 6, OBKCustomTrigger8: 7, OBKCustomTrigger9: 8, OBKCustomTrigger10: 9,

/**
 * Set the value for a custom trigger
 *
 * @param {String or Number} value the value to set
 * @param {Number} trigger the custom trigger identifier
 * @param {Function} successCallback The function to call when the configuration succeeds.
 * @param {Function} errorCallback The function to call when there is an error. (OPTIONAL)
 */
setValueForCustomTrigger(value, trigger, successCallback, errorCallback);

Example:

window.openback.setValueForCustomTrigger("Hello", window.openback.OBKCustomTrigger1, function() {
	console.log("Success");
});
```
