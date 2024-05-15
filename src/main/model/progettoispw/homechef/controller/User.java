package progettoispw.letmeknow.controller;

public interface User {
    String getUserid();
    String getType();
    boolean confirmCredentials(String password);
    boolean isErrorOccurred();
    void setErrorOccurred(boolean errorOccurred);
}
