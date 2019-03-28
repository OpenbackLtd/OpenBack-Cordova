var exec = require('cordova/exec'),
    cordova = require('cordova'),
    argscheck = require('cordova/argscheck');

module.exports = {

  /**
   * If your application needs COPPA to be enabled, set the COPPA compliant status with this function. 
   * If COPPA is enabled, campaigns will not run unless compliant is set to true.
   * 
   * @param {Boolean} The COPPA compliant status, true or false.
   * @param {Function} successCallback The function to call when the configuration succeeds.
   * @param {Function} errorCallback The function to call when there is an error. (OPTIONAL)
   */
  coppaCompliant: function(compliant, successCallback, errorCallback) {
    argscheck.checkArgs('*FF', 'OpenBack.coppaCompliant', arguments);
    exec(successCallback, errorCallback, "OpenBack", "coppaCompliant", [compliant]);
  },
  
  /**
   * When your user requests that all the data should be erased, set this value to true. 
   * It will inform the OpenBack server to remove all logs for the current user. 
   * All future logs will be erased after processing. If you wish to be fully GDPR complient, 
   * you also need to enable GDPR in your application settings on the OpenBack Dashboard.
   * 
   * @param {Boolean} If true, user data will be cleared on the server.
   * @param {Function} successCallback The function to call when the configuration succeeds.
   * @param {Function} errorCallback The function to call when there is an error. (OPTIONAL)
   */
  gdprForgetUser: function(forgetUser, successCallback, errorCallback) {
    argscheck.checkArgs('*FF', 'OpenBack.gdprForgetUser', arguments);
    exec(successCallback, errorCallback, "OpenBack", "gdprForgetUser", [forgetUser]);
  },

  /**
   * At any point, you can log a goal with the step number and value associated with it. 
   * Goals are created in the OpenBack Dashboard. You can then associate a campaign to a goal. 
   * When logging a goal, use the goal code.
   * 
   * @param {String} goal The coal code
   * @param {Number} step The step number (integer)
   * @param {Number} value The value associated with this goal (double)
   * @param {Function} successCallback The function to call when the configuration succeeds.
   * @param {Function} errorCallback The function to call when there is an error. (OPTIONAL)
   */
  logGoal: function(goal, step, value, successCallback, errorCallback) {
    argscheck.checkArgs('SNNFF', 'OpenBack.logGoal', arguments);
    exec(successCallback, errorCallback, "OpenBack", "logGoal", [goal, step, value]);
  },

  /**
   * Get the current OpenBack SDK version.
   * 
   * @param {Function} successCallback The function to call when the configuration succeeds.
   * @param {Function} errorCallback The function to call when there is an error. (OPTIONAL)
   */
  sdkVersion: function(successCallback, errorCallback) {
    exec(successCallback, errorCallback, "OpenBack", "sdkVersion", []);
  },

  CUSTOM_TRIGGER_1: 0, CUSTOM_TRIGGER_2: 1, CUSTOM_TRIGGER_3: 2, CUSTOM_TRIGGER_4: 3, CUSTOM_TRIGGER_5: 4,
  CUSTOM_TRIGGER_6: 5, CUSTOM_TRIGGER_7: 6, CUSTOM_TRIGGER_8: 7, CUSTOM_TRIGGER_9: 8, CUSTOM_TRIGGER_10: 9,

  /**
   * Set the value for a custom trigger
   *
   * @param {String|Number} the value to set
   * @param {Number} the custom trigger identifier
   * @param {Function} successCallback The function to call when the configuration succeeds.
   * @param {Function} errorCallback The function to call when there is an error. (OPTIONAL)
   */
  setCustomTrigger: function(trigger, value, successCallback, errorCallback) {
    argscheck.checkArgs('N*FF', 'OpenBack.setCustomTrigger', arguments);
    exec(successCallback, errorCallback, "OpenBack", "setCustomTrigger", [trigger, value]);
  },

  /**
   * Set the value for a custom trigger
   * Available keys: addressLine1, addressLine2, advertisingId, age,
   *  city, country, countryCode, dateOfBirth, emailAddress, firstName,
   *  gender, phoneNumber, postCode, profession, state, surname, title
   * All values are string type
   *
   * @param {Objet} the user info
   * @param {Function} successCallback The function to call when the configuration succeeds.
   * @param {Function} errorCallback The function to call when there is an error. (OPTIONAL)
   */
  setUserInfo: function(userInfo, successCallback, errorCallback) {
    argscheck.checkArgs('OFF', 'OpenBack.setUserInfo', arguments);
    exec(successCallback, errorCallback, "OpenBack", "setUserInfo", [userInfo]);
  }
};
