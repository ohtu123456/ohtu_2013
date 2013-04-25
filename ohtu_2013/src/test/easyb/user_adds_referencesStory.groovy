import com.ohtu123456.ohtu_2013.Storage.*
import com.ohtu123456.ohtu_2013.UserInterface.*
import com.ohtu123456.ohtu_2013.logic.*
import com.ohtu123456.ohtu_2013.Storage.*

book =  ["-add",
        "-book",
        "BA04",
        "Beck, Kent and Andres, Cynthia",
        "Extreme Programming Explained: Embrace Change (2nd Edition)",
        "2004",
        "Addison-Wesley Professional",
        "-print",
        "-quit"] as String[]

#bookprint = "{ID: BA04,  AUTHOR: Beck, Kent and Andres, Cynthia,  TITLE: Extreme Programming Explained: Embrace Change (2nd Edition),  YEAR: 2004}"
bookprint = "@book{BA04,author = {Beck, Kent and Andres, Cynthia},title = {Extreme Programming Explained: Embrace Change (2nd Edition)},year = {2004},publisher = {Addison-Wesley Professional}
inproceedings = ["-add",
                 "-inproceedings",
                 "Begel_2008",
                 "Begel, Andrew and Simon, Beth",
                 "Struggles of new college graduates in their first software development job",
                 "2008",
                 "ACM",
                 "Proceedings of the SIGCSE '08",
                 "133",
                 "-print",
                 "-quit"] as String[]

#inproceedingsprint = "{ID: Begel_2008,  AUTHOR: Begel, Andrew and Simon, Beth,  TITLE: Struggles of new college graduates in their first software development job,  YEAR: 2008}"
inproceedingsprint = "@inproceedings{Begel_2008,author = {Begel, Andrew and Simon, Beth},title = {Struggles of new college graduates in their first software development job},address = {},pages = {133},year = {2008},booktitle = {Proceedings of the SIGCSE '08},publisher = {ACM},}"

article = ["-add",
           "-article",
           "fox",
           "Fox, Armando and Patterson, David",
           "Crossing the software education chasm",
           "2012",
           "ACM",
           "Communications of ACM",
           "55",
           "5",
           "44-49",
           "New York, NY, USA",
           "-print",
           "-quit"] as String[]

#articleprint = "{ID: fox,  AUTHOR: Fox, Armando and Patterson, David,  TITLE: Crossing the software education chasm,  YEAR: 2012}"
articleprint = "@article{fox,journal = {Communications of ACM},author = {Fox, Armando and Patterson, David},title = {Crossing the software education chasm},address = {New York, NY, USA},pages = {44-49},volume = {55},year = {2012},number = {5},publisher = {ACM},}" 

scenario "user adds a new book reference",    {
    given "a new program instance", {        
        stubio = new StubIO(book)
        db = new StorageDatabase("testidatabase", true);
        logic = new Logic(db);
        ui = new UI(stubio,logic);
    }

    when "user adds a new book and prints it", {
        ui.initialize();
    }

    then "the added book is in database",   {
        output = stubio.getInput();
        ensure(output)  {
            contains(bookprint);
        }
    }
} 

scenario "user adds a new article reference",   {
    given "a new program instance", {
        stubio = new StubIO(article)
        db = new StorageDatabase("testidatabase", true);
        logic = new Logic(db);
        ui = new UI(stubio,logic);
    }
    
    when "user adds a new article and prints it",   {
        ui.initialize();
    }
    
    then "the added article is in database",    {
        output = stubio.getInput();
        ensure(output)  {
            contains(articleprint);
        }
    }
}

scenario "user adds a new inproceedings reference",   {
    given "a new program instance", {
        stubio = new StubIO(inproceedings)
        db = new StorageDatabase("testidatabase", true);
        logic = new Logic(db);
        ui = new UI(stubio,logic);
    }
    
    when "user adds a new inproceeding and prints it",   {
        ui.initialize();
    }
    
    then "the added inproceedings is in database",  {
        output = stubio.getInput();
        ensure(output)  {
            contains(inproceedingsprint);
        }
    }
}
