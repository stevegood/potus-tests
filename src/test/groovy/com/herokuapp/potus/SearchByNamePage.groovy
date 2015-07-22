package com.herokuapp.potus

import geb.Page

class SearchByNamePage extends Page {
  static url = 'search-by-name.html'

  static at = {
    title == 'SearchByNamePage'
  }

  static content = {
    searchByNameForm {$("#search-name-form")}
    searchname {searchByNameForm.searchname}
    submitButton(to: IndexPage) {
    searchByNameForm.find("input",type: "submit")
    }
  }
}
