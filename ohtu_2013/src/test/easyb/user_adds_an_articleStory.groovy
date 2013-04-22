import com.ohtu123456.ohtu_2013.UserInterface.*
import com.ohtu123456.ohtu_2013.logic.*

scenario "user adds a new article", {
    given "a new program instance", {
        //io = new StubIO("-add","-article","kirjaID", "Testi Kirjailija", "Testikirja", "1992", "Otava", "Journaali", "12", "1", "112", "osoite");
        ui = new UI();     
    }
    
    when "user selects add article and inputs data",    {
        ui.initialize();
    }
    
    then "a new reference is added",    {
    }
}