package com.herokuapp.potus

import geb.Page

class SearchByYearPage extends Page {
  static url = 'search-by-year.html'

  static at = {
    title == 'SearchByYearPage'
  }

  static content = {
    searchByYearForm {$("#search-year-form")}
    selectyear {searchByYearForm.selectyear}
    submitButton(to: IndexPage) {
    searchByYearForm.find("input",type: "submit")
    }
  }
}
