package pe.edu.upc.ea2025.sacjExceptions;

public class InvalidDataRangeException extends RuntimeException {
    public InvalidDataRangeException(String message) { super(message); }
    public InvalidDataRangeException() { super(); }
}
