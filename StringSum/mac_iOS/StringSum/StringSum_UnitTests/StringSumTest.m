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

- (void)setUp {
    [super setUp];
    // Put setup code here. This method is called before the invocation of each test method in the class.
}

- (void)tearDown {
    // Put teardown code here. This method is called after the invocation of each test method in the class.
    [super tearDown];
}

- (void)test_given_two_natural_number {
    NSString *sum = [StringSum sum:@"1" with:@"1"];
    XCTAssertEqualObjects(sum, @"2");
}

@end
