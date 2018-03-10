//
//  ViewController.swift
//  Saver
//
//  Created by andrew alfieri on 3/3/18.
//  Copyright © 2018 Marshmallow. All rights reserved.
//

import UIKit

class ViewController: UIViewController, protoNetDelegate {
    
    var net: protoNet!
    
    @IBOutlet weak var label: UILabel!
    @IBOutlet weak var addressName: UITextField!
    @IBOutlet weak var portNum: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        net = protoNet()
        // Do any additional setup after loading the view, typically from a nib.
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func receivedMessage() {
        
    }
    
    @IBAction func connectHeart(_ sender: Any) {
        var add: String = "localhost"
        var port = 4555 as UInt32
        add = addressName.text! 
        port = UInt32(portNum.text!) ?? port
        net.setupNetwork(address: add, port: port)
    }
    
    @IBAction func sendBeat(_ sender: Any) {
    }
    
}

class protoHeart: protoStruct {
    
    var beat: String
    
    init(){
        beat = ""
    }
    
    func fill() {}
    
    func fill(proto: HeartBeat) {
        beat = proto.beat
    }
}


//protocol MessageInputDelegate {
//    func sendWasTapped(message: String)
//}
//
//protocol ChatRoomDelegate: class {
//    func receivedMessage(message: String)
//}

//class protoNet: NSObject {
//
//    weak var delegate: ChatRoomDelegate?
//
//    var mess = HeartBeat()
//    var inputStream: InputStream!
//    var outputStream: OutputStream!
//
//    var username = ""
//
//    let maxReadLength = 4096
//
//    func setupNetwork(address: CFString, port: UInt32) {
//        var readStream: Unmanaged<CFReadStream>?
//        var writeStream: Unmanaged<CFWriteStream>?
//
//        // 2
//        CFStreamCreatePairWithSocketToHost(kCFAllocatorDefault,
//                                           address,
//                                           port,
//                                           &readStream,
//                                           &writeStream)
//
//        inputStream = readStream!.takeRetainedValue()
//        outputStream = writeStream!.takeRetainedValue()
//
//        inputStream.delegate = self
//
//        inputStream.schedule(in: .current, forMode: .commonModes)
//        outputStream.schedule(in: .current, forMode: .commonModes)
//
//        inputStream.open()
//        outputStream.open()
//    }
//
//    func sendMessage(message: String) {
//        print("1\n")
//        var binaryData = Data()
//        print("2\n")
//        mess.id = "1"
//        mess.beat = "Hey kid, wanna buy some cocaine?"
//        print("3\n")
//        do {
//            binaryData = try mess.serializedData()
//        } catch {
//            print(error)
//        }
//
//        print("4\n")
//        _ = binaryData.withUnsafeBytes {outputStream.write($0, maxLength: binaryData.count)}
//        print("5\n")
//    }
//}
//
//extension protoNet: StreamDelegate {
//    func stream(_ aStream: Stream, handle eventCode: Stream.Event) {
//        switch eventCode {
//        case Stream.Event.hasBytesAvailable:
//            print("new message received")
//        case Stream.Event.endEncountered:
//            print("new message received")
//        case Stream.Event.errorOccurred:
//            print("error occurred")
//        case Stream.Event.hasSpaceAvailable:
//            print("has space available")
//            readBytes(stream: aStream as! InputStream)
//        default:
//            print("some other event...")
//            break
//        }
//    }
//
//    private func readBytes(stream: InputStream) {
//        //1
//        let buffer = UnsafeMutablePointer<UInt8>.allocate(capacity: maxReadLength)
//        var dat = Data()
//        //2
//        while stream.hasBytesAvailable {
//            //3
//            let numberOfBytesRead = inputStream.read(buffer, maxLength: maxReadLength)
//            dat.append(buffer, count: numberOfBytesRead)
//            //4
//            if numberOfBytesRead < 0 {
//                if let _ = stream.streamError {
//                    break
//                }
//            }
//            var decodedMess = HeartBeat()
//            decodedMess.id = "-1"
//            decodedMess.beat = "test"
//            do {
//                decodedMess = try HeartBeat(serializedData: dat)
//            } catch {
//                print(error)
//            }
//            if let message = processedMessageString(message: decodedMess) {
//                delegate?.receivedMessage(message: message)
//            }
//
//        }
//    }
//
//    private func processedMessageString(message: HeartBeat) -> String? {
//        return message.beat
//    }
//
//}




