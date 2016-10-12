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
    [self initializeExample];

    [self registerForTextChanges:self.firstNumberTextField];
    [self registerForTextChanges:self.secondNumberTextField];
}

- (void)initializeExample {
    self.firstNumberTextField.text = @"50";
    self.secondNumberTextField.text = @"50";
    [self calculate];
}

- (void)registerForTextChanges:(UITextField *)textField {
    [textField addTarget:self action:@selector(textFieldDidChange:) forControlEvents:UIControlEventEditingChanged];
}

- (void)textFieldDidChange:(id)textFieldDidChange {
    [self calculate];
}

- (void)calculate {
    NSString *first = self.firstNumberTextField.text;
    NSString *second = self.secondNumberTextField.text;
    self.resultNumberTextField.text = [StringSum sum:first with:second];
}

@end
