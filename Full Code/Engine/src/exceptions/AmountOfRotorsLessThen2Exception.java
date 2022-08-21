package exceptions;

public class AmountOfRotorsLessThen2Exception extends RuntimeException {
    private int amountOfRotors;
    private final String EXCEPTION_MESSAGE = "The amount of rotors that the Machine needs (%d) is < 2";

    public AmountOfRotorsLessThen2Exception(int amountOfRotors){
        this.amountOfRotors = amountOfRotors;
    }

    @Override
    public String getMessage() {
        return String.format(EXCEPTION_MESSAGE, amountOfRotors);
    }
}
