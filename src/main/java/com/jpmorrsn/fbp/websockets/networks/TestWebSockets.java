/*
 * Copyright (C) J.P. Morrison, Enterprises, Ltd. 2009, 2014 All Rights Reserved. 
 */
package com.jpmorrsn.fbp.websockets.networks;

/* 
 * Useful info:  http://stackoverflow.com/questions/18900187/processing-how-to-send-data-through-websockets-to-javascript-application
 * 
 * This project requires java_websocket.jar - obtainable from https://github.com/TooTallNate/Java-WebSocket/tree/master/dist
 */


import com.jpmorrsn.fbp.engine.Network;

import com.jpmorrsn.fbp.websockets.components.*;



/**
 * This network uses the JavaFBP WebSocketReceive and WebSocketRespond components - these are basically 
 * TooTallNate's AutobahnServerTest code, split into two processes -
 * not to be confused with TestSockets, which uses WriteToSocket and ReadFromSocket! 
 */

public class TestWebSockets extends Network {

  @Override
  protected void define() {

    component("WSRcv", WebSocketReceive.class);
    component("Process", WebSocketSimProc.class);
    component("WSRsp", WebSocketRespond.class);
    
    initialize(new Integer(9003), "WSRcv.PORT");

    connect("WSRcv.OUT", "Process.IN", 40);
    connect("Process.OUT", "WSRsp.IN", 40);
    
    initialize("C:\\Users\\Paul\\Documents\\GitHub\\FBP-Repos\\javafbp\\build\\libs\\javafbp-2.9.jar", "Process.JARFILE");
  }

  public static void main(final String[] argv) throws Exception {
    Network net = new TestWebSockets();
    //net.runTimeReqd = false;
    net.go();
  }

}
