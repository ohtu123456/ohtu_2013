
import com.ohtu123456.ohtu_2013.UserInterface.*
import com.ohtu123456.ohtu_2013.Logic.*
import com.ohtu123456.ohtu_2013.Storage.*
import com.ohtu123456.ohtu_2013.BibtexParser.*

scenario "user adds a new article", {
    given "a new program instance", {
        io = new StubIO("-add","-article","kirjaID", "Testi Kirjailija", "Testikirja", "1992", "Otava", "Journaali", "12", "1", "112", "osoite");
        ui = new UI(io);     
        ui.initialize();
    }
    
    when "user selects add article and inputs data",    {
        
    }
    
    then "a new reference is added",    {
        ensure(io.getInput())  {
            contains("New reference added");
        }
    }
}