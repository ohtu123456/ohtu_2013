import com.ohtu123456.ohtu_2013.Storage.*

scenario "test",    {
    given "a new database", {
        db = new StorageDatabase("testidb");
    }
    
    then "it should not be null",   {
        ensure(db)  {
            isNotNull
        }
    }
}