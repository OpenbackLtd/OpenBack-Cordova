//
//  OpenBack
//
//  Copyright © 2015 OpenBack. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <UserNotifications/UserNotifications.h>
#import <OpenBack/OpenBackUserInfo.h>

//! Project version number for OpenBack.
FOUNDATION_EXPORT double OpenBackVersionNumber;

//! Project version string for OpenBack.
FOUNDATION_EXPORT const unsigned char OpenBackVersionString[];

typedef NS_ENUM(NSUInteger, OBKLogLevel) {
    kOBKLogLevelNone, kOBKLogLevelError, kOBKLogLevelWarning,
    kOBKLogLevelInfo, kOBKLogLevelDebug, kOBKLogLevelVerbose
};

typedef NS_ENUM(NSUInteger, OBKCustomTriggerType) {
    kOBKCustomTrigger1, kOBKCustomTrigger2, kOBKCustomTrigger3, kOBKCustomTrigger4, kOBKCustomTrigger5,
    kOBKCustomTrigger6, kOBKCustomTrigger7, kOBKCustomTrigger8, kOBKCustomTrigger9, kOBKCustomTrigger10
};

#pragma mark - OpenBack Main Interface

@interface OpenBack : NSObject

/** Setup OpenBack on-board campaign manager.
 
 Call this function in your application delegate didFinishLaunchingWithOptions:launchOptions.
 OpenBack will first check for the presence of the OpenBackConfig.plist and override any values
 with the passed configuration parameters. You can pass nil if you want to solely use the config
 file provided by your application.
 
 @params config The configuration parameters.
 @returns YES if the user info was set, NO otherwise with an error
 */
+ (BOOL)setupWithConfig:(nullable NSDictionary *)config error:(NSError * _Nullable * _Nullable)error;

/** Set extra user information
 
 @params userInfo A dictionary with the keys kOBKUserInfo...
 @returns YES if the user info was set, NO otherwise with an error
 */
+ (BOOL)setUserInfo:(nullable NSDictionary *)userInfo error:(NSError * _Nullable * _Nullable)error;

/** Set a custom trigger value
 
 Call this function to set a specific custom trigger value that will be checked against campaigns.
 To remove the custom trigger, set the value to nil.
 Expected types: NSString, NSNumber
 
 @params value The value of the trigger (NSString or NSNumber), nil to erase
 @params trigger The trigger to set or erase
 @returns YES if the value was set, NO otherwise with an error
 */
+ (BOOL)setValue:(nullable id)value forCustomTrigger:(OBKCustomTriggerType)trigger error:(NSError * _Nullable * _Nullable)error;

/** Start the OpenBack on-board campaign manager
 
 Call this function to start OpenBack campaign checks.
 
 @params error The error if start failed
 @returns YES if OpenBack was started successfully, NO otherwise with an error
 */
+ (BOOL)start:(NSError * _Nullable * _Nullable)error;

/** Stop the OpenBack on-board campaign manager
 
 Call this function to stop OpenBack campaign checks.
 
 @params error The error if start failed
 @returns YES if OpenBack was stopped successfully, NO otherwise with an error
 */
+ (BOOL)stop:(NSError * _Nullable * _Nullable)error;

// New iOS 10 API

/** Check if OpenBack will handle the notification
 
 If you are using UNUserNotificationCenterDelegate and implementing willPresentNotification::
 call this function before your own code. If it returns YES, the notification was intended to
 OpenBack and you should not handle it.
 
 @params notification the notification to present
 @completionHandler the hanlder called after completion with presentation options
 @return YES if handled by OpenBack
 */
+ (BOOL)handleWillPresentNotification:(UNNotification * _Nullable)notification withCompletionHandler:(void (^ _Nullable)(UNNotificationPresentationOptions))completionHandler;

/** Check if OpenBack will handle the response
 
 If you are using UNUserNotificationCenterDelegate and implementing didReceiveNotificationResponse::
 call this function before your own code. If it returns YES, the response was intended to
 OpenBack and you should not handle it.
 
 @params response the notification response
 @completionHandler the hanlder called after completion when done
 @return YES if handled by OpenBack
 */
