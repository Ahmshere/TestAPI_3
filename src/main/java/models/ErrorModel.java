package models;

public class ErrorModel {
    int status;
    String error;
    Object message; // Изменил тип на Object

    public ErrorModel status(int status) {
        this.status = status;
        return this;
    }

    public ErrorModel error(String error) {
        this.error = error;
        return this;
    }

    public ErrorModel message(Object message) { // Изменили тип на Object
        this.message = message;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getMessage() { // Изменили тип на Object
        return message;
    }

    public void setMessage(Object message) { // Изменили тип на Object
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorModel{" +
                "status=" + status +
                ", error='" + error + '\'' +
                ", message=" + message + // Изменили тип на Object
                '}';
    }
}
