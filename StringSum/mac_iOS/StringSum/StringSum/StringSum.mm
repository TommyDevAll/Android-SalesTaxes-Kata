//
// Created by Tommaso Resti on 12/10/16.
// Copyright (c) 2016 Tommaso Resti. All rights reserved.
//

#import "StringSum.h"


@implementation StringSum

+ (NSString *)sum:(NSString *)first with:(NSString *)second {
    int sum = [self naturalNumberToInt:first] + [self naturalNumberToInt:second];
    return [NSString stringWithFormat:@"%d", sum];
}

+ (int)naturalNumberToInt:(NSString *)value {
    if([self isNaturalNumber:value])
        return [value intValue];
    return 0;
}

+ (BOOL)isNaturalNumber:(NSString *)value {
    NSRange range = [value rangeOfString:@"[0-9]" options:NSRegularExpressionSearch];
    return range.location != NSNotFound;
}

@end