+ (BOOL)handleDidReceiveNotificationResponse:(UNNotificationResponse * _Nullable)response withCompletionHandler:(void (^ _Nullable)())completionHandler;

@end

#pragma mark - OpenBack Configuration

/** The application ID (NSString/Required)
 If using OpenBackConfig.plist, key: OBKApplicationID - type: String
 */
extern NSString * const _Nonnull kOBKConfigApplicationId;

/** The application user ID (NSString/Optional)
 If using OpenBackConfig.plist, key: OBKConfigApplicationUserId - type: String
 */
extern NSString * const _Nonnull kOBKConfigApplicationUserId;

/** The advertising ID (NSString/Optional)
 If using OpenBackConfig.plist, key: OBKConfigAdvertisingID - type: String
 */
extern NSString * const _Nonnull kOBKConfigAdvertisingId;

/** Use the development or live server (BOOL/Optional/Default:NO)
 If using OpenBackConfig.plist, key: OBKConfigUseDevServer - type: BOOL
 */
extern NSString * const _Nonnull kOBKConfigUseDevServer;

/** The extra user information (NSDictionary/Optional)
 If using OpenBackConfig.plist, key: OBKConfigUserInfo - type: Dictionary
 */
extern NSString * const _Nonnull kOBKConfigUserInfo;

/** Set the logging level (OBKLogLevel/Optional/Default:None)
 If using OpenBackConfig.plist, key: OBKConfigLogLevel - type: Int
 0: None, 1: Error, 2: Warning, 3: Info, 4: Debug, 5: Verbose
 */
extern NSString * const _Nonnull kOBKConfigLogLevel;

/** Set the notification default sound (NSString/Optional/Default:UILocalNotificationDefaultSoundName)
 On iOS 10, UILocalNotificationDefaultSoundName is converted internally to [UNNotificationSound defaultSound]
 If using OpenBackConfig.plist, key: OBKConfigNotificationSound - type: String
 */
extern NSString * const _Nonnull kOBKConfigNotificationSound;

#pragma mark - Enable/Disable settings

/** Enable system alert notifications (BOOL/Optional/Default:YES)
 If using OpenBackConfig.plist, key: OBKEnableAlertNotifications - type: BOOL
 */
extern NSString * const _Nonnull kOBKConfigEnableAlertNotifications;

/** Enable in-app notifications (BOOL/Optional/Default:YES)
 If using OpenBackConfig.plist, key: OBKEnableInAppNotifications - type: BOOL
 */
extern NSString * const _Nonnull kOBKConfigEnableInAppNotifications;

/** Enable remote notifications (BOOL/Optional/Default:YES)
 If using OpenBackConfig.plist, key: OBKEnableRemoteNotifications - type: BOOL
 */
extern NSString * const _Nonnull kOBKConfigEnableRemoteNotifications;

/** Enable device proximity (BOOL/Optional/Default:NO)
 If using OpenBackConfig.plist, key: OBKEnableProximity - type: BOOL
 */
extern NSString * const _Nonnull kOBKConfigEnableProximity;

/** Enable motion coprocessor (M7/M8/M9) (BOOL/Optional/Default:NO)
 If using OpenBackConfig.plist, key: OBKEnableMotionCoprocessor - type: BOOL
 */
extern NSString * const _Nonnull kOBKConfigEnableMotionCoprocessor;

/** Enable microphone for noise level detection (BOOL/Optional/Default:NO)
 If using OpenBackConfig.plist, key: OBKEnableMicrophone - type: BOOL
 */
extern NSString * const _Nonnull kOBKConfigEnableMicrophone;

/** Enable always location (BOOL/Optional/Default:NO)
 If using OpenBackConfig.plist, key: OBKEnableLocation - type: BOOL
 */
extern NSString * const _Nonnull kOBKConfigEnableLocation;

/** Enable when in use location (BOOL/Optional/Default:NO)
 If using OpenBackConfig.plist, key: OBKEnableLocation - type: BOOL
 */
extern NSString * const _Nonnull kOBKConfigEnableLocationWhenInUse;

#pragma mark - Authorization Prompts

