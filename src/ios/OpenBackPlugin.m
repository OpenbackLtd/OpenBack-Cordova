#import <Cordova/CDV.h>
@import OpenBack;

@interface OpenBackPlugin : CDVPlugin

- (void)coppaCompliant:(CDVInvokedUrlCommand*)command;
- (void)gdprForgetUser:(CDVInvokedUrlCommand*)command;
- (void)logGoal:(CDVInvokedUrlCommand*)command;
- (void)sdkVersion:(CDVInvokedUrlCommand*)command;
- (void)setUserInfo:(CDVInvokedUrlCommand*)command;
- (void)setValueForCustomTrigger:(CDVInvokedUrlCommand*)command;

@end

@implementation OpenBackPlugin
  
- (void)coppaCompliant:(CDVInvokedUrlCommand*)command
{
    BOOL compliant  = [[command.arguments objectAtIndex:0] boolValue];
    [OpenBack coppaCompliant:compliant];
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:YES];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];

}

- (void)gdprForgetUser:(CDVInvokedUrlCommand*)command
{
    NSError *error = nil;
    CDVPluginResult *pluginResult = nil;
    BOOL forgetUser  = [[command.arguments objectAtIndex:0] boolValue];
    
    if ([OpenBack gdprForgetUser:forgetUser error:&error]) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:YES];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsBool:NO];
    }
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)logGoal:(CDVInvokedUrlCommand*)command
{
    NSError *error = nil;
    CDVPluginResult *pluginResult = nil;
    NSString *goal  = [command.arguments objectAtIndex:0];
    NSUInteger step  = [[command.arguments objectAtIndex:1] integerValue];
    double value  = [[command.arguments objectAtIndex:2] doubleValue];
    
    if ([OpenBack logGoal:goal step:step value:value error:&error]) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:YES];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsBool:NO];
    }
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)sdkVersion:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult *pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:kOBKFrameworkVersion];
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)setUserInfo:(CDVInvokedUrlCommand *)command
{
    NSError *error = nil;
    CDVPluginResult *pluginResult = nil;
    NSDictionary *userInfo = [command.arguments objectAtIndex:0];
    
    if ([OpenBack setUserInfo:userInfo error:&error]) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:YES];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsBool:NO];
    }
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)setCustomTrigger:(CDVInvokedUrlCommand *)command
{
    NSError *error = nil;
    CDVPluginResult *pluginResult = nil;
    id value = [command.arguments objectAtIndex:1];
    OBKCustomTriggerType trigger = [[command.arguments objectAtIndex:0] unsignedIntegerValue];
    
    if ([OpenBack setValue:value forCustomTrigger:trigger error:&error]) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:YES];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsBool:NO];
    }
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
