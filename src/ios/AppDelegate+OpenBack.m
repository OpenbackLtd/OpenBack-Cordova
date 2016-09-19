//
//  AppDelegate+OpenBack.m
//  OpenBack Cordova Sample
//
//  Created by Nicolas Pabion on 6/10/16.
//
//

#import "AppDelegate.h"
#import <objc/runtime.h>
#import <OpenBack/OpenBack.h>

@interface AppDelegate (OpenBack)

@end

@implementation AppDelegate (OpenBack)

+ (void)load
{
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        Class class = [self class];
        
        SEL originalSelector = @selector(application:didFinishLaunchingWithOptions:);
        SEL swizzledSelector = @selector(openback_application:didFinishLaunchingWithOptions:);
        
        Method originalMethod = class_getInstanceMethod(class, originalSelector);
        Method swizzledMethod = class_getInstanceMethod(class, swizzledSelector);
        
        BOOL didAddMethod = class_addMethod(class, originalSelector, method_getImplementation(swizzledMethod), method_getTypeEncoding(swizzledMethod));
        
        if (didAddMethod) {
            class_replaceMethod(class, swizzledSelector, method_getImplementation(originalMethod), method_getTypeEncoding(originalMethod));
        } else {
            method_exchangeImplementations(originalMethod, swizzledMethod);
        }
    });
}

- (BOOL)openback_application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    NSError *error = nil;
    if ([OpenBack setupWithConfig:@{} error:&error]) {
        error = nil;
        if (![OpenBack start:&error]) {
            NSLog(@"Unable to start OpenBack: %@", error);
        }
    } else {
        NSLog(@"OpenBack configuration error: %@", error);
    }
    return [self openback_application:application didFinishLaunchingWithOptions:launchOptions];
}

@end
