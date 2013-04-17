import com.ohtu123456.ohtu_2013.UserInterface.*;
import com.ohtu123456.ohtu_2013.logic.LogicInterface;
import com.ohtu123456.ohtu_2013.logic.Logic;

scenario "user adds a new article type reference",  {
    given "a new program instance",     {
        ui = new UI(new Logic());
        ui.initialize();
        ui.setOutput("testfile.txt");
    }
    when "selects add new article and fills in the fields",  {
        ui.giveInput("-add\n-article\nTestimies\n1992");
        ui.start();
    }
    then "a new reference is added",    {
        
    }
}