package com.ftel.paytv.Services.Statistics

/**
  * Created by hungdv on 02/02/2017.
  */
case class LogParsed (
     customerId: String,
     contract: String,
     logId: String,
     appName: String,
     itemId: String,
     realTimePlaying: String,
     sessionMainMenu: String,
     boxTime: String,
     received_at: String,
     sessionSubMenu: String,
     ip_wan: String,
     firmware: String,
     screen: String,
     duration: String
   )
