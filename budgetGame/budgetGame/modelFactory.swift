//
//  modelFactory.swift
//  budgetGame
//
//  Created by andrew alfieri on 3/10/18.
//  Copyright © 2018 Marshmallow. All rights reserved.
//

import Foundation

class modelFactory{
    static let mods = modelFactory()
    var models: [String: model] = ["loginModel": loginModel()]
    
    init(){
    }
    
    func get(mod: String) -> model{
        var ret: model
        
        ret = models[mod]!
        
        return ret
    }
    
    func update(id: String, data: Data){
        print("update Models")
        switch id {
        case "CreateAccountMessageResponse":
            models["loginModel"]?.handle(id: id, data: data)
        case "LoginApproved":
            models["loginModel"]?.handle(id: id, data: data)
        default:
            print("Not Recognized")
        }
    }
}

class loginModel: model{
    
    var logged = false
    var logIn = true
    var username = ""
    var password = ""
    var organization = ""
    var grade = ""
    var email = ""
    
    func handle(id: String, data: Data) {
        print("update loginModel")
        do {
            switch id {
            case "CreateAccountMessageResponse":
                let decodedMess = try CreateAccountMessageResponse(serializedData: data)
                logged = decodedMess.success
            case "LoginApproved":
                let decodedMess = try CreateAccountMessageResponse(serializedData: data)
                logged = decodedMess.success
            default:
                print("Not Recognized")
            }
        } catch {
            print(error)
        }
    }
}
