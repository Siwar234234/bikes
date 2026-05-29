package at.spengergasse.domain;

public class BikeException extends RuntimeException{
    public BikeException(String meldung){
        super(meldung);
    }
}
