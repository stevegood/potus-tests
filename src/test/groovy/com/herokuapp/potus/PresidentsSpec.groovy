package com.herokuapp.potus

import geb.spock.GebReportingSpec

class PresidentsSpec extends GebReportingSpec {

  API api

  def setup() {
    api = new API()
  }

  def "Should test that API returns a list of presidents and the UI displays them"() {
    when:
    to IndexPage

    then:
    at IndexPage

    when:
    def apiPresidents = api.listPresidents()

    then:
    apiPresidents.each { president ->
      def uiPresident = $("a[href='/president/$president._id']")
      assert uiPresident
      assert president.name == uiPresident.text()
    }
  }

  def "Should test that API returns President Info Name,Party and the UI displays them based on the selection of the year"() {
    when:
    to SearchByYearPage
    selectYear = '2002'
    submitButton.Click()

    then:
    at IndexPage
    def presidentInfo = api.getPresidentInfoByYear(2002)
    def uiPresident = $("a[href='/president/presidentInfo._id']")
      assert uiPresident
      assert presidentInfo.name == uiPresident.text()
      assert presidentInfo.party == uiPresident.party
  }

  def "Should test that API returns President Info Name and the UI displays them based on search for First or Last name"() {
    when:
    to SearchByNamePage
    searchname = "Abraham"
    submitButton.Click()

    then:
    at IndexPage
    def presidentInfo = api.getPresidentInfoByName("Abraham")
    def uiPresident = $("a[href='/president/presidentInfo._id']")
      assert uiPresident
      assert presidentInfo.name == uiPresident.text()
  }
  
  def "Should test that API returns Many President Info Name and the UI displays them based on search for First or Last name"() {
    when:
    to SearchByNamePage
    searchname = "Bush"
    submitButton.Click()
 
    then:
    at IndexPage
    def presidentInfo = api.getPresidentInfoByName("Bush")
    def uiPresident = $("a[href='/president/presidentInfo._id']")
      assert uiPresident
      assert presidentInfo.name == uiPresident.text()
      assert presidentInfo.size() == 2
  }

  def "Should test that API returns NO President Info Name,Party and the UI displays No Data  based on search for incorrect First or Last name"() {
    when:
    to SearchByNamePage
    searchname = "XYZ"
    submitButton.Click()

    then:
    at SearchByNamePage
    def presidentInfo = api.getPresidentInfoByName("XYZ")
    errors.size == 1
    NoDataFoundError.present
  }

  def "Should test that API returns President Parties and the UI displays them as pie-chart"() {
    when:
    to IndexPage

    then:
    at IndexPage

    when:
    def apiPresidentsPartiesMap = api.listPresidentParties()

    then:
    assert apiPresidentsPartiesMap.collect{it.value.size()}
  }  
}
