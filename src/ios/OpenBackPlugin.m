#import <Cordova/CDV.h>
@import OpenBack;

@interface OpenBackPlugin : CDVPlugin

- (void)setUserInfo:(CDVInvokedUrlCommand*)command;
- (void)setValueForCustomTrigger:(CDVInvokedUrlCommand*)command;
- (void)setValueForCustomLog:(CDVInvokedUrlCommand*)command;

@end

@implementation OpenBackPlugin

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

- (void)setValueForCustomTrigger:(CDVInvokedUrlCommand *)command
{
    NSError *error = nil;
    CDVPluginResult *pluginResult = nil;
    id value = [command.arguments objectAtIndex:0];
    OBKCustomTriggerType trigger = [[command.arguments objectAtIndex:1] unsignedIntegerValue];
    
    if ([OpenBack setValue:value forCustomTrigger:trigger error:&error]) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:YES];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsBool:NO];
    }
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)setValueForCustomLog:(CDVInvokedUrlCommand *)command
{
    NSError *error = nil;
    CDVPluginResult *pluginResult = nil;
    NSString *value = [command.arguments objectAtIndex:0];
    OBKCustomLogType log = [[command.arguments objectAtIndex:1] unsignedIntegerValue];
    
    if ([OpenBack setValue:value forCustomLog:log error:&error]) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:YES];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsBool:NO];
    }
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
