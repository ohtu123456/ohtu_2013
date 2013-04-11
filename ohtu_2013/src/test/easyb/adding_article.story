import com.ohtu123456.ohtu_2013.UserInterface.*

description "User can add article-type reference manually"

//scenario "user cannot add article with no data", {
//    given 'user has selected add reference', {
//        printer = new StubPrinter()
//        reader = new StubReader("1", "2")
//        ui = new UI(reader, printer)
//    }
//    when 'user inputs data', {
//        ui.start()
//    }
//    then 'failure will be reported', {
//        printer.getPrints().shouldHave("Lisäys epäonnistui")
//    }
//}

scenario "user can add article with title", {
    given 'user has selected add reference', {
        printer = new StubPrinter()
        reader = new StubReader("1", "1", "", "JokuOtsikko", "", "")
        ui = new UI(reader, printer)
    }
    when 'user inputs data', {
        ui.start()
    }
    then 'success will be reported', {
        print(printer.getPrints())
        printer.getPrints().shouldHave("Viite lisätty")
    }
}

scenario "user cannot add article without title", {
    given 'user has selected add reference'
    when 'user inputs data'
    then 'failure will be reported'
}

scenario "user can add article with journal field", {
    given 'user has selected add reference'
    when 'user inputs data'
    then 'success will be reported'
}

scenario "user can add article with author field", {
    given 'user has selected add reference'
    when 'user inputs data'
    then 'success will be reported'
}

scenario "user can add article with volume field", {
    given 'user has selected add reference'
    when 'user inputs data'
    then 'success will be reported'
}

scenario "user can add article with number field", {
    given 'user has selected add reference'
    when 'user inputs data'
    then 'success will be reported'
}

scenario "user can add article with year field", {
    given 'user has selected add reference'
    when 'user inputs data'
    then 'success will be reported'
}

scenario "user can add article with pages field", {
    given 'user has selected add reference'
    when 'user inputs data'
    then 'success will be reported'
}

scenario "user can add article with publisher field", {
    given 'user has selected add reference'
    when 'user inputs data'
    then 'success will be reported'
}

scenario "user can add article with address field", {
    given 'user has selected add reference'
    when 'user inputs data'
    then 'success will be reported'
}
