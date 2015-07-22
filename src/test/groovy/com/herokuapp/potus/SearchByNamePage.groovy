package com.herokuapp.potus

import geb.Page

class SearchByNamePage extends Page {
  static url = 'search-by-name.html'

  static at = {
    title == 'Presidents of the United States'
  }

  static content = {
    searchByNameForm {$("#search-name-form")}
    searchname {searchByNameForm.searchname}
    submitButton(to: IndexPage) {
    searchByNameForm.find("input",type: "submit")
    }
    errors(required:false) { $(".errors li") }
    NoDataFoundError(required:false) {
    errors.filter(text:contains("No Data Found"))
    }
  }
}
