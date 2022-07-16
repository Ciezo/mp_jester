/**
    Author: Cloyd Van S. Secuya
    Date of Creation: July 12, 2022
    Description:
        Populate the jester_users
*/

-- Start using the created database from the schema
USE Jester_DB;

INSERT INTO jester_users (ID_auth, f_name, l_name, userName, user_pwd)
    VALUES
    -- BEGIN POPULATING ROWS
        /** 
            @NOTE: 
                THESE ROW VALUES ARE CONSIDERED TO BE THE FIRST INITIALIZATION TO FILL UP THE DATABASE. 
                THEY ARE MORE LIKE THE DEVELOPERS WHO ALREADY HAVE BACKDOOR ACCESS TO THE APP WITHOUT REGISTRATION
        */        
        ROW("100", "Cloyd", "Secuya", "cloydvan", "12345"),
        ROW("200", "Ron", "Relayo", "ron", "12345"),
        ROW("300", "Karl", "Halcon", "karl", "12345"),
        ROW("400", "Nadrin", "Caluya", "nadrin", "12345");
        
        /* New row values will be inserted, and they are considered to be the NEW USERS who registered the app. */
        /* After this, it is expected that the app itself will be the one creating and executing the SQL statements */
    -- END POPULATING ROWS
