package de.stefandraegersoftware.asimplesqlitejpa.database.annotations.java.de.stefandraegersoftware.asimplesqlitejpa.database.exception;


public class AnnotationNotFoundException extends RuntimeException {

    public AnnotationNotFoundException(String message) {
        super(message);
    }

    public AnnotationNotFoundException(String annotationClassname, String targetClassname) {
        this("Missing annotation [" + annotationClassname + "] at class [" + targetClassname + "]");
    }

}
