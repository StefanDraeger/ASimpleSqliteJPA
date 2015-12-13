package de.stefandraegersoftware.database.exception;


public class AnnotationNotFoundException extends RuntimeException {

    public AnnotationNotFoundException(String message) {
        super(message);
    }

    public AnnotationNotFoundException(String annotationClassname, String targetClassname) {
        this("Missing annotation [" + annotationClassname + "] at class [" + targetClassname + "]");
    }

}
