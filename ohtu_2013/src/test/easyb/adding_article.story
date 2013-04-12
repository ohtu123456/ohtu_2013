import com.ohtu123456.ohtu_2013.UserInterface.*

description "User can add article-type reference manually"

scenario "user can add article",  {
    given 'user has selected add reference', {
        printer = new StubPrinter()
        reader = new StubReader("1", "1", "Maija Mallikas", "Maijan päiväkirja", "2013", "Otava", "0")
//reader = new StubReader("1", "1", "Matti Meikäläinen", "JokuOtsikko", "2013", "Otava", "0")
        ui = new UI(reader, printer)
    }
    when 'user inputs data', {
        ui.start()
    }
    then 'success will be reported', {
        printer.getPrints().shouldHave("Reference added.\n")
    }
}