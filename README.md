# Getting Started: Accessing Relational Data with Spring

What you'll build
-----------------

This guide walks you through the process of accessing relational data with Spring.

What you'll need
----------------

 - About 15 minutes
 - {!include#prereq-editor-jdk-buildtools}

## {!include#how-to-complete-this-guide}

<a name="scratch"></a>
Set up the project
------------------

{!include#build-system-intro}

{!include#create-directory-structure-hello}

### Create a Maven POM

{!include#maven-project-setup-options}

    {!include:complete/pom.xml}

{!include#bootstrap-starter-pom-disclaimer}

<a name="initial"></a>
Creating a Customer object
--------------------------
The first thing we need is a domain object to represent our data. In this case, we are going to store and retrieve first and last names. To capture this, you need to define a `Customer` class.

    {!include:complete/src/main/java/hello/Customer.java}


Storing and retrieving data
---------------------------
Spring provides a convenient template class called the `JdbcTemplate`. It makes working with relational SQL databases through JDBC a trivial affair. When you look at most JDBC code, it's mired in resource acquisition, connection management, exception handling and general error checking code that is wholly unrelated to what the code is trying to achieve. The `JdbcTemplate` takes care of all of that for you. All you have to do is focus on the task at hand.

    {!include:complete/src/main/java/hello/Application.java}

This example sets up a JDBC `DataSource` using Spring's handy `SimpleDriverDataSource` (this class is **not** intended for production!). Then, we use that to construct a `JdbcTemplate` instance. For more on DataSources, see [this link]().

Once we have our configured `JdbcTemplate`, it's easy to then start making calls to the database. 

First, we configure the table to store data using `JdbcTemplate`'s `execute` method.

Then, we insert some records in our newly created table using `JdbcTemplate`'s `update` method. The first argument to the method call is the query string, the last argument (the array of `Object`s) holds the variables to be substituted into the query where the "`?`" characters are.

> **Note:** Using `?` for arguments avoids [SQL injection attacks](http://en.wikipedia.org/wiki/SQL_injection) by instructing JDBC to bind variables.

Finally we use the `query` method to search our table for records matching our criteria. We again use the "`?`" arguments to parameterize the query, passing in the actual values when we make the call. The last argument in the `query` method is an instance of `RowMapper<T>`, which we provide. Spring's done 90% of the work, but it can't possibly know what we want it to do with the result set data. So, we provide a `RowMapper<T>` instance that Spring will call for each record, aggregate the results, and then give back to us as a collection. 


## {!include#build-an-executable-jar}


Run the application
-------------------

Run your application with `java -jar` at the command line:

    java -jar target/gs-relational-data-access-0.1.0.jar


You should see the following output:

    Creating tables
    Inserting customer record for John Woo
    Inserting customer record for Jeff Dean
    Inserting customer record for Josh Bloch
    Inserting customer record for Josh Long
    Querying for customer records where first_name = 'Josh':
    Customer[id=3, firstName='Josh', lastName='Bloch']
    Customer[id=4, firstName='Josh', lastName='Long']


Summary
-------
Congrats! You've just developed a simple JDBC client using Spring. There's more to building and working with JDBC and data stores in general than is covered here, but this should provide a good start.
