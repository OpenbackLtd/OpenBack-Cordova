# OpenBack Cordova Plugin

## Install the plugin

```bash
cordova plugin add <PATH_TO_OPENBACK_PLUGIN>
```

## Configure OpenBack

Edit your application config.xml with the following preferences

Set your application ID (Required)
```xml
<preference name="com.openback.applicationId" value="YOUR_APPLICATION_ID" />
```

### iOS Specific Configurations

Enable alert notifications (Optional - Default: true)
```xml
<preference name="com.openback.enableAlertNotifications" value="true | false" />
```
Enable remote notifications (Optional - Default: true)
```xml
<preference name="com.openback.enableRemoteNotifications" value="true | false" />
```
Enable proximity sensor (Optional - Default: false)
```xml
<preference name="com.openback.enableProximity" value="true | false" />
```
Enable microphone (Optional - Default: false)
```xml
<preference name="com.openback.enableMicrophone" value="true | false" />
```
Enable motion coprocessor (Optional - Default: false)
```xml
<preference name="com.openback.enableMotionCoprocessor" value="true | false" />
```
Enable location (Optional - Default: false)
```xml
<preference name="com.openback.enableLocation" value="true | false" />
```
Allows OpenBack to prompt for location always authorization (Optional - Default: true)
```xml
<preference name="com.openback.requestLocationAlwaysAuthorization" value="true | false" />
```
Allows OpenBack to prompt for system alert notifications authorization (Optional - Default: true)
```xml
<preference name="com.openback.requestAlertNotificationsAuthorization" value="true | false" />
```
Allows OpenBack to prompt for motion coprocessor access authorization (Optional - Default: true)
```xml
<preference name="com.openback.requestMotionCoprocessorAuthorization" value="true | false" />
```
Allows OpenBack to prompt for microphone authorization (Optional - Default: true)
```xml
<preference name="com.openback.requestMicrophoneAuthorization" value="true | false" />
```
Set the minimim fetch interval in seconds (Optional - Default: 0)
 * Use -1 for UIApplicationBackgroundFetchIntervalNever
 * Use 0 for UIApplicationBackgroundFetchIntervalMinimum
```xml
<preference name="com.openback.minimumFetchInterval" value="0" />
```
Set the notification sound (Optional - Default: UILocalNotificationDefaultSoundName)
```xml
<preference name="com.openback.notificationSound" value="YOUR_SOUND_FILE_NAME" />
```

### Android Specific Configurations

Set the GCM/FCM Sender ID
```xml
<preference name="com.openback.gcmSenderId" value="G-123456" />
```

Follow the [Android Integration Guide](https://gist.github.com/npabion/fed561598677119efc0f8934a477488f) to set the manifest permissions and GCM/FCM services.
> Note: This is a manual step, you can use a plugin like [cordova-custom-config](https://www.npmjs.com/package/cordova-custom-config) to add settings in the application config.xml.

## OpenBack API

OpenBack javascript API is available via the `window.openback` interface.

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
