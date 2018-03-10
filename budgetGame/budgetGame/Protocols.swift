//
//  networkProtocol.swift
//  saveGame
//
//  Created by andrew alfieri on 3/6/18.
//  Copyright © 2018 Marshmallow. All rights reserved.
//

import Foundation
protocol networkCall {
    func setupNetwork(address: String, port: UInt32)
    func sendMessage(message: Data)
}

protocol MessageInputDelegate {
    func sendWasTapped(message: String)
}

protocol protoNetDelegate: class {
    func receivedMessage()
}

protocol protoStruct: class {
    func fill()
}

protocol model {
    func handle(id: String, data: Data)
}
