package shop.person;

/**
 *
 * @author Justas
 */

import shop.exceptions.*;

/**
 * Implementation of a person
 * 
 * @author      Justas Rutkauskas, <rujustas @ gmail.com>
 * @lastChange  2014 - 05 - 13
 */
public abstract class Person implements Cloneable {
    /**
    * number of clients
    */
    private static int lastId;
    
    /**
    * client's id
    */
    private final int id;
   
    /**
    * name of a person
    */
    private String name;
    
    /**
    * surname of a person
    */
    private String surname;
   
    /** 
    * Class constructor.
    * @param name - name of a new person
    * @param surname - surname of a new person
    */
    public Person(String name, String surname) throws SetterException	
    {
        lastId++;
        id = lastId;
        setName(name);
        setSurname(surname);						
    }
    
   // Setters:

    /**
    * sets person's name
    * @param name - person's name
    * @throws SetterException
    */
    public void setName(String name) throws SetterException
    {
        if (name.length() > 0)
        {
            this.name = name;
        } else { 
            throw new SetterException("Person's name string is empty");
        }
    }
    
    /**
    * sets person's surname
    * @param surname - person's surname
    */
    public void setSurname(String surname) throws SetterException
    {
        if (surname.length() > 0)
        {
            this.surname = surname;
        } else { 
            throw new SetterException("Person's surname string is empty");
        }
    }

        // Getters:
    
    /**
    * getter for id field
    * @return client's id
    */
    public int getID() 
    {
        return this.id;
    }

    /**
    * getter for lastID field
    * @return number of clients
    */
    public int getLastId() 
    {
        return this.lastId;
    }
    
    /**
    * getter for name field
    * @return person's name
    */
    public String getName()
    {
        return this.name;
    }

    /**
    * getter for surname field
    * @return person's surname
    */
    public String getSurname()
    {
    	return this.surname;
    }
    
    @Override
    public String toString()
    {
        return this.name  + " " + this.surname;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}