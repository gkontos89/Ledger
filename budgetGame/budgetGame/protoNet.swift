//
//  protoNet.swift
//  saveGame
//
//  Created by andrew alfieri on 3/6/18.
//  Copyright © 2018 Marshmallow. All rights reserved.
//

import Foundation

class protoNet: NSObject, networkCall{
    
    
    weak var delegate: protoNetDelegate?
    
    var mess = HeartBeat()
    var inputStream: InputStream!
    var outputStream: OutputStream!
    
    var username = ""
    
    let maxReadLength = 4096
    
    func setupNetwork(address: String, port: UInt32) {
        var readStream: Unmanaged<CFReadStream>?
        var writeStream: Unmanaged<CFWriteStream>?
        
        // 2
        CFStreamCreatePairWithSocketToHost(kCFAllocatorDefault,
                                           address as CFString,
                                           port,
                                           &readStream,
                                           &writeStream)
        print("1")
        inputStream = readStream!.takeRetainedValue()
        outputStream = writeStream!.takeRetainedValue()
        
        inputStream.delegate = self
        
        inputStream.schedule(in: .current, forMode: .commonModes)
        outputStream.schedule(in: .current, forMode: .commonModes)
        
        inputStream.open()
        outputStream.open()
    }
    
    func sendMessage(message: Data) {
        //        print("1\n")
        //        var binaryData = Data()
        //        print("2\n")
        //        print("3\n")
        //
        //        do {
        //            binaryData = try message.serializedData()
        //        } catch {
        //            print(error)
        //        }
        
        print("4")
        _ = message.withUnsafeBytes {outputStream.write($0, maxLength: message.count)}
        print("5")
    }
}

extension protoNet: StreamDelegate {
    
    func stream(_ aStream: Stream, handle eventCode: Stream.Event) {
        switch eventCode {
        case Stream.Event.hasBytesAvailable:
            print("new message received")
            readBytes(stream: aStream as! InputStream)
        case Stream.Event.endEncountered:
            print("new message received")
        case Stream.Event.errorOccurred:
            print("error occurred")
        case Stream.Event.hasSpaceAvailable:
            print("has space available")
        default:
            print("some other event...")
            break
        }
    }
    
    private func readBytes(stream: InputStream) {
        //1
        let buffer = UnsafeMutablePointer<UInt8>.allocate(capacity: maxReadLength)
        var dat = Data()
        //2
        while stream.hasBytesAvailable {
            //3
            let numberOfBytesRead = inputStream.read(buffer, maxLength: maxReadLength)
            dat.append(buffer, count: numberOfBytesRead)
            //4
            if numberOfBytesRead < 0 {
                if let _ = stream.streamError {
                    break
                }
            }
        }
        var decodedMess = Header()
        do {
            decodedMess = try Header(serializedData: dat)
            print(decodedMess.id)
        } catch {
            print(error)
        }
        modelFactory.mods.update(id: decodedMess.id, data: dat)
        delegate?.receivedMessage()
    }
    
    private func processedMessageString(serializedData: Data){
    }
}
