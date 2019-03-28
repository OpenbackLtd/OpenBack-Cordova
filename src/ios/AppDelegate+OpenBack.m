//
//  AppDelegate+OpenBack.m
//  OpenBack Cordova Sample
//
//  Created by OpenBack, Ltd on 6/10/16.
//

#import "AppDelegate.h"
#import <objc/runtime.h>
#import <OpenBack/OpenBack.h>

@interface AppDelegate (OpenBack)

@end

@implementation AppDelegate (OpenBack)

+ (void)load {
    NSNotificationCenter *defaultCenter = [NSNotificationCenter defaultCenter];
    [defaultCenter addObserver:self selector:@selector(handleAppDidFinishLaunchingNotification:) name:UIApplicationDidFinishLaunchingNotification object:nil];
}

+ (void)handleAppDidFinishLaunchingNotification:(NSNotification *)notification {
    NSError *error = nil;
    if ([OpenBack setupWithConfig:@{} error:&error]) {
        error = nil;
        if (![OpenBack start:&error]) {
            NSLog(@"Unable to start OpenBack: %@", error);
        }
    } else {
        NSLog(@"OpenBack configuration error: %@", error);
    }
}

@end
