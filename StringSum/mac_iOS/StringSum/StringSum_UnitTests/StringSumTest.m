//
//  StringSum_UnitTests.m
//  StringSum_UnitTests
//
//  Created by Tommaso Resti on 12/10/16.
//  Copyright (c) 2016 Tommaso Resti. All rights reserved.
//

#import <XCTest/XCTest.h>
#import "StringSum.h"

@interface StringSumTest : XCTestCase

@end

@implementation StringSumTest

- (void)test_given_two_natural_numbers {
    NSString *sum = [StringSum sum:@"1" with:@"1"];
    XCTAssertEqualObjects(sum, @"2");
}

- (void)test_given_one_non_natural_number {
    NSString *sum = [StringSum sum:@"1" with:@"a"];
    XCTAssertEqualObjects(sum, @"1");
}

- (void)test_given_two_non_natural_numbers {
    NSString *sum = [StringSum sum:@"a" with:@"b"];
    XCTAssertEqualObjects(sum, @"0");
}

- (void)test_given_empty_values {
    NSString *sum = [StringSum sum:@"" with:@""];
    XCTAssertEqualObjects(sum, @"0");
}

- (void)test_given_nil_values {
    NSString *sum = [StringSum sum:nil with:nil];
    XCTAssertEqualObjects(sum, @"0");
}


@end
