import com.ohtu123456.ohtu_2013.UserInterface.*;
import com.ohtu123456.ohtu_2013.logic.LogicInterface;
import com.ohtu123456.ohtu_2013.logic.Logic;

scenario "user adds a new article type reference",  {
    
    given "a new program instance",     {
        ui = new UI(new Logic());
        ui.initialize();
        ui.setOutput("testfile.txt");
    }
    
    when "user selects new article and fills in the fields",    {
        ui.setInput("-add\n-article\nTestimies\n1992\n-quit\n");
    }
    
    then "a new reference is added",    {
        ui.start();
        testOutput = new File("testfile.txt").text;
        ensure(testOutput)  {
            contains("New reference added");
        }
    }
}
