//
//  loginViewController.swift
//  budgetGame
//
//  Created by andrew alfieri on 3/9/18.
//  Copyright © 2018 Marshmallow. All rights reserved.
//

import Foundation
import UIKit

class loginViewController: UIViewController, protoNetDelegate {
    
    var net: protoNet!
    var logIn: Bool = true
    let mod = modelFactory.mods.get(mod: "loginModel") as! loginModel
    @IBOutlet var majorView: UIView!
    @IBOutlet weak var username: UITextField!
    @IBOutlet weak var password: UITextField!
    @IBOutlet weak var organization: UITextField!
    @IBOutlet weak var grade: UITextField!
    @IBOutlet weak var email: UITextField!
    @IBOutlet weak var sendButton: UIButton!
    @IBOutlet weak var logInButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        net = protoNet()
        net.delegate = self
        update()
    }
    
    @IBAction func switchView(_ sender: Any) {
        mod.logIn = !mod.logIn
        update()
    }
    
    func pushData() {
        mod.username = username.text!
        mod.password = password.text!
        mod.organization = organization.text!
        mod.grade = grade.text!
        mod.email = email.text!
    }
    
    @IBAction func sendBeat(_ sender: Any) {
        connectHeart()
        pushData()
        if(mod.logIn){
            var mess = LoginRequest()
            mess.id = "LoginRequest"
            mess.username = mod.username
            mess.password = mod.password
            do {
                try net.sendMessage(message: mess.serializedData())
            } catch {
                print(error)
            }
        }else{
            var mess = CreateAccountMessage()
            mess.id = "CreateAccountMessage"
            mess.username = mod.username
            mess.password = mod.password
            mess.org = mod.organization
            mess.grade = mod.grade
            mess.email = mod.email
            do {
                try net.sendMessage(message: mess.serializedData())
            } catch {
                print(error)
            }
        }
    }
    
    func connectHeart() {
        let addr: String = "192.168.1.153"
        let port: UInt32 = 8321
        net.setupNetwork(address: addr, port: port)
    }
    
    func update(){
        if(mod.logged){
            print("here")
            let nextViewController = self.storyboard?.instantiateViewController(withIdentifier: "summaryViewController") as! ViewController
            //self.navigationController?.pushViewController(nextViewController, animated: true)
            present(nextViewController, animated: true, completion: nil)
        }else{
            organization.isHidden = mod.logIn
            grade.isHidden = mod.logIn
            email.isHidden = mod.logIn
            sendButton.setTitle(mod.logIn ? "log in" : "register", for: .normal)
            logInButton.setTitle(mod.logIn ? "register" : "log in", for: .normal)
            let dealer = viewDealer()
            dealer.packY(majorView: majorView)
        }
    }
    
    func receivedMessage() {
        update()
        if(!mod.logged){
            let alert = UIAlertController(title: "Error", message: "account already exists", preferredStyle: UIAlertControllerStyle.actionSheet)
            self.present(alert, animated: true, completion: nil)
        }
    }
    
    
}
