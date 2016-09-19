var exec = require('cordova/exec'),
    cordova = require('cordova'),
    argscheck = require('cordova/argscheck');

module.exports = {

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
      argscheck.checkArgs('oFF', 'OpenBack.setUserInfo', arguments);
      exec(successCallback, errorCallback, "OpenBack", "setUserInfo", [userInfo]);
    },

    OBKCustomTrigger1: 0, OBKCustomTrigger2: 1, OBKCustomTrigger3: 2, OBKCustomTrigger4: 3, OBKCustomTrigger5: 4,
    OBKCustomTrigger6: 5, OBKCustomTrigger7: 6, OBKCustomTrigger8: 7, OBKCustomTrigger9: 8, OBKCustomTrigger10: 9,

    /**
     * Set the value for a custom trigger
     *
     * @param {String|Number} the value to set
     * @param {Number} the custom trigger identifier
     * @param {Function} successCallback The function to call when the configuration succeeds.
     * @param {Function} errorCallback The function to call when there is an error. (OPTIONAL)
     */
    setValueForCustomTrigger: function(value, trigger, successCallback, errorCallback) {
      argscheck.checkArgs('*nFF', 'OpenBack.setValueForCustomTrigger', arguments);
      exec(successCallback, errorCallback, "OpenBack", "setValueForCustomTrigger", [value, trigger]);
    }
};
