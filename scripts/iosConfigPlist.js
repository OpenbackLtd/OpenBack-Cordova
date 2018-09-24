'use strict';

module.exports = function(ctx) {
    var fs = ctx.requireCordovaModule('fs'),
        path = ctx.requireCordovaModule('path'),
        plist = ctx.requireCordovaModule('plist'),
        ConfigParser = ctx.requireCordovaModule('cordova-common').ConfigParser;

    var platformRoot = path.join(ctx.opts.projectRoot, 'platforms/ios'),
        configPath = path.join(ctx.opts.projectRoot, 'config.xml'),
        appConfig = new ConfigParser(configPath);

    var openbackConfigPath = path.join(platformRoot, appConfig.name(), 'Resources/OpenBackConfig.plist');
    var openbackConfig = plist.parse(fs.readFileSync(openbackConfigPath, 'utf8')) || {};

    var prefArray = [
        { name: 'com.openback.appCode', pref: 'OBKApplicationID', type: 'string' },
        { name: 'com.openback.enableAlertNotifications', pref: 'OBKEnableAlertNotifications', type: 'boolean' },
        { name: 'com.openback.enableInAppNotifications', pref: 'OBKEnableInAppNotifications', type: 'boolena' },
        { name: 'com.openback.enableRemoteNotifications', pref: 'OBKEnableRemoteNotifications', type: 'boolean' },
        { name: 'com.openback.enableProximity', pref: 'OBKEnableProximity', type: 'boolean' },
        { name: 'com.openback.enableMicrophone', pref: 'OBKEnableMicrophone', type: 'boolean' },
        { name: 'com.openback.enableMotionCoprocessor', pref: 'OBKEnableMotionCoprocessor', type: 'boolean' },
        { name: 'com.openback.enableLocation', pref: 'OBKEnableLocation', type: 'boolean' },
        { name: 'com.openback.requestLocationAlwaysAuthorization', pref: 'OBKRequestLocationAlwaysAuthorization', type: 'boolean' },
        { name: 'com.openback.requestAlertNotificationsAuthorization', pref: 'OBKRequestAlertNotificationsAuthorization', type: 'boolean' },
        { name: 'com.openback.requestMotionCoprocessorAuthorization', pref: 'OBKRequestMotionCoprocessorAuthorization', type: 'boolean' },
        { name: 'com.openback.requestMicrophoneAuthorization', pref: 'OBKRequestMicrophoneAuthorization', type: 'boolean' },
        { name: 'com.openback.minimumFetchInterval', pref: 'OBKMinimumFetchInterval', type: 'number' },
        { name: 'com.openback.notificationSound', pref: 'OBKConfigNotificationSound', type: 'string' },
        { name: 'com.openback.logLevel', pref: 'OBKConfigLogLevel', type: 'number' }
    ];

    console.log(openbackConfigPath);

    for (const pref of prefArray) {
        var prefValue = appConfig.getPlatformPreference(pref['name'], "ios");
        if (!prefValue)
            continue;

        switch(pref['type']) {
            case 'string': openbackConfig[pref['pref']] = prefValue; break;
            case 'boolean': openbackConfig[pref['pref']] = (prefValue == "true"); break;
            case 'number': openbackConfig[pref['pref']] = parseInt(prefValue); break;
        }
    }

    fs.writeFileSync(openbackConfigPath, plist.build(openbackConfig));

    console.log("Updated OpenBackConfig.plist");
};