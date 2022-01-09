package mflix.api.daos;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import mflix.api.models.Session;
import mflix.api.models.User;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.swing.text.html.Option;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
public class UserDao extends AbstractMFlixDao {

    private final MongoCollection<User> usersCollection;
    //Ticket: User Management - do the necessary changes so that the sessions collection
    //returns a Session object
    //private final MongoCollection<Document> sessionsCollection;
    private final MongoCollection<Session> sessionsCollection;

    private final Logger log;

    @Autowired
    public UserDao(
            MongoClient mongoClient, @Value("${spring.mongodb.database}") String databaseName) {
        super(mongoClient, databaseName);
        CodecRegistry pojoCodecRegistry =
                fromRegistries(
                        MongoClientSettings.getDefaultCodecRegistry(),
                        fromProviders(PojoCodecProvider.builder().automatic(true).build()));



        log = LoggerFactory.getLogger(this.getClass());
        // Ticket: User Management - implement the necessary changes so that the sessions
        // collection returns a Session objects instead of Document objects.
        usersCollection = db.getCollection("users", User.class).withCodecRegistry(pojoCodecRegistry);
        sessionsCollection = db.getCollection("sessions",Session.class).withCodecRegistry(pojoCodecRegistry);
    }

    /**
     * Inserts the `user` object in the `users` collection.
     *
     * @param user - User object to be added
     * @return True if successful, throw IncorrectDaoOperation otherwise
     */
    public boolean addUser(User user) {


        // Ticket: Durable Writes -  you might want to use a more durable write concern here!
        //usersCollection.withWriteConcern(WriteConcern.W1).insertOne(user);
        // Ticket: Handling Errors - make sure to only add new users
        // and not users that already exist.
        try {
            usersCollection.withWriteConcern(WriteConcern.MAJORITY).insertOne(user);
            log.info("email {} is found if user is inserted ", usersCollection.find(eq("email", user.getEmail())).first().getEmail());
            return true;
        }
        catch (MongoException me)
        {
            log.error("An error ocurred while trying to insert a User.");
            if (ErrorCategory.fromErrorCode(me.getCode() ) == ErrorCategory.DUPLICATE_KEY) {
                throw new IncorrectDaoOperation("The User is already in the database.");
            }
            return false;
        }
        catch (Exception e)
        {
            log.info("Exeption {} recived",e.getMessage());
            return false;
        }


    }

    /**
     * Creates session using userId and jwt token.
     *
     * @param userId - user string identifier
     * @param jwt    - jwt string token
     * @return true if successful
     */
    public boolean createUserSession(String userId, String jwt) {
        // Ticket: User Management - implement the method that allows session information to be
        // stored in it's designated collection.

        //Step 1 : Created a session object
        Session newSessionObject=new Session();
        newSessionObject.setUserId(userId);
        newSessionObject.setJwt(jwt);
        try {
            if (Optional.ofNullable(sessionsCollection.find(eq("user_id", userId)).first()).isPresent()) {

                log.info("found {} in db", userId);
                sessionsCollection.updateOne(eq("user_id", userId), set("jwt", jwt));
            } else {
                log.info("not found {} in db", userId);
                sessionsCollection.insertOne(newSessionObject);
            }
        }
        catch (MongoException me)
        {
            //DONE > Ticket: Handling Errors - implement a safeguard against
            // creating a session with the same jwt token.
            log.error("Mongo Exception while creating/updating a new session Detials : {}",me.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Returns the User object matching the an email string value.
     *
     * @param email - email string to be matched.
     * @return User object or null.
     */
    public User getUser(String email) {
        User user = Optional.of(usersCollection.find(eq("email", email)).first()).get();
        //DONE> Ticket: User Management - implement the query that returns the first User object.

        return usersCollection.find(eq("email", email)).first();

    }

    /**
     * Given the userId, returns a Session object.
     *
     * @param userId - user string identifier.
     * @return Session object or null.
     */
    public Session getUserSession(String userId) {
        Optional<Session> session=Optional.ofNullable(sessionsCollection.find(eq("user_id",userId)).first());
        if(session.isPresent())
        {
            log.info("found user_id {} in userSession table ",session.get().getUserId());
            return session.get();
        }
        return null;
    }

    public boolean deleteUserSessions(String userId) {
        //DONE
        return sessionsCollection.deleteMany(eq("user_id",userId)).wasAcknowledged();
    }

    /**
     * Removes the user document that match the provided email.
     *
     * @param email - of the user to be deleted.
     * @return true if user successfully removed
     */
    public boolean deleteUser(String email) {
        // remove user sessions
        //TODO> Ticket: User Management - implement the delete user method
        //TODO > Ticket: Handling Errors - make this method more robust by
        // handling potential exceptions.
        boolean useDeleted=true;
        boolean sessionDeleted=true;
        sessionsCollection.deleteMany(eq("user_id", email));
        usersCollection.deleteMany(eq("email", email));

        return true;
    }

    /**
     * Updates the preferences of an user identified by `email` parameter.
     *
     * @param email           - user to be updated email
     * @param userPreferences - set of preferences that should be stored and replace the existing
     *                        ones. Cannot be set to null value
     * @return User object that just been updated.
     */
    public boolean updateUserPreferences(String email, Map<String, ?> userPreferences) {
        //TODO> Ticket: User Preferences - implement the method that allows for user preferences to
        // be updated.
        //TODO > Ticket: Handling Errors - make this method more robust by
        // handling potential exceptions when updating an entry.
        try {

            if(userPreferences ==null)
            {
                throw new IncorrectDaoOperation("Invalid user preference value(s)");
            }


            Bson queryFilter = new Document("email", email);
            log.info("for email-id : {}, updating preference : {}",email,userPreferences.toString());
            usersCollection.updateOne(
                    queryFilter,
                    set("preferences",
                            Optional.ofNullable(userPreferences)
                                    .orElseThrow(() -> new IncorrectDaoOperation("Invalid user preference value(s)"))));

        }
        catch (MongoException mongoException)
        {
            log.error("MongoException while updating preference : {}"+mongoException.getMessage());
            return false;
        }

        return true;
    }
}
