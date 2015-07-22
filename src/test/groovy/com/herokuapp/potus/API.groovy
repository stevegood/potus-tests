package com.herokuapp.potus

import groovyx.net.http.RESTClient

class API {
  static final String ROOT = System.getProperty('API_URL').toString()
  def http
     // Moved Http in constructor to initialize so that will use in other services
      API() {
        http = new RESTClient(ROOT)
      }
  def listPresidents() {
    def res = http.get(
      path: '/presidents'
    )
    res.data
  }
  /*
    This Service will get the PresidentInfo based on the Year
  */
    def getPresidentInfoByYear(String year) {
    def res = http.get(
      path: "/presidents/with-year/$year"
    )
    res.data
  }

  /*
    This Service will get the PresidentInfo based on the Name
  */
    def getPresidentInfoByName(String name) {
    def res = http.get(
      path: "/presidents/name-like/$name"
    )
    res.data
  }

  /*
    This Service will list all the President parties
  */
    def listPresidentParties() {
    def res = http.get(
      path: "/presidents/parties"
    )
    res.data
  }

}
