//
// Created by Tommaso Resti on 12/10/16.
// Copyright (c) 2016 Tommaso Resti. All rights reserved.
//

#import "StringSum.h"


@implementation StringSum

+ (NSString *)sum:(NSString *)string with:(NSString *)with {
    int sum = [self naturalNumberToInt:string] + [self naturalNumberToInt:with];
    return [NSString stringWithFormat:@"%d", sum];
}

+ (int)naturalNumberToInt:(NSString *)value {
    if([StringSum isNaturalNumber:value])
        return [value intValue];
    return 0;
}

+ (BOOL)isNaturalNumber:(NSString *)string {
    NSRange range = [string rangeOfString:@"[0-9]" options:NSRegularExpressionSearch];
    return range.location != NSNotFound;
}

@end