/// For all authorization prompts, it is strongly recommended to set
/// them to NO and to handle the permissions you want to allow in your application.
/// See https://library.launchkit.io/the-right-way-to-ask-users-for-ios-permissions-96fa4eb54f2c

/** Allows OpenBack to prompt for system alert notifications authorization (BOOL/Optional/Default:YES)
 If using OpenBackConfig.plist, key: OBKRequestAlertNotificationsAuthorization - type: BOOL
 */
extern NSString * const _Nonnull kOBKConfigRequestAlertNotificationsAuthorization;

/** Allows OpenBack to prompt for motion coprocessor access authorization (BOOL/Optional/Default:YES)
 If using OpenBackConfig.plist, key: OBKRequestMotionCoprocessorAuthorization - type: BOOL
 */
extern NSString * const _Nonnull kOBKConfigRequestMotionCoprocessorAuthorization;

/** Allows OpenBack to prompt for microphone authorization (BOOL/Optional/Default:YES)
 If using OpenBackConfig.plist, key: OBKRequestMicrophoneAuthorization - type: BOOL
 */
extern NSString * const _Nonnull kOBKConfigRequestMicrophoneAuthorization;

/** Allows OpenBack to prompt for location always authorization (BOOL/Optional/Default:YES)
 If using OpenBackConfig.plist, key: OBKRequestLocationAlwaysAuthorization - type: BOOL
 */
extern NSString * const _Nonnull kOBKConfigRequestLocationAlwaysAuthorization;

/** Allows OpenBack to prompt for location when in use authorization (BOOL/Optional/Default:YES)
 If using OpenBackConfig.plist, key: OBKRequestLocationWhenInUseAuthorization - type: BOOL
 */
extern NSString * const _Nonnull kOBKConfigRequestLocationWhenInUseAuthorization;

#pragma mark - Background Fetch

/** Set the minimum background fetch interval (NSTimeInterval/Optional/Default:UIApplicationBackgroundFetchIntervalMinimum)
 
 OpenBack uses background fetch as a way to update the campaigns with the server and also to
 check if a campaign can be triggered. If your application already uses background fetch, you
 should set this parameter to avoid having OpenBack override your value.
 
 If using OpenBackConfig.plist, key: MinimumBackgroundFetchInterval - type: Number
 Use -1 for UIApplicationBackgroundFetchIntervalNever
 Use 0 for UIApplicationBackgroundFetchIntervalMinimum
 */
extern NSString * const _Nonnull kOBKConfigMinimumBackgroundFetchInterval;

#pragma mark - OpenBack Errors

/** OpenBack error domain. */
extern NSString * const _Nonnull kOBKErrorDomain;

typedef NS_ENUM(NSInteger, OBKErrors) {
    // Runtime errors
    kOBKErrorAlreadyStarted         = 1,        // OpenBack is already running
    kOBKErrorAlreadyStopped         = 2,        // OpenBack is already stopped
    kOBKErrorAlreadySetup           = 3,        // OpenBack is already setup / Setup can only be done once (this might change in the future)
    kOBKErrorNotSetup               = 4,        // OpenBack is not setup, you must setup OpenBack before using any other functions
    // Configuration errors
    kOBKErrorInvalidApplicationId   = 100,      // Application ID is missing or invalid
    kOBKErrorInvalidConfiguration   = 101,      // Configuration contains invalid values (see userInfo...)
    kOBKErrorInvalidEnvironment     = 102,      // Invalid environment (dev or live) for given application 
    // Custom values errors
    kOBKErrorUnkownCustomTrigger    = 200,      // Trying to set an unknown custom trigger
    kOBKErrorInvalidCustomTrigger   = 201,      // Trying to set an invalid value to the custom trigger (only NSString or NSNumber supported)
    kOBKErrorUnkownCustomLog        = 202,      // Trying to set an unknown custom log
    kOBKErrorInvalidCustomLog       = 203,      // Trying to set an invalid value to the custom log (only NSString supported)
    kOBKErrorInvalidUserInfo        = 204,      // Trying to set an invalid user info
    // Fatal error
    kOBKErrorFatalException         = 999,      // Something went really wrong and OpenBack cannot safely recover from it
};
