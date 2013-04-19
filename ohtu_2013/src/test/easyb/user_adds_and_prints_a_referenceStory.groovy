scenario "user adds a new reference and prints it", {
    given "a new program instance", {
        ui = new UI(new Logic());
        ui.initialize();
        ui.setOutput("testfile.txt");
    }
    
    when "user adds a new reference",   {
        ui.setInput("-add\n-article\nTestimies\n1992\n");
    }
    
    then "the added reference is printed",    {
        ui.start();
        testOutput = new File("testfile.txt").text;
        ensure(testOutput)  {
            contains("Testimies");
            contains("1992");
        }
    }
}