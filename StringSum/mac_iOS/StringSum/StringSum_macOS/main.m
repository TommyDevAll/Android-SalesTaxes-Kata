//
//  main.m
//  StringSum_macOS
//
//  Created by Tommaso Resti on 12/10/16.
//  Copyright (c) 2016 Tommaso Resti. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "StringSum.h"

int main(int argc, const char * argv[]) {
    @autoreleasepool {

        NSString *first = @"100";
        NSString *second = @"22";
        NSString *sum = [StringSum sum:first with:second];

        NSLog(@"%@ + %@ = %@", first, second, sum);
    }

    return 0;
}