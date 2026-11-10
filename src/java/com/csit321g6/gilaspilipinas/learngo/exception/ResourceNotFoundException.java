package java.com.csit321g6.gilaspilipinas.learngo.exception;

/** Thrown when an id does not exist. Becomes HTTP 404. */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Long id) {
        super(resource + " not found with id " + id);
    }
}