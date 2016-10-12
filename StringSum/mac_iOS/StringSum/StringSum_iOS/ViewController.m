//
//  ViewController.m
//  StringSum_iOS
//
//  Created by Tommaso Resti on 12/10/16.
//  Copyright (c) 2016 Tommaso Resti. All rights reserved.
//

#import "ViewController.h"
#import "StringSum.h"


@interface ViewController ()
@property (weak, nonatomic) IBOutlet UITextField *firstNumberTextField;
@property (weak, nonatomic) IBOutlet UITextField *secondNumberTextField;
@property (weak, nonatomic) IBOutlet UITextField *resultNumberTextField;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.firstNumberTextField.text = @"50";
    self.secondNumberTextField.text = @"50";
    [self calculate];
}

- (IBAction)onSumButtonPressed:(id)sender {
    [self calculate];
}

- (void)calculate {
    NSString *first = self.firstNumberTextField.text;
    NSString *second = self.secondNumberTextField.text;
    self.resultNumberTextField.text = [StringSum sum:first with:second];
}


@end
