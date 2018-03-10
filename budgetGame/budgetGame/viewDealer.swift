//
//  viewDealer.swift
//  budgetGame
//
//  Created by andrew alfieri on 3/9/18.
//  Copyright © 2018 Marshmallow. All rights reserved.
//

import Foundation
import UIKit

class viewDealer{
    
    init(){
        
    }
    
    func packY(majorView: UIView){
        var dayViewSubviews = [UIView]()
        var yTot = 0
        var yCurr = 0
        for subView in majorView.subviews {
            if(!subView.isHidden){
                dayViewSubviews.append(subView)
                yTot = yTot + Int(subView.frame.size.height * 1.1)
            }
        }
        let top = Int(majorView.center.y) - (yTot/2)
        for subView in dayViewSubviews{
            subView.frame.origin.y = CGFloat(top + yCurr)
            yCurr = yCurr + Int(subView.frame.size.height * 1.1)
        }
    }
}
